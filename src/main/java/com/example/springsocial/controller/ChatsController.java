package com.example.springsocial.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.model.QueueConnection;
import com.example.springsocial.repository.QueueConnectionRepository;
import com.example.springsocial.repository.UserRepository;

@RestController
public class ChatsController {
	
	@Autowired
	private QueueConnectionRepository queueConnectionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/user/me/getAvailableChats")
	public HashMap<String, List<String>> availableChats(Long id){
		HashMap<String, List<String>> map = new HashMap<>();
		ArrayList<QueueConnection> qConnections = queueConnectionRepository.getConnections(id);
		for(QueueConnection qc : qConnections) {
			long cId = 0;
			if(qc.getId1() == id) {
				cId = qc.getId2();
			}else {
				cId = qc.getId1();
			}
			ArrayList<String> mylist = new ArrayList<>();
			mylist.add(String.valueOf(cId));
			mylist.add(userRepository.getById(cId).getName());
			mylist.add(userRepository.getById(cId).getImageUrl());
			map.put(qc.getqName(), mylist);
		}
		
		return map;
	}
	
}
