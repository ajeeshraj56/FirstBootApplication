package com.ajeres.com.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ajeres.com.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	
	UserEntity findOneBynameAndPassword(String username,String password);

	Optional<UserEntity> findById(Long long1);

	
}
