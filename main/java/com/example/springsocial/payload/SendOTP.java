package com.example.springsocial.payload;

import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendOTP {
	@Id
	private Long id;

	private String phonenumber;
	
}
