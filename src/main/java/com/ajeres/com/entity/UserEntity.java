package com.ajeres.com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {

	    @Id
	    @GeneratedValue
	    private Long id;
	    
	    private String password;
	    
	    private String name;
	    
	    private String email;
	    
	    @OneToOne(fetch = FetchType.LAZY,mappedBy = "user", cascade=CascadeType.ALL)
	    private AccountEntity accountEntity;
	    
	    public AccountEntity getAccountEntity() {
			return accountEntity;
		}
		public void setAccountEntity(AccountEntity accountEntity) {
			this.accountEntity = accountEntity;
		}
		public UserEntity() {
	    }
	    public UserEntity(int id, String name, String password) {
	         this.name = name;
	         this.id = (long) id;
	         this.password = password;
	    }

	    public Long getId() {
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