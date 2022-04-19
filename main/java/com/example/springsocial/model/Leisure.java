package com.example.springsocial.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "leisure")
public class Leisure {
	@Id
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	private boolean social;//socializing with family and friends
	private boolean cognitive;//mentally stimulating activities
	private boolean physical;//exercise and sports
	public boolean isSocial() {
		return social;
	}
	public void setSocial(boolean social) {
		this.social = social;
	}
	public boolean isCognitive() {
		return cognitive;
	}
	public void setCognitive(boolean cognitive) {
		this.cognitive = cognitive;
	}
	public boolean isPhysical() {
		return physical;
	}
	public void setPhysical(boolean physical) {
		this.physical = physical;
	}

}
