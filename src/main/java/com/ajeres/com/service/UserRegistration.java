package com.ajeres.com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.commons.lang3.RandomUtils;

import com.ajeres.com.dao.AccountRepository;
import com.ajeres.com.dao.UserRepository;
import com.ajeres.com.entity.AccountEntity;
import com.ajeres.com.entity.UserEntity;
import com.ajeres.com.model.Account;
import com.ajeres.com.model.ResponseObj;
import com.ajeres.com.model.Status;
import com.ajeres.com.model.User;

@Service
public class UserRegistration {
	@Autowired
	UserRepository userRepository;

	@Autowired
	AccountRepository accountRepository;

	public ResponseObj<User> validate(User user) {
		ResponseObj<User> response = new ResponseObj<>();

		UserEntity userEntity = userRepository.findOneBynameAndPassword(user.getName(), user.getPassword());
		if (userEntity.getName().equals(user.getName()) && userEntity.getPassword().equals(user.getPassword())) {
			response.setStatus(Status.Success);
			response.setMessage("Logged in sucessfully");
		} else {
			response.setStatus(Status.Failure);
			response.setMessage("Cannot Login");
			response.getData().add(user);

		}
		return response;
	}

	public ResponseObj<User> getUserDeatils(Long id) {
		ResponseObj<User> response = new ResponseObj<>();

		Optional<UserEntity> user = userRepository.findById(id);
		User usernew = new User();
		BeanUtils.copyProperties(user, usernew);
		response.getData().add(usernew);
		response.setStatus(Status.Success);
		return response;

	}

	public ResponseObj<User> getAllUsers() {
		ResponseObj<User> response = new ResponseObj<>();

		List<UserEntity> userEntities = userRepository.findAll();
		List<User> users = userEntities.stream().map(userEntity -> {
			User usernew = new User();
			BeanUtils.copyProperties(userEntity, usernew);
			return usernew;
		}).collect(Collectors.toList());

		response.setData(users);
		response.setStatus(Status.Success);
		return response;

	}

	public ResponseObj<User> updateUserDetails(User user) {
		ResponseObj<User> response = new ResponseObj<>();

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		userRepository.save(userEntity);
		response.setStatus(Status.Success);
		response.getData().add(user);
		return response;
	}

	public ResponseObj<AccountEntity> registerAccount(User user) {
		ResponseObj<AccountEntity> response = new ResponseObj<>();
		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getId());
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setAccountNo(RandomUtils.nextLong(0, 1000));
		accountEntity.setUser(userEntity);
		
		accountRepository.save(accountEntity);
		response.setStatus(Status.Success);
		response.getData().add(accountEntity);
		return response;
	}

	public ResponseObj<AccountEntity> debit(AccountEntity userExists, int amount) {
		ResponseObj<AccountEntity> response = new ResponseObj<>();
		if (userExists.getBalance() < amount) {
			response.setStatus(Status.Failure);
		} else {
			userExists.setBalance(userExists.getBalance() - amount);
			accountRepository.save(userExists);
			response.setStatus(Status.Success);
			response.getData().add(userExists);
		}

		return response;
	}

	public ResponseObj<AccountEntity> credit(AccountEntity accountEntity, int amount) {
		ResponseObj<AccountEntity> response = new ResponseObj<>();
		accountEntity.setBalance(accountEntity.getBalance() + amount);
		accountRepository.save(accountEntity);
		response.setStatus(Status.Success);
		response.getData().add(accountEntity);

		return response;
	}

	public ResponseObj<AccountEntity> transferMoney(AccountEntity fromAccount2, AccountEntity toAccount, int amount) {
		// TODO Auto-generated method stub

		ResponseObj<AccountEntity> response = new ResponseObj<>();
		fromAccount2.setBalance(fromAccount2.getBalance() - amount);
		accountRepository.save(fromAccount2);
		response.getData().add(fromAccount2);

		toAccount.setBalance(toAccount.getBalance() + amount);
		accountRepository.save(toAccount);
		response.setStatus(Status.Success);
		response.getData().add(toAccount);

		return response;

	}

	/*
	 * private Connection connect = null; private Statement statement = null;
	 * private PreparedStatement preparedStatement = null; private ResultSet
	 * resultSet = null;
	 * 
	 * final private String host = "sql12.freemysqlhosting.net"; final private
	 * String user = "sql12272761"; final private String passwd = "3vap344d1l";
	 * 
	 * public static void main(String[] args) throws Exception {
	 * 
	 * StudentRegistration studentRegistration=new StudentRegistration();
	 * studentRegistration.readDataBase(); }
	 * 
	 * 
	 * public void readDataBase() throws Exception { try {
	 * Class.forName("com.mysql.jdbc.Driver"); connect =
	 * DriverManager.getConnection("jdbc:mysql://"+host+"/sql12272761", user,
	 * passwd);
	 * 
	 * System.out.println(connect); } catch (Exception e) { throw e; }
	 * 
	 * }
	 */

}
