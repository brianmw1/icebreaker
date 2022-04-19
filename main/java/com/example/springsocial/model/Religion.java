package com.example.springsocial.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "religion")
public class Religion {
	@Id
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	private boolean polytheism;//multiple gods
	private boolean monotheism;//single god
	private boolean atheism;
	private boolean animism;//animals, plant, natural 
	private boolean totemism;//human-natural being connection
	public boolean isPolytheism() {
		return polytheism;
	}
	public void setPolytheism(boolean polytheism) {
		this.polytheism = polytheism;
	}
	public boolean isMonotheism() {
		return monotheism;
	}
	public void setMonotheism(boolean monotheism) {
		this.monotheism = monotheism;
	}
	public boolean isAtheism() {
		return atheism;
	}
	public void setAtheism(boolean atheism) {
		this.atheism = atheism;
	}
	public boolean isAnimism() {
		return animism;
	}
	public void setAnimism(boolean animism) {
		this.animism = animism;
	}
	public boolean isTotemism() {
		return totemism;
	}
	public void setTotemism(boolean totemism) {
		this.totemism = totemism;
	}

}
