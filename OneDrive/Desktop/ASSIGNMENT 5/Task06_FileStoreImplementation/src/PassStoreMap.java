import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PassStoreMap {
    public static Map<String, PassStore.Pass> load(Path file) throws IOException {
        Map<String, PassStore.Pass> index = new HashMap<>();
        for (PassStore.Pass pass : PassStore.readAll(file)) {
            index.put(pass.getPassId(), pass);
        }
        return index;
    }

    public static Optional<PassStore.Pass> find(Path file, String passId) throws IOException {
        return Optional.ofNullable(load(file).get(passId));
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Usage: java PassStoreMap <file>");
            return;
        }
        System.out.println("Indexed passes: " + load(Path.of(args[0])).size());
    }
}
