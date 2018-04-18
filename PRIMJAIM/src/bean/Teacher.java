package bean;

import java.io.Serializable;

public class Teacher implements Serializable {
	/*
	 * Declaration of variables
	 */
	String teacher_id, name, gender, ic_number, marital_status,
	  address, telno_ext, telno_house, telno_hp, position;
	User user;

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIc_number() {
		return ic_number;
	}

	public void setIc_number(String ic_number) {
		this.ic_number = ic_number;
	}

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelno_ext() {
		return telno_ext;
	}

	public void setTelno_ext(String telno_ext) {
		this.telno_ext = telno_ext;
	}

	public String getTelno_house() {
		return telno_house;
	}

	public void setTelno_house(String telno_house) {
		this.telno_house = telno_house;
	}

	public String getTelno_hp() {
		return telno_hp;
	}

	public void setTelno_hp(String telno_hp) {
		this.telno_hp = telno_hp;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
