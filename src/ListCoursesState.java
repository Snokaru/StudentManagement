import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListCoursesState extends State {
    List<Course> courses;
    Predicate<Course> p;

    public ListCoursesState(AppData appData) {
        super(appData);
    }

    public ListCoursesState(AppData appData, Predicate<Course> p) {
        super(appData);
        this.p = p;
    }

    private void loadCourses() {
        if (p != null)
            courses = appData.getCourses().stream().filter(p).collect(Collectors.toList());
        else
            courses = appData.getCourses();
    }

    public void display() {
        this.loadCourses();
        System.out.println("********************************************");
        int i = 0;
        for (Course course : courses) {
            i++;
            System.out.println(String.valueOf(i) + ") " + course.getCourseName());
        }
        System.out.println("********************************************");
        System.out.println("0) Back");
    }

    public State handleInput(Scanner scanner) {
        State nextState = null;
        boolean validInput = false;
        while (!validInput) {
            validInput = true;
            System.out.print("> ");
            String input = scanner.nextLine();
            int inputInteger = Integer.parseInt(input);
            if (input.compareTo("0") == 0) {
                nextState = null;
            } else if (inputInteger >= 1 && inputInteger <= courses.size()) {
                nextState = new CourseDetailState(appData, courses.get(inputInteger - 1));
            } else {
                validInput = false;
                System.out.println("Invalid Input; Try again!");
            }
        }
        return nextState;
    }
}
