package com.example.springsocial.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "education")
public class Education {

	@Id
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private boolean socialScience;
	private boolean science;
	private boolean engineering;
	private boolean fineArtsMusic;
	
	public boolean isSocialScience() {
		return socialScience;
	}
	public void setSocialScience(boolean socialScience) {
		this.socialScience = socialScience;
	}
	public boolean isScience() {
		return science;
	}
	public void setScience(boolean science) {
		this.science = science;
	}
	public boolean isEngineering() {
		return engineering;
	}
	public void setEngineering(boolean engineering) {
		this.engineering = engineering;
	}
	public boolean isFineArtsMusic() {
		return fineArtsMusic;
	}
	public void setFineArtsMusic(boolean fineArtsMusic) {
		this.fineArtsMusic = fineArtsMusic;
	}

	
	
	
	


}




