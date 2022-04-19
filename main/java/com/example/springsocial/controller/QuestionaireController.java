package com.example.springsocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.model.UserQuestionaire;
import com.example.springsocial.payload.UpdateUserQ;
import com.example.springsocial.repository.UserQRepository;

@RestController
public class QuestionaireController {
	
	@Autowired
	private UserQRepository userQRepository;
	
	@PostMapping("user/me/UpdateQ")
	public String updateStuff(UpdateUserQ updateUserq) {
		UserQuestionaire userq = new UserQuestionaire();
		userq.setId(updateUserq.getId());
		userq.setOne(updateUserq.getOne());
		userq.setTwo(updateUserq.getTwo());
		userq.setThree(updateUserq.getThree());
		userQRepository.save(userq);
		return "Saved";
	}
}
