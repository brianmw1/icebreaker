package com.example.springsocial.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "family")
public class Family {

	@Id
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	private boolean complicated;
	private boolean married;
	private boolean single;
	private boolean divorced;
	private boolean dating;
	private boolean widow;
	
	
	
	
	
	
	public boolean isMarried() {
		return married;
	}
	public void setMarried(boolean married) {
		this.married = married;
	}
	public boolean isSingle() {
		return single;
	}
	public void setSingle(boolean single) {
		this.single = single;
	}
	public boolean isDivorced() {
		return divorced;
	}
	public void setDivorced(boolean divorced) {
		this.divorced = divorced;
	}
	public boolean isDating() {
		return dating;
	}
	public void setDating(boolean dating) {
		this.dating = dating;
	}
	public boolean isWidow() {
		return widow;
	}
	public void setWidow(boolean widow) {
		this.widow = widow;
	}
	public boolean isComplicated() {
		return complicated;
	}
	public void setComplicated(boolean complicated) {
		this.complicated = complicated;
	}
	

}



