public class SqlInjectionRunner {
    public static void main(String[] args) {
        System.out.println("This assignment example is for a local test database only.");
        System.out.println("Unsafe SQL is shown for comparison and is not executed.");
        System.out.println("Secure SQL uses: SELECT 1 FROM users WHERE id = ? AND pwd = ?");
    }
}
