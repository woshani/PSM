package bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Announcement implements Serializable {
	/*
	 * Declaration of variables
	 */
	String announcement_id, subject, text, type, status;
	User announcer;
	Object target;
	Timestamp datetime;
	String name;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getAnnouncement_id() {
		return announcement_id;
	}
	
	public void setAnnouncement_id(String announcement_id) {
		this.announcement_id = announcement_id;
	}
	
	public User getAnnouncer() {
		return announcer;
	}
	
	public void setAnnouncer(User announcer) {
		this.announcer = announcer;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Object getTarget() {
		return target;
	}
	
	public void setTarget(Object target) {
		this.target = target;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
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
}
