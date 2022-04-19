package com.example.springsocial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "connections")
@Getter
@Setter
public class Connections {
	@Id
	private String connectionId;
	
	public String getConnectionId() {
		return connectionId;
	}
	public void setConnectionId(String connectionId) {
		this.connectionId = connectionId;
	}
	private Long user_one;	
	
	
	private Long user_two;

	private String status;
	

	public void setId(Long id) {
		this.user_one = id;
	}
	
	public void setUser_two(Long user_two) {
		this.user_two = user_two;
	}
	
}
