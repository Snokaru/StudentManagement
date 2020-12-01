import java.util.Scanner;

public class AddStudentState extends State {
    public AddStudentState(AppData appData) {
        super(appData);
    }

    public void display() {
        System.out.println("********************************************");
    }

    public State handleInput(Scanner scanner) throws Exception {
        State nextState = null;
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Does the student have a job(yes/no)? ");
        boolean hasJob = scanner.nextLine().compareTo("yes") == 0 ? true : false;
        System.out.print("Student tuition: ");
        double tuition = Double.parseDouble(scanner.nextLine());
        appData.getStudents().add(new Student(firstName, lastName, phoneNumber, age, hasJob, tuition));
        appData.saveStudents();
        return nextState;
    }
}
