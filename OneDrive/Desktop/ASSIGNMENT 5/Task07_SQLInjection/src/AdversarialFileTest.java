import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AdversarialFileTest {
    public static int countValidRows(Path file) throws IOException {
        int valid = 0;
        int lineNumber = 0;
        for (String line : Files.readAllLines(file)) {
            lineNumber++;
            String[] fields = line.split("\\|", -1);
            boolean validRow = fields.length == 7;
            if (validRow) {
                try {
                    Integer.parseInt(fields[3]);
                    Double.parseDouble(fields[6]);
                } catch (NumberFormatException exception) {
                    validRow = false;
                }
            }
            if (validRow) {
                valid++;
            } else {
                System.out.println("Rejected row " + lineNumber);
            }
        }
        return valid;
    }

    public static void main(String[] args) throws IOException {
        Path file = args.length == 0
                ? Path.of("Task07_SQLInjection", "input", "damaged_passes.csv")
                : Path.of(args[0]);
        System.out.println("Valid rows: " + countValidRows(file));
    }
}
