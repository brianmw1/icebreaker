package com.example.springsocial.twilio;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@Data
@NoArgsConstructor
public class Twilioproperties {
	
	private String accountSid;
	private String authToken;
	private String serviceId;
	
}
