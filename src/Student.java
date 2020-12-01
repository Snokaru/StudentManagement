import java.util.UUID;

public class Student {

	private UUID         id;
    private String       firstName, lastName, phoneNumber;
    private int          age;
    private boolean      hasJob;
    private double       tuition;


    public Student() {
    }

    public Student(UUID id, String firstName, String lastName, String phoneNumber, int age, boolean hasJob, double tuition) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.hasJob = hasJob;
        this.tuition = tuition;
    }

    public Student(String firstName, String lastName, String phoneNumber, int age, boolean hasJob, double tuition) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.hasJob = hasJob;
        this.tuition = tuition;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isHasJob() {
        return this.hasJob;
    }

    public boolean getHasJob() {
        return this.hasJob;
    }

    public void setHasJob(boolean hasJob) {
        this.hasJob = hasJob;
    }

    public double getTuition() {
        return this.tuition;
    }

    public void setTuition(int tuition) {
        this.tuition = tuition;
    }
    
    @Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", age=" + age + ", hasJob=" + hasJob + ", tuition=" + tuition + "]";
	}
}