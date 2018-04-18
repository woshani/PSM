package bean;

import java.util.Comparator;

public class PpdAttendance {
	
	private Instituition instituition;
	private int totalFinish;
	private int totalClass;
	private int finishPercentage;
	private int totalAttend;
	private int totalAbsent;
	private int totalStudent;
	private int attendancePercentage;
	public Instituition getInstituition() {
		return instituition;
	}
	public void setInstituition(Instituition instituition) {
		this.instituition = instituition;
	}
	public int getTotalFinish() {
		return totalFinish;
	}
	public void setTotalFinish(int totalFinish) {
		this.totalFinish = totalFinish;
	}
	public int getTotalClass() {
		return totalClass;
	}
	public void setTotalClass(int totalClass) {
		this.totalClass = totalClass;
	}
	public int getFinishPercentage() {
		return finishPercentage;
	}
	public void setFinishPercentage(int finishPercentage) {
		this.finishPercentage = finishPercentage;
	}
	public int getTotalAttend() {
		return totalAttend;
	}
	public void setTotalAttend(int totalAttend) {
		this.totalAttend = totalAttend;
	}
	public int getTotalAbsent() {
		return totalAbsent;
	}
	public void setTotalAbsent(int totalAbsent) {
		this.totalAbsent = totalAbsent;
	}
	public int getTotalStudent() {
		return totalStudent;
	}
	public void setTotalStudent(int totalStudent) {
		this.totalStudent = totalStudent;
	}
	public int getAttendancePercentage() {
		return attendancePercentage;
	}
	public void setAttendancePercentage(int attendancePercentage) {
		this.attendancePercentage = attendancePercentage;
	}
	
	public static Comparator<PpdAttendance> FinishPercentageComparator = new Comparator<PpdAttendance>() {

	    @Override
	    public int compare(PpdAttendance e1, PpdAttendance e2) {
	        return (int) (e1.getFinishPercentage() - e2.getFinishPercentage());
	    }
	};
	
	public static Comparator<PpdAttendance> AttendancePercentageComparator = new Comparator<PpdAttendance>() {

	    @Override
	    public int compare(PpdAttendance e1, PpdAttendance e2) {
	        return (int) (e1.getAttendancePercentage() - e2.getAttendancePercentage());
	    }
	};
}

