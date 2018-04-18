package bean;

import java.sql.Date;

public class Student {
	/*
	 * Declaration of variables
	 */
	String student_id, name, ic_number, birth_cert, gender,
		place_of_birth;
	Race race;
	Religion religion;
	String date_of_birth;
	Classroom classroom;
	Date dob;
	byte[] picture;
	
	//for speed performance list purposes
	String classname;
	String classType;
	
	
	//for speed performance list purposes
	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
	

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getStudent_id() {
		return student_id;
	}
	
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getIc_number() {
		return ic_number;
	}

	public void setIc_number(String ic_number) {
		this.ic_number = ic_number;
	}

	public String getBirth_cert() {
		return birth_cert;
	}

	public void setBirth_cert(String birth_cert) {
		this.birth_cert = birth_cert;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public Religion getReligion() {
		return religion;
	}

	public void setReligion(Religion religion) {
		this.religion = religion;
	}

	public String getPlace_of_birth() {
		return place_of_birth;
	}

	public void setPlace_of_birth(String place_of_birth) {
		this.place_of_birth = place_of_birth;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
}
