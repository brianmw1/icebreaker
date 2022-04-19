package com.example.springsocial.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springsocial.model.Interest;
import com.example.springsocial.model.User;
import com.example.springsocial.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
//@Transactional
public interface InterestRepository extends JpaRepository<Interest, Long> {

	  @Query(value = "select * from interests where email = :email", nativeQuery = true)
	    Interest getByEmail(String email);


}
