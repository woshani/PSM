package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Guardian implements Serializable {
	/*
	 * Declaration of variables
	 */
	String guardian_id, name, gender, occupation, address, city, state, postcode, ic, citizen_status, relationship, dependent2;
	int dependent;
	User user;
	
	GuardianContact guardianContact;
	
	ArrayList<GuardianContact> guardianContacts;
	
	
	
	
	public ArrayList<GuardianContact> getGuardianContacts() {
		return guardianContacts;
	}

	public void setGuardianContacts(ArrayList<GuardianContact> guardianContacts) {
		this.guardianContacts = guardianContacts;
	}

	public Guardian() {
		// TODO Auto-generated constructor stub
	}
	
	public Guardian(String guardian_id, GuardianContact guardianContact) {
		super();
		this.guardian_id = guardian_id;
		this.guardianContact = guardianContact;
	}

	
	public GuardianContact getGuardianContact() {
		return guardianContact;
	}

	public void setGuardianContact(GuardianContact guardianContact) {
		this.guardianContact = guardianContact;
	}

	public String getGuardian_id() {
		return guardian_id;
	}
	
	public void setGuardian_id(String guardian_id) {
		this.guardian_id = guardian_id;
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

	public String getOccupation() {
		return occupation;
	}
	
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getPostcode() {
		return postcode;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	
	public int getDependent() {
		return dependent;
	}

	public void setDependent(int dependent) {
		this.dependent = dependent;
	}

	public String getDependent2() {
		return dependent2;
	}

	public void setDependent2(String dependent2) {
		this.dependent2 = dependent2;
	}

	public String getIc() {
		return ic;
	}

	public void setIc(String ic) {
		this.ic = ic;
	}

	public String getFullAddress() {
		return address + ", " + postcode + ", " + city + ", " + state;
	}

	public String getCitizen_status() {
		return citizen_status;
	}

	public void setCitizen_status(String citizen_status) {
		this.citizen_status = citizen_status;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	
}
