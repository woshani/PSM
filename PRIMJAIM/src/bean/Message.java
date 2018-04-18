package bean;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	/*
	 * Declaration of variables
	 */
	String message_id, receiver, sender, msgText, type, receiverId;
	Date receivedDate;
	Guardian guardian;
	
	public String getMessage_id() {
		return message_id;
	}
	
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	
	public String getReceiver() {
		return receiver;
	}
	
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getMsgText() {
		return msgText;
	}
	
	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}
	
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Guardian getGuardian() {
		return guardian;
	}

	public void setGuardian(Guardian guardian) {
		this.guardian = guardian;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	
	
}
