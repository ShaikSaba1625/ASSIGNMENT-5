import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcExperiment {
    private static List<String[]> createRows(int count) {
        List<String[]> rows = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            rows.add(new String[]{"EXP" + System.currentTimeMillis() + "_" + i,
                    "STU" + i, "Student " + i, "1", "Main Gate",
                    "2026-12-31", "1000.00"});
        }
        return rows;
    }

    public static void main(String[] args) throws Exception {
        List<String[]> rows = createRows(10);
        try (Connection connection = DatabaseConnection.open()) {
            long start = System.nanoTime();
            int inserted = PassLoader.saveBatch(connection, rows);
            long elapsedMillis = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Inserted rows: " + inserted);
            System.out.println("Batch time (ms): " + elapsedMillis);
        } catch (SQLException exception) {
            System.err.println("JDBC experiment failed: " + exception.getMessage());
            throw exception;
        }
    }
}
