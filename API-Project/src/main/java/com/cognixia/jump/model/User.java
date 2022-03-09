package com.cognixia.jump.model;


import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User {

@Id
private String id;

@NotBlank
private String username;
@NotBlank
private String password;

@NotBlank
private int accountId;

@NotBlank
private String role;

private Roles roles;

@NotBlank
private boolean enabled;
public boolean isEnabled() {
	return enabled;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}

public User(String id, @NotBlank String username, @NotBlank String password, @NotBlank int accountId, @NotBlank String role) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
	this.accountId = accountId;
	this.roles = Roles.valueOf(role);
}

public String getId() {
	return id;
}

public void setId(String id) {
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
	return roles;
}

public void setRole(Roles role) {
	this.roles = role;
}


}
