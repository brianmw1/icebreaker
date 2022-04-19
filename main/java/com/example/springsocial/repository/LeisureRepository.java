package com.example.springsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springsocial.model.Leisure;

@Repository
public interface LeisureRepository extends JpaRepository<Leisure, Long> {
	  @Query(value = "select * from leisure where email = :email", nativeQuery = true)
	    Leisure getByEmail(String email);

}