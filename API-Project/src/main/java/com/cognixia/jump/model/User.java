package com.cognixia.jump.model;


import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User {

@Id
private Long id;

@NotBlank
private String username;
@NotBlank
private String password;

@NotBlank
private int accountId;

@NotBlank
private Roles role;

@NotBlank
private boolean enabled;
public boolean isEnabled() {
	return enabled;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}

public User(Long id, @NotBlank String username, @NotBlank String password, @NotBlank int accountId) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
	this.accountId = accountId;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public int getAccountId() {
	return accountId;
}

public void setAccountId(int accountId) {
	this.accountId = accountId;
}

public Roles getRole() {
	return role;
}

public void setRole(Roles role) {
	this.role = role;
}


}
