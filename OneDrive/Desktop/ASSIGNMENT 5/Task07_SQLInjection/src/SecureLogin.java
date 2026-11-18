import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SecureLogin {
    public static boolean authenticate(Connection connection, String userId,
                                        String password) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE id = ? AND pwd = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            statement.setString(2, password);
            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Secure login binds user values through PreparedStatement.");
        System.out.println("Configure a local test database to run authentication.");
    }
}
