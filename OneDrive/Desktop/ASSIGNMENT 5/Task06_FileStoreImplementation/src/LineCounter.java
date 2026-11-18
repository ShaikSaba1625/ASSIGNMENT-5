import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class LineCounter {
    public static long countOneByteAtATime(Path path) throws IOException {
        long count = 0;
        try (FileInputStream input = new FileInputStream(path.toFile())) {
            int value;
            while ((value = input.read()) != -1) {
                if (value == '\n') {
                    count++;
                }
            }
        }
        return count;
    }

    public static long countBuffered(Path path) throws IOException {
        long count = 0;
        byte[] buffer = new byte[8192];
        try (BufferedInputStream input =
                     new BufferedInputStream(new FileInputStream(path.toFile()))) {
            int used;
            while ((used = input.read(buffer)) != -1) {
                for (int i = 0; i < used; i++) {
                    if (buffer[i] == '\n') {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Usage: java LineCounter <file>");
            return;
        }
        System.out.println("Line feeds: " + countBuffered(Path.of(args[0])));
    }
}
