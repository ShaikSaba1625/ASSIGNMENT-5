import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDao implements Dao<Student, Integer>, AutoCloseable {
    private final Connection connection;

    public StudentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Student insert(Student student) throws SQLException {
        String sql = "INSERT INTO student (student_id, name, email, department) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, student.getStudentId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getDepartment());
            statement.executeUpdate();
            return student;
        }
    }

    @Override
    public Optional<Student> findById(Integer id) throws SQLException {
        String sql = "SELECT student_id, name, email, department FROM student WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return Optional.of(readStudent(result));
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Student> findAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT student_id, name, email, department FROM student";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                students.add(readStudent(result));
            }
        }
        return students;
    }

    @Override
    public boolean update(Student student) throws SQLException {
        String sql = "UPDATE student SET name = ?, email = ?, department = ? WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getDepartment());
            statement.setInt(4, student.getStudentId());
            return statement.executeUpdate() == 1;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement("DELETE FROM student WHERE student_id = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate() == 1;
        }
    }

    private Student readStudent(ResultSet result) throws SQLException {
        return new Student(result.getInt("student_id"), result.getString("name"),
                result.getString("email"), result.getString("department"));
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    public static void main(String[] args) throws SQLException {
        String url = System.getenv().getOrDefault("STUDENT_DB_URL",
                "jdbc:mysql://127.0.0.1:3306/student_db");
        try (Connection connection = DriverManager.getConnection(url);
             StudentDao dao = new StudentDao(connection)) {
            System.out.println("Student count: " + dao.findAll().size());
        } catch (SQLException exception) {
            System.err.println("Could not connect to the configured student database: "
                    + exception.getMessage());
            throw exception;
        }
    }
}
