package bean;

public class PushNoti {
	private String token;
	private String matric;
	private NotificationMessage msj;
	private String sender;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMatric() {
		return matric;
	}
	public void setMatric(String matric) {
		this.matric = matric;
	}
	public NotificationMessage getMsj() {
		return msj;
	}
	public void setMsj(NotificationMessage msj) {
		this.msj = msj;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	
}
