package bean;

//store guide data for warning process
public class Warning {
	
	//store level warning name
	private String description;
	
	//total day absent
	private int absentDay;
	
	//continuous or non-continous absent type
	private String warningType;
	
	private int warningLevel;
	
	

	public int getWarningLevel() {
		return warningLevel;
	}

	public void setWarningLevel(int warningLevel) {
		this.warningLevel = warningLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the absentDay
	 */
	public int getAbsentDay() {
		return absentDay;
	}

	/**
	 * @param absentDay the absentDay to set
	 */
	public void setAbsentDay(int absentDay) {
		this.absentDay = absentDay;
	}

	/**
	 * @return the warningType
	 */
	public String getWarningType() {
		return warningType;
	}

	/**
	 * @param warningType the warningType to set
	 */
	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}
		
	
}
