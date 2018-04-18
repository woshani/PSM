package bean;

public class TeacherKoku {
	private String teacherKokuId;
	private InstituitionKoku instituitionKokuId;
	private Teacher teacherId;
	private String assignDate;
	public String getTeacherKokuId() {
		return teacherKokuId;
	}
	public void setTeacherKokuId(String teacherKokuId) {
		this.teacherKokuId = teacherKokuId;
	}
	public InstituitionKoku getInstituitionKokuId() {
		return instituitionKokuId;
	}
	public void setInstituitionKokuId(InstituitionKoku instituitionKokuId) {
		this.instituitionKokuId = instituitionKokuId;
	}
	public Teacher getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Teacher teacherId) {
		this.teacherId = teacherId;
	}
	public String getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(String assignDate) {
		this.assignDate = assignDate;
	}
	
}
