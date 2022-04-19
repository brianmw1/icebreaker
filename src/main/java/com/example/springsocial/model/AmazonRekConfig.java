package com.example.springsocial.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonRekConfig {
 
	@Value("${aws.access_key_id}")
	private String accessKeyId;
	
	@Value("${aws.secret_access_key}")
	private String secretAccessKey;
	
	@Value("${aws.s3.region}")
	private String region;
	
	@Bean
	public AmazonRekognition getAmazonRekognitionClient() {
		final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
		return AmazonRekognitionClientBuilder
				.standard()
				.withRegion(Regions.fromName(region))
				.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
				.build();
		
	}
}
