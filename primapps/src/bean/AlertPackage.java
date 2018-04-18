package bean;

import java.io.Serializable;

public class AlertPackage implements Serializable {
	/*
	 * Declaration of variables
	 */
	String package_id, code, name;
	
	public String getPackage_id() {
		return package_id;
	}
	
	public void setPackage_id(String package_id) {
		this.package_id = package_id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
