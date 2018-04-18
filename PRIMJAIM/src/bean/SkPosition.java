package bean;

public class SkPosition {

	private String sk_PositionId;
	private StudentKoku studentKoku;
	private Position position;
	private String position_date;
	
	public String getSk_PositionId() {
		return sk_PositionId;
	}
	public void setSk_PositionId(String sk_PositionId) {
		this.sk_PositionId = sk_PositionId;
	}
	public StudentKoku getStudentKoku() {
		return studentKoku;
	}
	public void setStudentKoku(StudentKoku studentKoku) {
		this.studentKoku = studentKoku;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public String getPosition_date() {
		return position_date;
	}
	public void setPosition_date(String position_date) {
		this.position_date = position_date;
	}
	
	
}
