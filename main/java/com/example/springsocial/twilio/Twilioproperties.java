package com.example.springsocial.twilio;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties("twilio")
@Data
public class Twilioproperties {
	
	private String accountSid;
	private String authToken;
	private String serviceId;
	
}
