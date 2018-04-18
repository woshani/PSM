package bean;

/**
 * @author Imran Ibrahim
 * 
 * Alter by Mohamad Idzhar on (18/11/2016)
 * 
 * Stored recorded monitor data from table monitor.
 * 
 * 1) Year of absent (date).
 * 2) Current warning status.
 * 3) Current counter absent row.
 * 4) Current counter absent no row.
 * 
 */

//dateAbsent may change to format string for improving performance.
//warning also can be change into string for improving performance.
public class Monitor {

	private Student student;
	private DateAbsent dateAbsent;
	private Warning warning;
	private int absentRow;
	private int absentNoRow;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public DateAbsent getDateAbsent() {
		return dateAbsent;
	}
	public void setDateAbsent(DateAbsent dateAbsent) {
		this.dateAbsent = dateAbsent;
	}
	public Warning getWarning() {
		return warning;
	}
	public void setWarning(Warning warning) {
		this.warning = warning;
	}
	public int getAbsentRow() {
		return absentRow;
	}
	public void setAbsentRow(int absentRow) {
		this.absentRow = absentRow;
	}
	public int getAbsentNoRow() {
		return absentNoRow;
	}
	public void setAbsentNoRow(int absentNoRow) {
		this.absentNoRow = absentNoRow;
	}
	
}
