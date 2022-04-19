package com.example.springsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springsocial.model.UserQuestionaire;

@Repository
public interface UserQRepository extends JpaRepository<UserQuestionaire, Long> {

	
}
