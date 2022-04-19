package com.example.springsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springsocial.model.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
	  @Query(value = "select * from education where email = :email", nativeQuery = true)
	    Education getByEmail(String email);

}