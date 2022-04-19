package com.example.springsocial.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class UserQuestions {
	@Id
	private Long id;
	private String time;
	private String one;
	private String two;
	private String three;
	private String four;
	private String five;
	private String date;
	// month -- day -- time (19:30)
	
	
}
