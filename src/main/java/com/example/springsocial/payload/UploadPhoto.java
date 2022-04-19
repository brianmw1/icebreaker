package com.example.springsocial.payload;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

public class UploadPhoto {
	@NotBlank
	private Long id;
	
	@NotBlank
	private MultipartFile multipartFile;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
}
