
package com.example.springsocial.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UpdateProfileRequest {
	@NotBlank
    @Email
    private String email;
	
	private String genderPreference;
	
	private boolean inpersonRelationship;
	
	private String phoneNumber;
	
	public String getGenderPreference() {
		return genderPreference;
	}

	public void setGenderPreference(String genderPreference) {
		this.genderPreference = genderPreference;
	}

	public boolean isInpersonRelationship() {
		return inpersonRelationship;
	}

	public void setInpersonRelationship(boolean inpersonRelationship) {
		this.inpersonRelationship = inpersonRelationship;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String locationPref;
	
	public void setLocationPref(String pref) {
		this.locationPref = pref;
	}
	
	public String getLocationPref() {
		return this.locationPref;
	}
	
	public String getEmail() {
		return this.email;
	}
	
}

