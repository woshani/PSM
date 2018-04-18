package bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class OutgoingSMS implements Serializable {
	/*
	 * Declaration of variables
	 */
	String osms_id, status, eventDate;
	Student student;
	AlertPackage alertPackage;
	Message message;
	Timestamp datetime;
	
	public String getOsms_id() {
		return osms_id;
	}
	
	public void setOsms_id(String osms_id) {
		this.osms_id = osms_id;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public AlertPackage getAlertPackage() {
		return alertPackage;
	}
	
	public void setAlertPackage(AlertPackage alertPackage) {
		this.alertPackage = alertPackage;
	}
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Timestamp getDatetime() {
		return datetime;
	}
	
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	
}
