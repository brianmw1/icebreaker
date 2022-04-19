package com.example.springsocial.payload;

import lombok.Setter;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.springsocial.security.CurrentUser;

import lombok.Getter;

@Getter
@Setter
public class VerifyOTPRequest {
	private Long id;
	private String phonenumber;
	private String otp;
	
}
