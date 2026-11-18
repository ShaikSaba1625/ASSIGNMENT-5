import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class B1Runner {
    public static void main(String[] args) throws IOException {
        Path file = args.length == 0 ? Path.of("passes.txt") : Path.of(args[0]);
        PassStore.add(file, new PassStore.Pass("P1003", "192311003", "Divya",
                7, "Main Gate", LocalDate.of(2026, 12, 31), 3800.0));
        List<PassStore.Pass> matches = PassStore.listByRoute(file, 7);
        for (PassStore.Pass pass : matches) {
            System.out.println(pass);
        }
    }
}
