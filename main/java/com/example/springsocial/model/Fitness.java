package com.example.springsocial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fitness")
public class Fitness {
	@Id
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private boolean calisthenics;
	private boolean cycling;
	private boolean hiking;
	private boolean rockClimbing;
	private boolean weightLifting;
	private boolean yoga;
	private boolean horseRiding;
	private boolean dancing;
	private boolean gym;
	
	public boolean isCalisthenics() {
		return calisthenics;
	}
	public void setCalisthenics(boolean calisthenics) {
		this.calisthenics = calisthenics;
	}
	public boolean isCycling() {
		return cycling;
	}
	public void setCycling(boolean cycling) {
		this.cycling = cycling;
	}
	public boolean isHiking() {
		return hiking;
	}
	public void setHiking(boolean hiking) {
		this.hiking = hiking;
	}
	public boolean isRockClimbing() {
		return rockClimbing;
	}
	public void setRockClimbing(boolean rockClimbing) {
		this.rockClimbing = rockClimbing;
	}
	public boolean isWeightLifting() {
		return weightLifting;
	}
	public void setWeightLifting(boolean weightLifting) {
		this.weightLifting = weightLifting;
	}
	public boolean isYoga() {
		return yoga;
	}
	public void setYoga(boolean yoga) {
		this.yoga = yoga;
	}
	public boolean isHorseRiding() {
		return horseRiding;
	}
	public void setHorseRiding(boolean horseRiding) {
		this.horseRiding = horseRiding;
	}
	public boolean isDancing() {
		return dancing;
	}
	public void setDancing(boolean dancing) {
		this.dancing = dancing;
	}
	public boolean isGym() {
		return gym;
	}
	public void setGym(boolean gym) {
		this.gym = gym;
	}







	
}
