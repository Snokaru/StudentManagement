import java.util.List;

public class Enrollment {

	private Course          course;
    private Student         student;
    private int             currentAttendance;
    private int             minAttendance;
    private List<Double>    grades;

    public Enrollment(Course course, Student student, int currentAttendance, int minAttendance, List<Double> grades) {
        this.course = course;
        this.student = student;
        this.currentAttendance = currentAttendance;
        this.minAttendance = minAttendance;
        this.grades = grades;
    }   

    public Enrollment(Course course, Student student, int currentAttendance, List<Double> grades) {
        this.course = course;
        this.student = student;
        this.currentAttendance = currentAttendance;
        this.minAttendance = course.getMinAttendance();
        this.grades = grades;
    }   
    
    public double computeFinalGrade() throws Exception {
        List<Integer> gradeWeights = course.getGradeWeights();
        double finalGrade = 0;
        if (gradeWeights.size() != grades.size()) 
            throw new Exception("Not Enough Grades Have Been Assigned!");
        for (int i = 0; i < gradeWeights.size(); i++) {
            finalGrade += (gradeWeights.get(i) / 100.0) * grades.get(i);
        }
        return finalGrade;
    }

    public Enrollment() {
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getCurrentAttendance() {
        return this.currentAttendance;
    }

    public void setCurrentAttendance(int currentAttendance) {
        this.currentAttendance = currentAttendance;
    }

    public int getMinAttendance() {
        return this.minAttendance;
    }

    public void setMinAttendance(int minAttendance) {
        this.minAttendance = minAttendance;
    }

    public List<Double> getGrades() {
        return this.grades;
    }

    public void setGrades(List<Double> grades) {
        this.grades = grades;
    }
    
    @Override
	public String toString() {
		return "Enrollment [course=" + course + ", student=" + student + ", currentAttendance=" + currentAttendance
				+ ", minAttendance=" + minAttendance + ", grades=" + grades + "]";
    }
    
    public void reduceMinAttendance() {
        this.minAttendance = (int) (this.minAttendance - 25.0/100 * this.minAttendance);
    }
}



