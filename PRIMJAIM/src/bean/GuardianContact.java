package bean;

import java.io.Serializable;

public class GuardianContact implements Serializable {
	/*
	 * Declaration of variables
	 */
	String gcontact_id, phone_number, provider, subscription, phoneNoType;
	Guardian guardian;

	public GuardianContact() {
		
	}

	public GuardianContact(String gcontact_id, String phone_number,
			String phoneNoType) {
		super();
		this.gcontact_id = gcontact_id;
		this.phone_number = phone_number;
		this.phoneNoType = phoneNoType;
	}

	public String getPhoneNoType() {
		return phoneNoType;
	}

	public void setPhoneNoType(String phoneNoType) {
		this.phoneNoType = phoneNoType;
	}

	public String getGcontact_id() {
		return gcontact_id;
	}
	
	public void setGcontact_id(String gcontact_id) {
		this.gcontact_id = gcontact_id;
	}
	
	public Guardian getGuardian() {
		return guardian;
	}
	
	public void setGuardian(Guardian guardian) {
		this.guardian = guardian;
	}
	
	public String getPhone_number() {
		return phone_number;
	}
	
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	public String getProvider() {
		return provider;
	}
	
	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	public String getSubscription() {
		return subscription;
	}
	
	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}
}
