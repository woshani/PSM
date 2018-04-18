package bean;

public class AnalysisYearAttendance {

	private int analysis_year_attendance_id;
	private Classroom classroom;
	private String month_year;
	private int no_of_student;
	private String attendance;	
	private String attendance_percentage;
	private int total_day;
	
	
	
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public int getAnalysis_year_attendance_id() {
		return analysis_year_attendance_id;
	}
	public void setAnalysis_year_attendance_id(int analysis_year_attendance_id) {
		this.analysis_year_attendance_id = analysis_year_attendance_id;
	}
	public Classroom getClassroom() {
		return classroom;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	public String getMonth_year() {
		return month_year;
	}
	public void setMonth_year(String month_year) {
		this.month_year = month_year;
	}
	public int getNo_of_student() {
		return no_of_student;
	}
	public void setNo_of_student(int no_of_student) {
		this.no_of_student = no_of_student;
	}
	public String getAttendance_percentage() {
		return attendance_percentage;
	}
	public void setAttendance_percentage(String attendance_percentage) {
		this.attendance_percentage = attendance_percentage;
	}
	public int getTotal_day() {
		return total_day;
	}
	public void setTotal_day(int total_day) {
		this.total_day = total_day;
	}
	
	
}
