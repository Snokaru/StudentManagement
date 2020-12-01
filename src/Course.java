import java.util.List;
import java.util.UUID;

public class Course {

	private UUID            id;
    private String          courseName;
    private int             minGrades, minAttendance;
    private List<Integer>   gradeWeights;

    public Course(UUID id, String courseName, int minGrades, int minAttendance, List<Integer> gradeWeights) {
        this.id = id;
        this.courseName = courseName;
        this.minGrades = minGrades;
        this.minAttendance = minAttendance;
        this.gradeWeights = gradeWeights;
    }

    public Course(String courseName, int minGrades, int minAttendance, List<Integer> gradeWeights) {
        this.id = UUID.randomUUID();
        this.courseName = courseName;
        this.minGrades = minGrades;
        this.minAttendance = minAttendance;
        this.gradeWeights = gradeWeights;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getMinGrades() {
        return this.minGrades;
    }

    public void setMinGrades(int minGrades) {
        this.minGrades = minGrades;
    }

    public int getMinAttendance() {
        return this.minAttendance;
    }

    public void setMinAttendance(int minAttendance) {
        this.minAttendance = minAttendance;
    }

    public List<Integer> getGradeWeights() {
        return gradeWeights;
    }

    public void setGradeWeights(List<Integer> gradeWeights) {
        this.gradeWeights = gradeWeights;
    }
    
    @Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", minGrades=" + minGrades + ", minAttendance="
				+ minAttendance + "]";
	}

}
