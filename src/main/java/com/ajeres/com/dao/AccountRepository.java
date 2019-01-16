package com.ajeres.com.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ajeres.com.entity.AccountEntity;


@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
	
	
	
}