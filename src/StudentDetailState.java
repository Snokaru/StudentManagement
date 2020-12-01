import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentDetailState extends State {
    private Student student;
    private List<Enrollment> enrollments;

    public StudentDetailState(AppData appData, Student student) {
        super(appData);
        this.student = student;
    }

    private void loadEnrollmentsData() {
        enrollments = new ArrayList<>();
        for (Enrollment enrollment : appData.getEnrollments()) {
            if (enrollment.getStudent() == student)
                enrollments.add(enrollment);
        }
    }
    public void display() {
        this.loadEnrollmentsData();
        System.out.println("********************************************");
        System.out.println("               Student Details              ");
        System.out.println("********************************************");
        System.out.println("ID: " + student.getId());
        System.out.println("First Name: " + student.getFirstName());
        System.out.println("Last Name: " + student.getLastName());
        System.out.println("Phone Number: " + student.getPhoneNumber());
        System.out.println("Age: " + String.valueOf(student.getAge()));
        System.out.println("Tuition: " + String.valueOf(student.getTuition()));
        System.out.println("Has Job: " + (student.getHasJob() ? "yes" : "no"));
        System.out.println("Courses Taken: ");
        int i = 0;
        for (Enrollment enrollment : enrollments) {
            i++;
            System.out.println(String.valueOf(i) + ") " + enrollment.getCourse().getCourseName());
        }
        System.out.println("********************************************");
        System.out.println(String.valueOf(++i) + ") " + "Change First Name");
        System.out.println(String.valueOf(++i) + ") " + "Change Last Name");
        System.out.println(String.valueOf(++i) + ") " + "Change Phone Number");
        System.out.println(String.valueOf(++i) + ") " + "Change Age");
        System.out.println(String.valueOf(++i) + ") " + "Change Tuition");
        System.out.println(String.valueOf(++i) + ") " + "Add Course");
        System.out.println(String.valueOf(++i) + ") " + "Remove Student");
        System.out.println("********************************************");
        System.out.println("0) Back");
    }

    public State handleInput(Scanner scanner) throws Exception {
        State nextState = null;
        boolean validInput = false;
        while (!validInput) {
            validInput = true;
            System.out.print("> ");
            String input = scanner.nextLine();
            int inputInteger = Integer.parseInt(input);
            if (inputInteger == 0) {
                nextState = null;
            } else if (inputInteger >= 1 && inputInteger <= enrollments.size()) {
               nextState = new EnrollmentDetailState(appData, enrollments.get(inputInteger - 1));
            } else if (inputInteger == enrollments.size() + 1) {
                System.out.println("Enter New First Name: ");
                String newFirstName = scanner.nextLine();
                student.setFirstName(newFirstName);
                appData.saveStudents();
                Utils.clearScreenWindows();
                this.display();
                validInput = false;
            } else if (inputInteger == enrollments.size() + 2) {
                System.out.println("Enter New Last Name: ");
                String newLastName = scanner.nextLine();
                student.setLastName(newLastName);
                appData.saveStudents();
                Utils.clearScreenWindows();
                this.display();
                validInput = false;
            } else if (inputInteger == enrollments.size() + 3) {
                System.out.println("Enter New Phone Number: ");
                String newPhoneNumber = scanner.nextLine();
                student.setPhoneNumber(newPhoneNumber);
                appData.saveStudents();
                Utils.clearScreenWindows();
                this.display();
                validInput = false;
            } else if (inputInteger == enrollments.size() + 4) {
                System.out.println("Enter New Phone Number: ");
                int newAge = Integer.parseInt(scanner.nextLine());
                student.setAge(newAge);
                appData.saveStudents();
                Utils.clearScreenWindows();
                this.display();
                validInput = false;
            } else if (inputInteger == enrollments.size() + 5) {
                System.out.println("Enter New Tuition: ");
                int newTuition = Integer.parseInt(scanner.nextLine());
                student.setTuition(newTuition);
                appData.saveStudents();
                Utils.clearScreenWindows();
                this.display();
                validInput = false;
            } else if (inputInteger == enrollments.size() + 6) {
                nextState = new EnrollStudentState(appData, student);
                validInput = true;
            } else if (inputInteger == enrollments.size() + 7) {
                appData.getStudents().remove(student);
                appData.saveStudents();
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
