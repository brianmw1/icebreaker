package com.example.springsocial.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDailyQuestionsRequest {
	
	private Long id;
	private String time;
	private String one;
	private String two;	
	private String three;
	private String four;
	private String five;
}
