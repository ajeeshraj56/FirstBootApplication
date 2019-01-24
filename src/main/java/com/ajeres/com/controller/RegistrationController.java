package com.ajeres.com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajeres.com.dao.AccountRepository;
import com.ajeres.com.dao.UserRepository;
import com.ajeres.com.entity.AccountEntity;
import com.ajeres.com.entity.UserEntity;
import com.ajeres.com.model.Account;
import com.ajeres.com.model.ResponseObj;
import com.ajeres.com.model.Status;
import com.ajeres.com.model.User;
import com.ajeres.com.service.UserRegistration;

@RestController
@RequestMapping("api/account/")
public class RegistrationController {

	@Autowired
	private UserRepository userrepo;

	@Autowired
	private UserRegistration userRegistration;

	@Autowired
	private AccountRepository accountRepository;

	@PostMapping(value = "registration")
	public ResponseObj<AccountEntity> regAccout(@RequestBody User user) {
		ResponseObj<AccountEntity> response = new ResponseObj<>();
		Optional<UserEntity> userExists = userrepo.findById(user.getId());

		if (userExists.isPresent()) {
			response = userRegistration.registerAccount(user);
		} else {
			response.setStatus(Status.Failure);
			response.setMessage("Invalid UserId");
		}
		return response;

	}

	@PostMapping(value = "debitTrans")
	public ResponseObj<AccountEntity> debit(@RequestBody Account acc, @RequestBody int amount) {
		ResponseObj<AccountEntity> response = new ResponseObj<>();
		AccountEntity userExists = accountRepository.findById(acc.getAccountNo());

		if (userExists.getAccountNo() != null) {
			response = userRegistration.debit(userExists, amount);
		} else {
			response.setStatus(Status.Failure);
			response.setMessage("Invalid UserId");
		}
		return response;
	}

	@PostMapping(value = "creditTrans")
	public ResponseObj<AccountEntity> credit(@RequestBody Account acc) {
		ResponseObj<AccountEntity> response = new ResponseObj<>();
		AccountEntity userExists = accountRepository.findById(acc.getAccountNo());
		int amount=300;
		if (userExists != null) {
			response = userRegistration.credit(userExists, amount);
			
		} else {			
			response.setStatus(Status.Failure);
			response.setMessage("Invalid UserId");

		}
		return response;
	}

	@PostMapping(value = "transMoney")
	public ResponseObj<AccountEntity> transferMoney(@RequestBody Long fromAcc, @RequestBody Long toAcc,
			@RequestBody int amount) {
		ResponseObj<AccountEntity> response = new ResponseObj<>();
		AccountEntity fromAccount = accountRepository.findById(fromAcc);
		AccountEntity toAccount = accountRepository.findById(toAcc);

		if (fromAccount.getAccountNo() != null && toAccount.getAccountNo() != null) {
			response = userRegistration.transferMoney(fromAccount, toAccount, amount);
		} else {
			response.setStatus(Status.Failure);
			response.setMessage("Invalid UserId");
		}
		return response;
	}

}
