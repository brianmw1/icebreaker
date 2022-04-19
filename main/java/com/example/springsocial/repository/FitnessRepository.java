package com.example.springsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springsocial.model.Fitness;
import com.example.springsocial.model.Interest;

@Repository
public interface FitnessRepository extends JpaRepository <Fitness, Long> {
	  //@Query(value = "select * from fitnesss where email = :email", nativeQuery = true)
	  Fitness findByEmail(String email);
}
