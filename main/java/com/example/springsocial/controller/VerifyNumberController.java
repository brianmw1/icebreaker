package com.example.springsocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.model.User;
import com.example.springsocial.payload.SendOTP;
import com.example.springsocial.payload.VerifyOTPRequest;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.twilio.VerificationResult.PhoneVerification;
import com.example.springsocial.twilio.VerificationResult.VerificationResult;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.example.springsocial.twilio.Twilioproperties;
import com.twilio.exception.ApiException;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.Twilio; 
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber; 

@RestController
public class VerifyNumberController {
	@Autowired
	PhoneVerification phonesmsservice;
	 
	@Autowired
	UserRepository userRepository;
	

	@PostMapping("/sendotp")
	public String[] sendotp(@CurrentUser @RequestBody SendOTP sendOTP) {
		User current = userRepository.getById(sendOTP.getId());
		current.setPhoneNumber(sendOTP.getPhonenumber());
		userRepository.save(current);
		VerificationResult result = phonesmsservice.startVerification(sendOTP.getPhonenumber());
		if(result.isValid()) {
			return result.getErrors();
		}
		return result.getErrors();
	}
	
	@PostMapping("/verifyotp")
	public boolean verifyOtp(@CurrentUser @RequestBody VerifyOTPRequest verifyOTPRequest) {
		VerificationResult result = phonesmsservice.checkverification(verifyOTPRequest.getPhonenumber(), verifyOTPRequest.getOtp());
		User current = userRepository.getById(verifyOTPRequest.getId());
		if(result.isValid()) {
			current.setVerifiedphonenumber(true);
			userRepository.save(current);
			return true;
		}
		return false;
	}
}
