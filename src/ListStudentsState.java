import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListStudentsState extends State {
    Predicate<Student> p;
    List<Student> students;

    public ListStudentsState(AppData appData) {
        super(appData);
    }

    public ListStudentsState(AppData appData, Predicate<Student> p) {
        this(appData);
        this.p = p;
    }

    private void loadStudents() {
        if (p != null)
            students = appData.getStudents().stream().filter(p).collect(Collectors.toList());
        else
            students = appData.getStudents();
    }

    public void display() {
        this.loadStudents();
        System.out.println("********************************************");
        int i = 0;
        for (Student student : students) {
            i++;
            System.out.println(String.valueOf(i) + ") " + student.getFirstName() + " " + student.getLastName());
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
            } else if (inputInteger >= 1 && inputInteger <= students.size()) {
                nextState = new StudentDetailState(appData, students.get(inputInteger - 1));
            } else {
                validInput = false;
                System.out.println("Invalid Input; Try again!");
            }
        }
        return nextState;
    }
}
