import java.util.ArrayList;
import java.util.List;

public class MigrationReport {
    private int rowsRead;
    private int rowsAccepted;
    private int rowsRejected;
    private final List<String> problems = new ArrayList<>();

    public void addAccepted() {
        rowsRead++;
        rowsAccepted++;
    }

    public void addRejected(int lineNumber, String reason) {
        rowsRead++;
        rowsRejected++;
        problems.add("Line " + lineNumber + ": " + reason);
    }

    public void print() {
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows accepted: " + rowsAccepted);
        System.out.println("Rows rejected: " + rowsRejected);
        for (String problem : problems) {
            System.out.println(problem);
        }
    }

    public static void main(String[] args) {
        MigrationReport report = new MigrationReport();
        report.addAccepted();
        report.addRejected(2, "Example validation error");
        report.print();
    }
}
