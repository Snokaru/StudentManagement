import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EnrollmentDetailState extends State {
    private Enrollment enrollment;

    public EnrollmentDetailState(AppData appData, Enrollment enrollment) {
        super(appData);
        this.enrollment = enrollment;
    }

    @Override
    public void display() {
        System.out.println("********************************************");
        System.out.println("            Enrollment Details              ");
        System.out.println("********************************************");
        System.out.println("Student Name: " + enrollment.getStudent().getFirstName() + " " + enrollment.getStudent().getLastName());
        System.out.println("Course Name: " + enrollment.getCourse().getCourseName());
        System.out.println("Current Attendance: " + enrollment.getCurrentAttendance());
        System.out.println("Minimum Attendance: " + enrollment.getMinAttendance());
        System.out.print("Grades: ");
        for (double grade : enrollment.getGrades()) {
            System.out.print(grade + " ");
        }
        System.out.print("\n");
        System.out.println("********************************************");
        System.out.println("1) Change Current Attendance");
        System.out.println("2) Reduce Minimum Attendance (Only For Students With Jobs)");
        System.out.println("3) Compute Final Grade");
        System.out.println("4) Change Grades");
        System.out.println("5) Remove Enrollment");
        System.out.println("********************************************");
        System.out.println("0) Back");
    }

    @Override
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
            } else if (inputInteger == 1) {
                System.out.println("Enter New Attendance: ");
                int newAttendance = Integer.parseInt(scanner.nextLine());
                enrollment.setCurrentAttendance(newAttendance);
                appData.saveEnrollments();
                Utils.clearScreenWindows();
                this.display();
                validInput = false;
            } else if (inputInteger == 2) {
                if (enrollment.getStudent().getHasJob() == false) {
                    System.out.println("Student doesn't have job!");
                    validInput = false;
                } else if (enrollment.getMinAttendance() < enrollment.getCourse().getMinAttendance()) {
                    System.out.println("Student Attendance Has Already Been Reduced!");
                    validInput = false;
                } else {
                    enrollment.reduceMinAttendance();
                    appData.saveEnrollments();
                    Utils.clearScreenWindows();
                    this.display();
                    validInput = false;
                }
            } else if (inputInteger == 3) {
                double finalGrade = 0;
                try {
                    finalGrade = enrollment.computeFinalGrade();
                } catch (Exception e) {
                    e.printStackTrace();
                    validInput = false;
                } finally {
                    System.out.println("Final Grade: " + finalGrade);
                    validInput = false;
                }
            } else if (inputInteger == 4) {
                System.out.println("Enter New Grades (Space Separated): ");
                List<Double> newGrades = Arrays.asList(scanner.nextLine().split(" ")).stream().map(el -> Double.parseDouble(el)).collect(Collectors.toList());
                enrollment.setGrades(newGrades);
                appData.saveEnrollments();
                Utils.clearScreenWindows();
                this.display();
                validInput = false;
            } else if (inputInteger == 5) {
                appData.getEnrollments().remove(enrollment);
                appData.saveEnrollments();
                validInput = true;
            }
        }
        return nextState;
    }
}
