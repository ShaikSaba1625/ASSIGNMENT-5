import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class PassStoreTest {
    public static void main(String[] args) throws IOException {
        Path file = Files.createTempFile("pass-store-test-", ".txt");
        try {
            PassStore.Pass pass = new PassStore.Pass("P|100", "STU100", "Asha",
                    7, "North Stop", LocalDate.of(2026, 12, 31), 2000.0);
            PassStore.add(file, pass);
            if (PassStore.findById(file, "P|100").isEmpty()) {
                throw new AssertionError("Escaped pass ID was not found.");
            }

            Files.writeString(file, "bad|row\n", StandardCharsets.UTF_8,
                    java.nio.file.StandardOpenOption.APPEND);
            if (PassStore.readAll(file).size() != 1) {
                throw new AssertionError("A damaged row affected the valid row.");
            }
            if (PassStore.listByRoute(file, 7).size() != 1) {
                throw new AssertionError("Route search returned the wrong result.");
            }
            System.out.println("PassStore tests passed.");
        } finally {
            Files.deleteIfExists(file);
        }
    }
}
