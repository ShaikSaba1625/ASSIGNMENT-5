import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SerializationDemo {
    private static class Hostel implements Serializable {
        private static final long serialVersionUID = 1L;
        private final ArrayList<Room> rooms = new ArrayList<>();
    }

    private static class Room implements Serializable {
        private static final long serialVersionUID = 1L;
        private final ArrayList<Resident> residents = new ArrayList<>();
    }

    public static void main(String[] args) throws Exception {
        Hostel hostel = new Hostel();
        Room room = new Room();
        room.residents.add(new Resident("Saba", "STU100", "not-saved"));
        hostel.rooms.add(room);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream output = new ObjectOutputStream(bytes)) {
            output.writeObject(hostel);
        }

        Hostel restored;
        try (ObjectInputStream input =
                     new ObjectInputStream(new ByteArrayInputStream(bytes.toByteArray()))) {
            restored = (Hostel) input.readObject();
        }

        Resident resident = restored.rooms.get(0).residents.get(0);
        System.out.println("Restored resident: " + resident.getName());
        System.out.println("Password restored: " + resident.hasPassword());
        System.out.println("Connection restored: " + resident.hasConnection());
    }
}
