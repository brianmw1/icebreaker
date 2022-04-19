package com.example.springsocial.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springsocial.model.Connections;
import com.example.springsocial.model.QueueConnection;

@Repository
public interface QueueConnectionRepository extends JpaRepository<QueueConnection, Long>{
	
	@Query(value = "select * from queue_connections where id1 = :id or id2 = :id", nativeQuery = true)
	ArrayList<QueueConnection> getConnections(Long id);
}
