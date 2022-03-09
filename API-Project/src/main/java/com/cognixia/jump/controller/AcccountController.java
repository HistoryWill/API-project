package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Account;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.AccountRepository;
import com.cognixia.jump.repository.UserRepository;

@RequestMapping("/api/accounts")
@RestController
public class AcccountController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository; 
	@Autowired
	MongoTemplate mt;
	
	@GetMapping("/account/{username}")
	public Optional<Account> getAccount(@PathVariable String username) {
		Optional<Account> account = accountRepository.findByUsername(username);
		return account;
	}
	//Adds elements to the item list
	@PutMapping("/update/Account")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(account.getUsername()));
		Update update = new Update();
		update.set("items", account.getItems());
		mt.updateFirst(query, update, Account.class);
		return new ResponseEntity<>( HttpStatus.OK);	
	}
	//Removes Items from item list
	@DeleteMapping("/delete/Account/{username}")
	public ResponseEntity<Account> removeAccount(@RequestBody Account account) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(account.getUsername()));
		Update update = new Update();
		update.set("items", account.getItems());
		mt.updateFirst(query, update, Account.class);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
