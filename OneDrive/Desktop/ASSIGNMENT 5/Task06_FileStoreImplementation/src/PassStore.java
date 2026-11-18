import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PassStore {
    public static class Pass {
        private final String passId;
        private final String registerNumber;
        private final String studentName;
        private final int routeNumber;
        private final String boardingStop;
        private final LocalDate validityDate;
        private final double amountPaid;

        public Pass(String passId, String registerNumber, String studentName,
                    int routeNumber, String boardingStop, LocalDate validityDate,
                    double amountPaid) {
            this.passId = passId;
            this.registerNumber = registerNumber;
            this.studentName = studentName;
            this.routeNumber = routeNumber;
            this.boardingStop = boardingStop;
            this.validityDate = validityDate;
            this.amountPaid = amountPaid;
            validate();
        }

        private void validate() {
            if (passId == null || registerNumber == null || studentName == null
                    || boardingStop == null || passId.isBlank()
                    || registerNumber.isBlank() || studentName.isBlank()
                    || boardingStop.isBlank()) {
                throw new IllegalArgumentException("Text fields cannot be blank.");
            }
            if (routeNumber <= 0 || !Double.isFinite(amountPaid) || amountPaid < 0) {
                throw new IllegalArgumentException("Route and amount must be valid.");
            }
            if (validityDate == null) {
                throw new IllegalArgumentException("Validity date is required.");
            }
        }

        public String getPassId() {
            return passId;
        }

        public int getRouteNumber() {
            return routeNumber;
        }

        @Override
        public String toString() {
            return passId + " | " + registerNumber + " | " + studentName + " | "
                    + routeNumber + " | " + boardingStop + " | " + validityDate
                    + " | " + amountPaid;
        }
    }

    public static void add(Path file, Pass pass) throws IOException {
        Path parent = file.toAbsolutePath().getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8,
                java.nio.file.StandardOpenOption.CREATE,
                java.nio.file.StandardOpenOption.APPEND)) {
            writer.write(encode(pass));
            writer.newLine();
        }
    }

    public static Optional<Pass> findById(Path file, String wantedId) throws IOException {
        for (Pass pass : readAll(file)) {
            if (pass.passId.equals(wantedId)) {
                return Optional.of(pass);
            }
        }
        return Optional.empty();
    }

    public static List<Pass> listByRoute(Path file, int wantedRoute) throws IOException {
        List<Pass> matches = new ArrayList<>();
        for (Pass pass : readAll(file)) {
            if (pass.routeNumber == wantedRoute) {
                matches.add(pass);
            }
        }
        return matches;
    }

    public static List<Pass> readAll(Path file) throws IOException {
        List<Pass> passes = new ArrayList<>();
        if (!Files.exists(file)) {
            return passes;
        }
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) {
                    continue;
                }
                try {
                    passes.add(decode(line));
                } catch (IllegalArgumentException exception) {
                    System.err.println("Skipping damaged pass row " + lineNumber + ": "
                            + exception.getMessage());
                }
            }
        }
        return passes;
    }

    private static String encode(Pass pass) {
        return String.join("|", escape(pass.passId), escape(pass.registerNumber),
                escape(pass.studentName), Integer.toString(pass.routeNumber),
                escape(pass.boardingStop), pass.validityDate.toString(),
                Double.toString(pass.amountPaid));
    }

    private static String escape(String text) {
        return text.replace("\\", "\\\\").replace("|", "\\|")
                .replace("\r", "\\r").replace("\n", "\\n");
    }

    private static Pass decode(String line) {
        List<String> fields = splitEscaped(line);
        if (fields.size() != 7) {
            throw new IllegalArgumentException("Expected 7 fields, found " + fields.size());
        }
        try {
            return new Pass(fields.get(0), fields.get(1), fields.get(2),
                    Integer.parseInt(fields.get(3)), fields.get(4),
                    LocalDate.parse(fields.get(5)), Double.parseDouble(fields.get(6)));
        } catch (RuntimeException exception) {
            throw new IllegalArgumentException("Invalid field value.", exception);
        }
    }

    private static List<String> splitEscaped(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        boolean escaped = false;
        for (int i = 0; i < line.length(); i++) {
            char current = line.charAt(i);
            if (escaped) {
                if (current == 'n') {
                    field.append('\n');
                } else if (current == 'r') {
                    field.append('\r');
                } else if (current == '\\' || current == '|') {
                    field.append(current);
                } else {
                    throw new IllegalArgumentException("Invalid escape sequence.");
                }
                escaped = false;
            } else if (current == '\\') {
                escaped = true;
            } else if (current == '|') {
                fields.add(field.toString());
                field.setLength(0);
            } else {
                field.append(current);
            }
        }
        if (escaped) {
            throw new IllegalArgumentException("Incomplete escape at end of row.");
        }
        fields.add(field.toString());
        return fields;
    }

    public static void main(String[] args) throws IOException {
        Path file = Files.createTempFile("assignment-pass-", ".txt");
        try {
            Pass sample = new Pass("P1001", "STU100", "Saba Shaik", 12,
                    "Central Station", LocalDate.of(2026, 12, 31), 4500.0);
            add(file, sample);
            System.out.println(findById(file, "P1001").orElseThrow());
        } finally {
            Files.deleteIfExists(file);
        }
    }
}
