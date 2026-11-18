import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MultiRowAndLoadData {
    public static int insertBatch(Connection connection, List<String[]> rows)
            throws SQLException {
        return PassLoader.saveBatch(connection, rows);
    }

    public static int insertMultiRow(Connection connection, List<String[]> rows)
            throws SQLException {
        if (rows.isEmpty()) {
            return 0;
        }
        StringBuilder sql = new StringBuilder("INSERT INTO bus_pass "
                + "(pass_id, register_number, student_name, route_number, "
                + "boarding_stop, validity_date, amount_paid) VALUES ");
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).length != 7) {
                throw new IllegalArgumentException("Each pass must have seven fields.");
            }
            if (i > 0) {
                sql.append(", ");
            }
            sql.append("(?, ?, ?, ?, ?, ?, ?)");
        }
        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            int parameter = 1;
            for (String[] row : rows) {
                statement.setString(parameter++, row[0]);
                statement.setString(parameter++, row[1]);
                statement.setString(parameter++, row[2]);
                statement.setInt(parameter++, Integer.parseInt(row[3]));
                statement.setString(parameter++, row[4]);
                statement.setDate(parameter++, java.sql.Date.valueOf(row[5]));
                statement.setDouble(parameter++, Double.parseDouble(row[6]));
            }
            return statement.executeUpdate();
        }
    }

    public static void main(String[] args) throws Exception {
        List<String[]> rows = new ArrayList<>();
        String id = "MULTI" + System.currentTimeMillis();
        rows.add(new String[]{id + "_1", "STU1", "Sample One", "2",
                "Main Gate", "2026-12-31", "1500.00"});
        rows.add(new String[]{id + "_2", "STU2", "Sample Two", "2",
                "North Stop", "2026-12-31", "1600.00"});
        try (Connection connection = DatabaseConnection.open()) {
            System.out.println("Rows inserted: " + insertMultiRow(connection, rows));
            System.out.println("Prepared JDBC is used; local file loading is disabled.");
        }
    }
}
