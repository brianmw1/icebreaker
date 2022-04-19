package com.example.springsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springsocial.model.Family;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {
	  @Query(value = "select * from family where email = :email", nativeQuery = true)
	    Family getByEmail(String email);

}