import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Migration {
    public static void main(String[] args) throws Exception {
        Path input = args.length == 0
                ? Path.of("Task08_JDBC_Batch_Migration", "input", "existing_passes.csv")
                : Path.of(args[0]);
        List<String[]> validRows = new ArrayList<>();
        MigrationReport report = new MigrationReport();
        int lineNumber = 0;

        for (String line : Files.readAllLines(input)) {
            lineNumber++;
            if (lineNumber == 1 && line.startsWith("pass_id,")) {
                continue;
            }
            String[] fields = line.split(",", -1);
            try {
                if (fields.length != 7) {
                    throw new IllegalArgumentException("Expected 7 comma-separated fields.");
                }
                new PassStore.Pass(fields[0], fields[1], fields[2],
                        Integer.parseInt(fields[3]), fields[4],
                        java.time.LocalDate.parse(fields[5]),
                        Double.parseDouble(fields[6]));
                validRows.add(fields);
                report.addAccepted();
            } catch (RuntimeException exception) {
                report.addRejected(lineNumber, exception.getMessage());
            }
        }

        report.print();
        if (!validRows.isEmpty()) {
            try (Connection connection = DatabaseConnection.open()) {
                System.out.println("Inserted rows: " + PassLoader.saveBatch(connection, validRows));
            }
        }
    }
}
