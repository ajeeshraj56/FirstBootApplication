package com.ajeres.com.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajeres.com.dao.UserRepository;
import com.ajeres.com.entity.UserEntity;
import com.ajeres.com.model.ResponseObj;
import com.ajeres.com.model.User;
import com.ajeres.com.service.UserRegistration;


@RequestMapping("api/")
@RestController
public class LoginController {
	
	 @Autowired
	    private UserRepository userRepository;
	 
	 @Autowired
	 private UserRegistration userRegistration;
	 
	   
	    @PostMapping("user")
	    public UserEntity createUser(@Valid @RequestBody User user) {
	    	UserEntity userEntity=new UserEntity();
	    	BeanUtils.copyProperties(user, userEntity);
	    	return userRepository.save(userEntity);
	    }
	    
	    @PostMapping("login")
	    public ResponseObj<User> validateUser(@RequestBody User user)
	    {
	    	return userRegistration.validate(user);
	    }
	    
	    @GetMapping("users")
	    public ResponseObj<User> findAllUser()
	    {
	    	return userRegistration.getAllUsers();
	    }
	    
	    @GetMapping("user/{id}")
	    public ResponseObj<User> getUserDetails(@PathVariable(value="id") Long id)
	    {
	    	return userRegistration.getUserDeatils(id);
	    }
	    
	    @PutMapping("user")
	    public ResponseObj<User> updateDetails(@RequestBody User user)
	    {
	    	return userRegistration.updateUserDetails(user);
	    }
	    
	    
}
