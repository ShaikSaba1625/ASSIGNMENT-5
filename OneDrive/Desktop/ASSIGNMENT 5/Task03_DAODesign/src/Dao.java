import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T, K> {
    T insert(T item) throws SQLException;

    Optional<T> findById(K id) throws SQLException;

    List<T> findAll() throws SQLException;

    boolean update(T item) throws SQLException;

    boolean delete(K id) throws SQLException;

    public static void main(String[] args) {
        System.out.println("Dao<T, K> describes common database operations.");
    }
}
