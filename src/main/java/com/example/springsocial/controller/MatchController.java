package com.example.springsocial.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.model.Connections;
import com.example.springsocial.model.QueueConnection;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.CreateConnectionRequest;
import com.example.springsocial.payload.UpdateMatch;
import com.example.springsocial.repository.ConnectionsRepository;
import com.example.springsocial.repository.QueueConnectionRepository;
import com.example.springsocial.repository.UserRepository;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@RestController
public class MatchController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConnectionsRepository connectionsRepository;
	
	@Autowired
	private QueueConnectionRepository queueConnectionRepository;
	
	
	@GetMapping("user/good/findmatches")
	public List<User> findPossibleMatches(CreateConnectionRequest createConnectionRequest) {
		ArrayList<Long> possibleMatches = userRepository.getMatches();
		ArrayList<User> keptPossibleMatches = new ArrayList<>();
		for(Long match : possibleMatches) {
			if(match == createConnectionRequest.getId()) continue;
			else if(connectionsRepository.exists(createConnectionRequest.getId(), match) == 1) continue;
			else {
				keptPossibleMatches.add(userRepository.getById(match));
				Connections connection = new Connections();
				connection.setUser_one(createConnectionRequest.getId());
				connection.setUser_two(match);
				connection.setConnectionId(createConnectionRequest.getId() + " " + match);
				connection.setStatus("Pending");
				connectionsRepository.save(connection);
				
			}
		}
		return keptPossibleMatches;
	}
	
	@PostMapping("user/updateMatch")
	public String updateMatch(@RequestBody UpdateMatch updateMatch) throws IOException, TimeoutException {
		if(!updateMatch.isAccepted()) {
			connectionsRepository.update("NotAccepted", updateMatch.getId1(), updateMatch.getId2());
		}else if(updateMatch.isAccepted()) {
			connectionsRepository.update("Accepted", updateMatch.getId1(), updateMatch.getId2());
			twoWayConnection(updateMatch.getId1(), updateMatch.getId2());
		}
		
		return "";
	}
	
	public void twoWayConnection(Long id1, Long id2) throws IOException, TimeoutException {
		if(connectionsRepository.getStatus(id1 + " " + id2) == null || 
			connectionsRepository.getStatus(id2 + " " + id1) == null) {
			return;
		}
		if(connectionsRepository.getStatus(id1 + " " + id2).equals("Accepted") &&
				connectionsRepository.getStatus(id2 + " " + id1).equals("Accepted")) {
			
			connectionsRepository.update("PendingMatched", id1, id2);
			connectionsRepository.update("PendingMatched", id2, id1);
			createQueue(id1, id2);
		}
	}
	
	@GetMapping("user/checkForMatches")
	public int showNewMatches(Long id) {
		int total = connectionsRepository.getAmountNewMatches(id);
		connectionsRepository.updateStatus(id);
		return total;
	}
	
	public void createQueue(Long id1, Long id2) throws IOException, TimeoutException {
		String queueName = id1 + "QUEUE" + id2;
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		boolean created = false;
		try (Connection connection = factory.newConnection();
		     Channel channel = connection.createChannel()) {
			 channel.queueDeclare(queueName, false, false, false, null);
			 created = true;
		}finally {
			if(created) {
				QueueConnection queueConnection = new QueueConnection();
				queueConnection.setId1(id1);
				queueConnection.setId2(id2);
				queueConnection.setqName(queueName);
				queueConnectionRepository.save(queueConnection);
			}
		}
	}
}
