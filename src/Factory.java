import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Factory {
    
    // Students Format: <uuid_mostsig>,<uuid_leastsig>,<first_name>,<last_name>,<phone_number>,<age>,<has_job>,<tuition>
    public static List<Student> createStudentsFromFile(String filename) throws Exception {
        List<Student> studentsList = new ArrayList<>();
        CSVFileHandler reader = new CSVFileHandler(filename);
        List<List<String>> CSVContent = reader.readFile();
        for (List<String> line : CSVContent) {
            long uuidMostSigBits = Long.parseLong(line.get(0));
            long uuidLeastSigBits = Long.parseLong(line.get(1));
            UUID id = new UUID(uuidMostSigBits, uuidLeastSigBits);
            String firstName = line.get(2);
            String lastName = line.get(3);
            String phoneNumber = line.get(4);
            int age = Integer.parseInt(line.get(5));
            boolean hasJob = line.get(6).compareTo("true") == 0 ? true : false;
            double tuition = Double.parseDouble(line.get(7));
            studentsList.add(new Student(id, firstName, lastName, phoneNumber, age, hasJob, tuition));
        }
        return studentsList;
    }

    // Courses Format: <uuid_mostsig>,<uuid_leastsig>,<course_name>,<min_grades>,<min_attendance>,<grade_weight_1>,<grade_weight_2>,...
    public static List<Course> createCoursesFromFile(String filename) throws Exception {
        List<Course> coursesList = new ArrayList<>();
        CSVFileHandler reader = new CSVFileHandler(filename);
        List<List<String>> CSVContent = reader.readFile();
        for (List<String> line : CSVContent) {
            long uuidMostSigBits = Long.parseLong(line.get(0));
            long uuidLeastSigBits = Long.parseLong(line.get(1));
            UUID id = new UUID(uuidMostSigBits, uuidLeastSigBits);
            String courseName = line.get(2);
            int minGrades = Integer.parseInt(line.get(3));
            int minAttendance = Integer.parseInt(line.get(4));
            List<Integer> gradeWeights = line.subList(5, line.size()).stream().map(el -> Integer.parseInt(el)).collect(Collectors.toList());
            coursesList.add(new Course(id, courseName, minGrades, minAttendance, gradeWeights));
        }
        return coursesList;
    }

    // Enrollments Format: <uuid_mostsig_course>,<uuid_leastsig_course>,<uuid_mostsig_student>,<uuid_leastsig_student>,<current_attendance>,<min_attendance>,<grade_1>,<grade_2>,...
    public static List<Enrollment> createEnrollmentsFromFile(String filename, List<Course> courses, List<Student> students) throws Exception {
        List<Enrollment> enrollmentsList = new ArrayList<>();
        CSVFileHandler reader = new CSVFileHandler(filename);
        List<List<String>> CSVContent = reader.readFile();
        for (List<String> line : CSVContent) {
            long uuidMostSigBitsCourse = Long.parseLong(line.get(0));
            long uuidLeastSigBitsCourse = Long.parseLong(line.get(1));
            long uuidMostSigBitsStudent = Long.parseLong(line.get(2));
            long uuidLeastSigBitsStudent = Long.parseLong(line.get(3));

            UUID UUIDstudent = new UUID(uuidMostSigBitsStudent, uuidLeastSigBitsStudent);
            UUID UUIDcourse = new UUID(uuidMostSigBitsCourse, uuidLeastSigBitsCourse);
            Course course = courses.stream().filter(c -> c.getId().compareTo(UUIDcourse) == 0).findFirst().orElse(null);
            if (course == null) 
                throw new Exception("Cannot find course id in enrollments!");
            Student student = students.stream().filter(c -> c.getId().compareTo(UUIDstudent) == 0).findFirst().orElse(null);
            if (student == null)
                throw new Exception("Cannot find student id in enrollments!");
            int currentAttendance = Integer.parseInt(line.get(4));
            int minAttendance = Integer.parseInt(line.get(5));
            List<Double> grades = line.subList(6, line.size()).stream().map(el -> Double.parseDouble(el)).collect(Collectors.toList());
            enrollmentsList.add(new Enrollment(course, student, currentAttendance, minAttendance, grades));
        }
        return enrollmentsList;
    }

    // Students Format: <uuid_mostsig>,<uuid_leastsig>,<first_name>,<last_name>,<phone_number>,<age>,<has_job>,<tuition>
    public static void writeStudentsToFile(String filename, List<Student> students) throws Exception {
        CSVFileHandler writer = new CSVFileHandler(filename);
        List<List<String>> csvStudents = new ArrayList<>();
        for (Student student : students) {
            List<String> currentLine = new ArrayList<>();
            currentLine.add(String.valueOf(student.getId().getMostSignificantBits()));
            currentLine.add(String.valueOf(student.getId().getLeastSignificantBits()));
            currentLine.add(student.getFirstName());
            currentLine.add(student.getLastName());
            currentLine.add(student.getPhoneNumber());
            currentLine.add(String.valueOf(student.getAge()));
            currentLine.add(student.isHasJob() ? "true" : "false");
            currentLine.add(String.valueOf(student.getTuition()));

            csvStudents.add(currentLine);
        }
        writer.writeFile(csvStudents);
    }

    // Courses Format: <uuid_mostsig>,<uuid_leastsig>,<course_name>,<min_grades>,<min_attendance>,<grade_weight_1>,<grade_weight_2>,...
    public static void writeCoursesToFile(String filename, List<Course> courses) throws Exception {
        CSVFileHandler writer = new CSVFileHandler(filename);
        List<List<String>> csvCourses = new ArrayList<>();
        for (Course course : courses) {
            List<String> currentLine = new ArrayList<>();
            currentLine.add(String.valueOf(course.getId().getMostSignificantBits()));
            currentLine.add(String.valueOf(course.getId().getLeastSignificantBits()));
            currentLine.add(course.getCourseName());
            currentLine.add(String.valueOf(course.getMinGrades()));
            currentLine.add(String.valueOf(course.getMinAttendance()));

            List<Integer> gradeWeights = course.getGradeWeights();
            for (int grade : gradeWeights)
                currentLine.add(String.valueOf(grade));          
            csvCourses.add(currentLine);
        }
        writer.writeFile(csvCourses);
    }

    // Enrollments Format: <uuid_mostsig_course>,<uuid_leastsig_course>,<uuid_mostsig_student>,<uuid_leastsig_student>,<current_attendance>,<min_attendance>,<grade_1>,<grade_2>,...
    public static void writeEnrollmentsToFile(String filename, List<Enrollment> enrollments) throws Exception {
        CSVFileHandler writer = new CSVFileHandler(filename);
        List<List<String>> csvEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            List<String> currentLine = new ArrayList<>();
            currentLine.add(String.valueOf(enrollment.getCourse().getId().getMostSignificantBits()));
            currentLine.add(String.valueOf(enrollment.getCourse().getId().getLeastSignificantBits()));
            currentLine.add(String.valueOf(enrollment.getStudent().getId().getMostSignificantBits()));
            currentLine.add(String.valueOf(enrollment.getStudent().getId().getLeastSignificantBits()));
            currentLine.add(String.valueOf(enrollment.getCurrentAttendance()));
            currentLine.add(String.valueOf(enrollment.getMinAttendance()));
            
            List<Double> grades = enrollment.getGrades();
            for (double grade : grades)
                currentLine.add(String.valueOf(grade));
            csvEnrollments.add(currentLine);
        }
        writer.writeFile(csvEnrollments);
    }
}
