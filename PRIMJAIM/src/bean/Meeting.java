package bean;

public class Meeting {
	private String meetingId;
	private Koku kokuId;
	private String meetingDate;
	private String meetingTime;
	private String meetingDetails;
	public String getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	public Koku getKokuId() {
		return kokuId;
	}
	public void setKokuId(Koku kokuId) {
		this.kokuId = kokuId;
	}
	public String getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}
	public String getMeetingTime() {
		return meetingTime;
	}
	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}
	public String getMeetingDetails() {
		return meetingDetails;
	}
	public void setMeetingDetails(String meetingDetails) {
		this.meetingDetails = meetingDetails;
	}
	
	
}
