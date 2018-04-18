package bean;

import java.io.Serializable;

public class Classroom implements Serializable {
	/*
	 * Declaration of variables
	 */
	String class_id, name, type;
	Instituition instituition;

	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Instituition getInstituition() {
		return instituition;
	}

	public void setInstituition(Instituition instituition) {
		this.instituition = instituition;
	}
}
