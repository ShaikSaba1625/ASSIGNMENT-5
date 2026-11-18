public class UnsafeLogin {
    public static String buildQuery(String userId, String password) {
        return "SELECT * FROM users WHERE id = '" + userId
                + "' AND pwd = '" + password + "'";
    }

    public static void main(String[] args) {
        System.out.println("Unsafe concatenation example (do not execute):");
        System.out.println(buildQuery("sample-user", "sample-password"));
        System.out.println("Use SecureLogin for database access.");
    }
}
