package com.ajeres.com.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ajeres.com.entity.AccountEntity;


public interface AccountRepository extends JpaRepository<AccountEntity, String> {

	AccountEntity findById(Long accountNo);
	
	
	
}