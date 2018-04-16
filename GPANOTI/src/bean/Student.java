package bean;

import java.util.ArrayList;

public class Student {
	
	private String Matric;
	private String Name;
	private String cohort;
	private String course;
	private String PA;
	private String phone;
	private ArrayList<ArrayList<String>> results;
	private String status;
	
	
	public String getMatric() {
		return Matric;
	}
	public void setMatric(String matric) {
		Matric = matric;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCohort() {
		return cohort;
	}
	public void setCohort(String cohort) {
		this.cohort = cohort;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getPA() {
		return PA;
	}
	public void setPA(String pA) {
		PA = pA;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public ArrayList<ArrayList<String>> getResults() {
		return results;
	}
	public void setResults(ArrayList<ArrayList<String>> results) {
		this.results = results;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
