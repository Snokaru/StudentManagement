import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddCourseState extends State {
    public AddCourseState(AppData appData) {
        super(appData);
    }

    public void display() {
        System.out.println("********************************************");
    }

    public State handleInput(Scanner scanner) throws Exception {
        State nextState = null;
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter Minimum Attendance: ");
        int minAttendance = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Number Of Grades: ");
        int numberOfGrades = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Grade Weights (space separated): ");
        List<Integer> gradeWeights = Arrays.asList(scanner.nextLine().split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());
        appData.getCourses().add(new Course(courseName, numberOfGrades, minAttendance, gradeWeights));
        appData.saveCourses();
        return nextState;
    }
}
