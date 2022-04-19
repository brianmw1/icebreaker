package com.example.springsocial.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserQuestionaire {
	@Id
	private Long id;
	
	private String one;
	private String two;
	private String three;
}
