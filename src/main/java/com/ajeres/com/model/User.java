package com.ajeres.com.model;

import javax.validation.constraints.NotNull;


public class User {

	    private Long id;
	    @NotNull 
	    private String password;
	    @NotNull
	    private String name;
	    private String email;
	    
	 
	    public Long getId() 
	    {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
}
