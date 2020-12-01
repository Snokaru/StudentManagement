import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EnrollStudentState extends State {
    List<Course> unenrolledCourses;
    Student student;
    
    public EnrollStudentState(AppData appData, Student student) {
        super(appData);
        this.unenrolledCourses = new ArrayList<>();
        this.student = student;
        this.loadUnenrolledCourses();
    }

    private void loadUnenrolledCourses() {
        List<Enrollment> enrollments = appData.getEnrollments();
        List<Course> enrolledCourses = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent() == student) {
                enrolledCourses.add(enrollment.getCourse());
            }
        }
        for (Course course : appData.getCourses()) {
            if (!enrolledCourses.contains(course))
                unenrolledCourses.add(course);
        }
    }

    public void display() {
        System.out.println("********************************************");
        System.out.println("Select Course                               ");
        System.out.println("********************************************");
        int i = 0;
        for (Course course : unenrolledCourses) {
            i++;
            System.out.println(String.valueOf(i) + ") " + course.getCourseName());
        }
        System.out.println("********************************************");
        System.out.println("0) Back");
    }

    public State handleInput(Scanner scanner) throws Exception {
        State nextState = null;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("> ");
            String input = scanner.nextLine();
            int inputInteger = Integer.parseInt(input);
            if (inputInteger >= 1 && inputInteger <= unenrolledCourses.size()) {
                System.out.print("Enter Current Attendance For Student: ");
                int currentAttendance = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter Grades (space separated): ");
                List<Double> grades = Arrays.asList(scanner.nextLine().split(" ")).stream().map(Double::parseDouble).collect(Collectors.toList());
                appData.getEnrollments().add(new Enrollment(unenrolledCourses.get(inputInteger - 1), student, currentAttendance, grades));
                appData.saveEnrollments();
                this.unenrolledCourses.remove(this.unenrolledCourses.get(inputInteger - 1));
                Utils.clearScreenWindows();
                this.display();
                validInput = false;
            } else if (inputInteger == 0) {
                nextState = null;
                validInput = true;
            } else {
                System.out.println("Invalid Input! Try Again!");
                validInput = false;
            }
        }
        return nextState;
    }
}
