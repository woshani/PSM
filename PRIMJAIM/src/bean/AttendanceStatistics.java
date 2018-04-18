package bean;

public class AttendanceStatistics {
	
	private String statisticId;
	private Classroom classroom;
	private String attendanceDate;
	private int jumlahHadirL;
	private int jumlahHadirP;
	private int jumlahTidakHadirL;
	private int jumlahTidakHadirP;
	private int jumlahPelajar;
	private double peratusanKedatangan;
	public String getStatisticId() {
		return statisticId;
	}
	public void setStatisticId(String statisticId) {
		this.statisticId = statisticId;
	}
	public Classroom getClassroom() {
		return classroom;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	public String getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	public int getJumlahHadirL() {
		return jumlahHadirL;
	}
	public void setJumlahHadirL(int jumlahHadirL) {
		this.jumlahHadirL = jumlahHadirL;
	}
	public int getJumlahHadirP() {
		return jumlahHadirP;
	}
	public void setJumlahHadirP(int jumlahHadirP) {
		this.jumlahHadirP = jumlahHadirP;
	}
	public int getJumlahTidakHadirL() {
		return jumlahTidakHadirL;
	}
	public void setJumlahTidakHadirL(int jumlahTidakHadirL) {
		this.jumlahTidakHadirL = jumlahTidakHadirL;
	}
	public int getJumlahTidakHadirP() {
		return jumlahTidakHadirP;
	}
	public void setJumlahTidakHadirP(int jumlahTidakHadirP) {
		this.jumlahTidakHadirP = jumlahTidakHadirP;
	}
	public int getJumlahPelajar() {
		return jumlahPelajar;
	}
	public void setJumlahPelajar(int jumlahPelajar) {
		this.jumlahPelajar = jumlahPelajar;
	}
	public double getPeratusanKedatangan() {
		return peratusanKedatangan;
	}
	public void setPeratusanKedatangan(double peratusanKedatangan) {
		this.peratusanKedatangan = peratusanKedatangan;
	}
	
	

}
