import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PassLoader {
    public static int saveBatch(Connection connection, List<String[]> rows)
            throws SQLException {
        String sql = "INSERT INTO bus_pass "
                + "(pass_id, register_number, student_name, route_number, "
                + "boarding_stop, validity_date, amount_paid) VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean oldAutoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (String[] row : rows) {
                if (row.length != 7) {
                    throw new IllegalArgumentException("Each pass must have seven fields.");
                }
                statement.setString(1, row[0]);
                statement.setString(2, row[1]);
                statement.setString(3, row[2]);
                statement.setInt(4, Integer.parseInt(row[3]));
                statement.setString(5, row[4]);
                statement.setDate(6, java.sql.Date.valueOf(row[5]));
                statement.setDouble(7, Double.parseDouble(row[6]));
                statement.addBatch();
            }
            int[] updates = statement.executeBatch();
            connection.commit();
            return updates.length;
        } catch (SQLException | RuntimeException exception) {
            connection.rollback();
            throw exception;
        } finally {
            connection.setAutoCommit(oldAutoCommit);
        }
    }

    public static void main(String[] args) {
        System.out.println("PassLoader saves validated pass rows in a JDBC transaction.");
    }
}
