import java.util.Scanner;

public class InitialState extends State {

    public InitialState(AppData appData) {
        super(appData);
    }

    public void display() {
        System.out.println("********************************************");
        System.out.println("1) List Students");
        System.out.println("2) Add Student");
        System.out.println("3) List Courses");
        System.out.println("4) Add Course");
        System.out.println("5) List Students With Unpaid Tuition");
        System.out.println("********************************************");
        System.out.println("0) Exit");
    }

    public State handleInput(Scanner scanner) {
        State nextState = null;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("> ");
            String input = scanner.nextLine();
            switch(input) {
                case "0":
                    validInput = true;
                    break;
                case "1":
                    validInput = true;
                    nextState = new ListStudentsState(appData);
                    break;
                case "2":
                    validInput = true;
                    nextState = new AddStudentState(appData);
                    break;
                case "3":
                    validInput = true;
                    nextState = new ListCoursesState(appData);
                    break;
                case "4":
                    validInput = true;
                    nextState = new AddCourseState(appData);
                    break;
                case "5":
                    validInput = true;
                    nextState = new ListStudentsState(appData, s -> s.getTuition() != 0);
                    break;
                default:
                    validInput = false;
                    System.out.println("Invalid Input; Try again!");
            }
        }
        return nextState;
    }
}
