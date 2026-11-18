import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class B1Benchmark {
    public static void main(String[] args) throws IOException {
        Path sample = Files.createTempFile("line-counter-benchmark-", ".txt");
        try {
            byte[] block = new byte[8192];
            java.util.Arrays.fill(block, (byte) 'x');
            block[block.length - 1] = '\n';
            try (java.io.OutputStream output = Files.newOutputStream(sample)) {
                for (int i = 0; i < 128; i++) {
                    output.write(block);
                }
            }

            long start = System.nanoTime();
            long unbuffered = LineCounter.countOneByteAtATime(sample);
            long unbufferedNanos = System.nanoTime() - start;
            start = System.nanoTime();
            long buffered = LineCounter.countBuffered(sample);
            long bufferedNanos = System.nanoTime() - start;
            System.out.println("Line counts: " + unbuffered + " / " + buffered);
            System.out.println("One-byte time (ms): " + unbufferedNanos / 1_000_000.0);
            System.out.println("Buffered time (ms): " + bufferedNanos / 1_000_000.0);
        } finally {
            Files.deleteIfExists(sample);
        }
    }
}
