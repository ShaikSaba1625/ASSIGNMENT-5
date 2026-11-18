import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseMetaDataPrinter {
    public static void print(Connection connection) throws SQLException {
        DatabaseMetaData metadata = connection.getMetaData();
        System.out.println("Database: " + metadata.getDatabaseProductName());
        System.out.println("Version: " + metadata.getDatabaseProductVersion());
        System.out.println("JDBC driver: " + metadata.getDriverName());
        try (ResultSet tables = metadata.getTables(connection.getCatalog(), null,
                "%", new String[]{"TABLE"})) {
            while (tables.next()) {
                System.out.println("Table: " + tables.getString("TABLE_NAME"));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        try (Connection connection = DatabaseConnection.open()) {
            print(connection);
        }
    }
}
