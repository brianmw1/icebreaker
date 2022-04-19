package com.example.springsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.springsocial.model.Religion;

@Repository
public interface ReligionRepository extends JpaRepository<Religion, Long> {
	  @Query(value = "select * from religion where email = :email", nativeQuery = true)
	    Religion getByEmail(String email);

}
