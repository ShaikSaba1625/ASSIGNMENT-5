import java.sql.Connection;

public class DatabaseInfo {
    public static void main(String[] args) throws Exception {
        try (Connection connection = DatabaseConnection.open()) {
            DatabaseMetaDataPrinter.print(connection);
        }
    }
}
