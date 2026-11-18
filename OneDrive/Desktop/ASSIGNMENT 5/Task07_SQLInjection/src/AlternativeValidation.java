public class AlternativeValidation {
    public static boolean isValidUserId(String userId) {
        return userId != null && userId.matches("[A-Za-z0-9_]{1,32}");
    }

    public static String escapeForDisplay(String value) {
        return value.replace("'", "''");
    }

    public static void main(String[] args) {
        String value = args.length == 0 ? "student_1" : args[0];
        System.out.println("Allowed identifier: " + isValidUserId(value));
        System.out.println("Quote-escaped display value: " + escapeForDisplay(value));
        System.out.println("Escaping is not a replacement for prepared statements.");
    }
}
