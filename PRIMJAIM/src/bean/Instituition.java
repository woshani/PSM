package bean;

import java.io.Serializable;

public class Instituition implements Serializable {
	/*
	 * Declaration of variables
	 */
	String academic_instituition_id, academic_name, address, postcode, city, 
	telephone_number, fax_number, type, district, ppd;

	public String getAcademic_instituition_id() {
		return academic_instituition_id;
	}

	public void setAcademic_instituition_id(String academic_instituition_id) {
		this.academic_instituition_id = academic_instituition_id;
	}

	public String getAcademic_name() {
		return academic_name;
	}

	public void setAcademic_name(String academic_name) {
		this.academic_name = academic_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone_number() {
		return telephone_number;
	}

	public void setTelephone_number(String telephone_number) {
		this.telephone_number = telephone_number;
	}

	public String getFax_number() {
		return fax_number;
	}

	public void setFax_number(String fax_number) {
		this.fax_number = fax_number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPpd() {
		return ppd;
	}

	public void setPpd(String ppd) {
		this.ppd = ppd;
	}
	
	
}
