package com.ajeres.com.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ajeres.com.entity.AccountEntity;


public interface AccountRepository extends JpaRepository<AccountEntity, String> {

	AccountEntity findById(Long accountNo);
	
	
	
}