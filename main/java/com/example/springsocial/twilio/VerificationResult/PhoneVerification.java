package com.example.springsocial.twilio.VerificationResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springsocial.twilio.Twilioproperties;
import com.twilio.exception.ApiException;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;

@Service
public class PhoneVerification {
	private final Twilioproperties twilioproperties;
	
	@Autowired
	public PhoneVerification(Twilioproperties twilioproperties) {
		this.twilioproperties = twilioproperties;
	}
	
	public VerificationResult startVerification(String phone) {
		try {
			Verification verification = Verification.creator("VAb449f45397a185f94b5a50be3ab222ba", phone, "sms").create();
			if("approved".equals(verification.getStatus()) || "pending".equals(verification.getStatus())) {
				return new VerificationResult(verification.getSid());
			}
		}catch (ApiException exception) {
			return new VerificationResult(new String[] {exception.getMessage()});
		}
		return null;
	}

	public VerificationResult checkverification(String phoneNumber, String otp) {
		try {
			VerificationCheck verification = VerificationCheck.creator(twilioproperties.getServiceId(), otp).setTo(phoneNumber).create();
			if("approved".equals(verification.getStatus())) {
				return new VerificationResult(verification.getSid());
			}
			return new VerificationResult(new String[] {"Invalid code."});
		}catch(ApiException exception) {
			return new VerificationResult(new String[] {exception.getMessage()});
		}
	}
}
