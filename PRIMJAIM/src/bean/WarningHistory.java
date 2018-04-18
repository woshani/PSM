package bean;

import java.util.Date;

/**
 * @author Mohamad Idzhar
 * 
 * Stored recorded warning history date data from table warning_history.
 * 
 */

public class WarningHistory {
	
	private int whId;
	private Warning warning;
	private Date warningDate;
	

	
	public int getWhId() {
		return whId;
	}
	public void setWhId(int whId) {
		this.whId = whId;
	}
	public Warning getWarning() {
		return warning;
	}
	public void setWarning(Warning warning) {
		this.warning = warning;
	}
	public Date getWarningDate() {
		return warningDate;
	}
	public void setWarningDate(Date warningDate) {
		this.warningDate = warningDate;
	}
}
