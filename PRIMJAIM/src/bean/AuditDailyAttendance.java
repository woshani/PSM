package bean;

public class AuditDailyAttendance {

	private String auditId;
	private StudentClass studentClass;
	private String auditDate;
	private String attendanceDate;
	private String statusBefore;
	private String statusAfter;
	
	public String getAuditId() {
		return auditId;
	}
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
	public StudentClass getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(StudentClass studentClass) {
		this.studentClass = studentClass;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public String getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	public String getStatusBefore() {
		return statusBefore;
	}
	public void setStatusBefore(String statusBefore) {
		this.statusBefore = statusBefore;
	}
	public String getStatusAfter() {
		return statusAfter;
	}
	public void setStatusAfter(String statusAfter) {
		this.statusAfter = statusAfter;
	}
	
	
}
