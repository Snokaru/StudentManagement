import java.util.List;

public class AppData {
    private final String studentsFilename = "students.csv";
    private final String coursesFilename = "courses.csv";
    private final String enrollmentsFilename = "enrollments.csv";
    private List<Student> students;
    private List<Course> courses;
    private List<Enrollment> enrollments;

    public AppData() throws Exception {
        students = Factory.createStudentsFromFile(studentsFilename);
        courses = Factory.createCoursesFromFile(coursesFilename);
        enrollments = Factory.createEnrollmentsFromFile(enrollmentsFilename, courses, students);
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void saveStudents() throws Exception {
        Factory.writeStudentsToFile(studentsFilename, students);
    }

    public void saveCourses() throws Exception {
        Factory.writeCoursesToFile(coursesFilename, courses);
    }

    public void saveEnrollments() throws Exception {
        Factory.writeEnrollmentsToFile(enrollmentsFilename, enrollments);
    }
}
