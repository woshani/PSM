package bean;

public class StudentKoku {
	private String studentKokuId;
	private InstituitionKoku instituitionKokuId;
	private Student studentId;
	private String registerDate;
	
	public String getStudentKokuId() {
		return studentKokuId;
	}
	public void setStudentKokuId(String studentKokuId) {
		this.studentKokuId = studentKokuId;
	}
	public InstituitionKoku getInstituitionKokuId() {
		return instituitionKokuId;
	}
	public void setInstituitionKokuId(InstituitionKoku instituitionKokuId) {
		this.instituitionKokuId = instituitionKokuId;
	}
	public Student getStudentId() {
		return studentId;
	}
	public void setStudentId(Student studentId) {
		this.studentId = studentId;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
	
}
