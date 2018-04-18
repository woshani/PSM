package bean;

public class StudentClass {
	
	private String studentClassId;
	private Student student;
	private Classroom classroom;
	private String startDate;
	private String endDate;
	
	public String getStudentClassId() {
		return studentClassId;
	}
	public void setStudentClassId(String studentClassId) {
		this.studentClassId = studentClassId;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Classroom getClassroom() {
		return classroom;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	

}
