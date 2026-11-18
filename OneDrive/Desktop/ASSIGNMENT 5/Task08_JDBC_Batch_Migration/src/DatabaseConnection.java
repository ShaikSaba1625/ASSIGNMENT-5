import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private DatabaseConnection() {
    }

    public static Connection open() throws IOException, SQLException {
        Path propertiesFile = Path.of("config", "db.properties");
        if (!Files.isRegularFile(propertiesFile)) {
            throw new IOException("Create config/db.properties from the example file first.");
        }
        Properties properties = new Properties();
        try (InputStream input = Files.newInputStream(propertiesFile)) {
            properties.load(input);
        }
        String url = properties.getProperty("url", "");
        if (!url.equals("jdbc:mysql://127.0.0.1:3306/transport_db")) {
            throw new IllegalArgumentException("Only the local transport_db database is allowed.");
        }
        return DriverManager.getConnection(url, properties.getProperty("user"),
                properties.getProperty("password"));
    }

    public static void main(String[] args) throws Exception {
        try (Connection connection = open()) {
            System.out.println("Connected to " + connection.getCatalog());
        }
    }
}
