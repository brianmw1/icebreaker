package com.example.springsocial.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "queue_connections")
public class QueueConnection {
	
	@Id
	private String qName;

	
	private Long id1;

	
	private Long id2;

	public Long getId1() {
		return id1;
	}

	public Long getId2() {
		return id2;
	}

	public String getqName() {
		return qName;
	}

	public void setId1(Long id12) {
		this.id1 = id12;
	}

	public void setId2(Long id2) {
		this.id2 = id2;
	}

	public void setqName(String qName) {
		this.qName = qName;
	}
}
