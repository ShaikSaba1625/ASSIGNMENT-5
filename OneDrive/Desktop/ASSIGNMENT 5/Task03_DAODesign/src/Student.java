public class Student {
    private int studentId;
    private String name;
    private String email;
    private String department;

    public Student(int studentId, String name, String email, String department) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.department = department;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return studentId + " - " + name + " (" + department + ")";
    }

    public static void main(String[] args) {
        Student sample = new Student(1, "Saba", "saba@example.com", "CSE");
        System.out.println(sample);
    }
}
