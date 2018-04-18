package bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class SubscriptionHistory implements Serializable {
	/*
	 * Declaration of variables
	 */
	String shistory_id, details, remark;
	Guardian guardian;
	Timestamp datetime;
	
	public String getShistory_id() {
		return shistory_id;
	}
	
	public void setShistory_id(String shistory_id) {
		this.shistory_id = shistory_id;
	}
	
	public Guardian getGuardian() {
		return guardian;
	}
	
	public void setGuardian(Guardian guardian) {
		this.guardian = guardian;
	}
	
	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Timestamp getDatetime() {
		return datetime;
	}
	
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}
}
