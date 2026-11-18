import java.io.Serializable;
import java.sql.Connection;

public class Resident implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private final String registerNumber;
    private transient String password;
    private transient Connection connection;

    public Resident(String name, String registerNumber, String password) {
        this.name = name;
        this.registerNumber = registerNumber;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public boolean hasPassword() {
        return password != null;
    }

    public boolean hasConnection() {
        return connection != null;
    }

    public static void main(String[] args) {
        Resident sample = new Resident("Saba", "STU100", "not-saved");
        System.out.println(sample.getName() + " (" + sample.getRegisterNumber() + ")");
    }
}
