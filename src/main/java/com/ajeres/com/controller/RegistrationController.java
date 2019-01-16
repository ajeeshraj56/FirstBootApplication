package com.ajeres.com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajeres.com.dao.UserRepository;
import com.ajeres.com.entity.AccountEntity;
import com.ajeres.com.entity.UserEntity;
import com.ajeres.com.model.ResponseObj;
import com.ajeres.com.model.Status;
import com.ajeres.com.model.User;
import com.ajeres.com.service.UserRegistration;

@RestController
@RequestMapping("api/user/")
public class RegistrationController {
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private UserRegistration userRegistration;
	
	@PutMapping(value="registration")
	public ResponseObj<AccountEntity> regAccout(@RequestBody User user)
	{
		ResponseObj<AccountEntity> response= new ResponseObj<>();
		Optional<UserEntity> userExists = userrepo.findById(user.getId());
		
		if(userExists.isPresent())
		{
			response= userRegistration.registerAccount(user);
		}
		else
		{
			response.setStatus(Status.Failure);
			response.setMessage("Invalid UserId");
		}
		return response;
		
	}

}
