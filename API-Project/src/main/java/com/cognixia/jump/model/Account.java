package com.cognixia.jump.model;

import java.util.ArrayList;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("accounts")
public class Account {
	@Id
	private String id;
	@NotBlank
	private int accountId;
	@NotBlank
	private String username;
	
	
	
	ArrayList<String> Items;

	public Account( @NotBlank String username) {
		super();
		this.id = id;
		this.username = username;
		
	}

	
	public Account(String id, @NotBlank String username) {
		super();
		this.id = id;
		this.username = username;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<String> getItems() {
		return Items;
	}

	public void setItems(ArrayList<String> items) {
		Items = items;
	}
	
	

}
