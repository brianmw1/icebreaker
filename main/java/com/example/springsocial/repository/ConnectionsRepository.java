package com.example.springsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springsocial.model.Connections;


@Repository
@Transactional
public interface ConnectionsRepository extends JpaRepository<Connections, Long> {
	
	@Query(value = "select count(1) from connections where user_one = :id and user_two = :u2", nativeQuery = true)
	int exists(Long id, Long u2);
	
	
	@Modifying
	@Query(value = "update connections set status = :status where user_one = :id1 AND  user_two = :id2", nativeQuery = true)
	void update(String status, Long id1, Long id2);
	
	@Query(value = "select status from connections where connection_id = :id", nativeQuery = true)
	String getStatus(String id);
	
	@Query(value = "select count(*) from connections where user_one = :id1 and status = 'PendingMatched'", nativeQuery = true)
	int getAmountNewMatches(Long id1);
	
	@Modifying
	@Query(value = "update connections set status = 'notified' where user_one = :id1 and status = 'PendingMatched'", nativeQuery = true)
	void updateStatus(Long id1);
}
