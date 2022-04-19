package com.example.springsocial.payload;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMatch {
	@NotNull
	private boolean accepted;
	@NotNull
	private Long id1;
	@NotNull
	private Long id2;
}
