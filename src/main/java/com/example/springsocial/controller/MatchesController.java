package com.example.springsocial.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.model.Connections;
import com.example.springsocial.model.QueueConnection;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.CreateConnectionRequest;
import com.example.springsocial.repository.ConnectionsRepository;
import com.example.springsocial.repository.QueueConnectionRepository;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;


@RestController
public class MatchesController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConnectionsRepository connections;
	
	@Autowired
	private  QueueConnectionRepository queueConnectionRepository;
	
	@PostMapping("/user/me/findMatches")
	//@PreAuthorize("hasRole('USER')")
	public String getCurrentUser(@RequestBody CreateConnectionRequest createConnectionRequest) throws IOException, TimeoutException {
		int count = 0;
		User current = userRepository.getById(createConnectionRequest.getId());
		//double pointMax = current.getDistance()/110;
		double pointMax = 0.5;
		double longit = current.getLong();
		double lagit = current.getLag();
		ArrayList<Long> listUser = userRepository.getUserByLocation((lagit + pointMax), (lagit - pointMax), (longit + pointMax), (longit - pointMax));
		for(Long currMatch : listUser) {
			if(currMatch != current.getId()) {
				Connections connection = new Connections();
				connection.setId(current.getId());
				connection.setUser_two(currMatch);
				connection.setConnectionId(currMatch + "C" + current.getId());
				connections.save(connection);
				if(checkIfExists(current.getId(), currMatch)){
					count++;
					createQueue(current.getId(), currMatch);
				}
			}
		}
		return count + "Matches Created"; 
	}
	
	@PostMapping("/testingQuery")
	public ArrayList<Long> test(){
		return userRepository.getUserByLocation(24.6, 23.6, 23.5, 22.5);
	}
	
	public boolean checkIfExists(double id1, double id2) {
		if(connections.exists(id2, id1) == 0) {
			return false;
		}else {
			return true;
		}
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
