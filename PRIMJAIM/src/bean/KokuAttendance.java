package bean;

public class KokuAttendance {
	private String attendanceId;
	private Student studentId;
	private Meeting meetingId;
	
	public String getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}
	public Student getStudentId() {
		return studentId;
	}
	public void setStudentId(Student studentId) {
		this.studentId = studentId;
	}
	public Meeting getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(Meeting meetingId) {
		this.meetingId = meetingId;
	}
	
	
}
