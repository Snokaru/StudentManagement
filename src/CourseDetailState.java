import java.util.Scanner;

public class CourseDetailState extends State {
    private Course course;

    public CourseDetailState(AppData appData, Course course) {
        super(appData);
        this.course = course;
    }

    public void display() {
        System.out.println("********************************************");
        System.out.println("               Course Details               ");
        System.out.println("********************************************");
        System.out.println("ID: " + course.getId());
        System.out.println("Course Name: " + course.getCourseName());
        System.out.println("Minimum Attendance: " + String.valueOf(course.getMinAttendance()));
        System.out.println("Number of Grades: " + String.valueOf(course.getMinGrades()));
        System.out.print("Grade Weights: ");
        for (int gradeWeight : course.getGradeWeights()) {
            System.out.print(gradeWeight + " ");
        }
        System.out.print("\n");
        System.out.println("********************************************");
        System.out.println("1) List Students That Have Taken This Course");
        System.out.println("2) Compute Average Attendance");
        System.out.println("3) List Students With Low Attendance");
        System.out.println("4) List Students That Have Failed The Course");
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
            } else if (inputInteger == 1) {
                nextState = new ListStudentsState(appData, (Student s) -> {
                    for (Enrollment e : appData.getEnrollments()) {
                        if (e.getStudent() == s && e.getCourse() == course)
                            return true;
                    }
                    return false;
                });
                validInput = true;
            } else if (inputInteger == 2) {
                validInput = false;
                int mediaPrezente = appData.getEnrollments().stream().filter(e -> e.getCourse() == course).map(e -> e.getCurrentAttendance()).reduce(0, (subtotal, element) -> subtotal + element);
                System.out.println("Average Attendance: " + String.valueOf(mediaPrezente));
            } else if (inputInteger == 3) {
                nextState = new ListStudentsState(appData, s -> {
                    Enrollment e = null;
                    for (Enrollment enrollment : appData.getEnrollments()) {
                        if (enrollment.getCourse() == course && enrollment.getStudent() == s) {
                            e = enrollment;
                            break;
                        }
                    }
                    if (e == null)
                        return false;
                    else
                    return e.getCurrentAttendance() < e.getMinAttendance();
                });
            } else if (inputInteger == 4) {
                nextState = new ListStudentsState(appData, s -> {
                    Enrollment e = null;
                    for (Enrollment enrollment : appData.getEnrollments()) {
                        if (enrollment.getCourse() == course && enrollment.getStudent() == s) {
                            e = enrollment;
                            break;
                        }
                    }
                    if (e == null)
                        return false;
                    else
                    try {
                        return e.computeFinalGrade() < 5;
                    } catch (Exception excep) {
                        return false;
                    }
                });
            } else {
                System.out.println("Invalid Input! Try Again!");
                validInput = false;
            }

        }
        return nextState;
    }
}
