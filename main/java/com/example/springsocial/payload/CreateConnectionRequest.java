package com.example.springsocial.payload;

public class CreateConnectionRequest {
	private Long id;
	
	
	public CreateConnectionRequest(Long id, Long id2) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	
}
