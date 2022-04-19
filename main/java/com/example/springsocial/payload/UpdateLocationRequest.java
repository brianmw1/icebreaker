package com.example.springsocial.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateLocationRequest {
	double longitude;
	private double latitude;
	private long id;
	private String city;
	
	
	public UpdateLocationRequest(double longitude, double latitude, int id) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.id = id;
	}
	
	public double getlongitude() {
		return this.longitude;
	}
	
	public double getlatitude() {
		return this.longitude;
	}
	
	public long getId() {
		return this.id;
	}

}
