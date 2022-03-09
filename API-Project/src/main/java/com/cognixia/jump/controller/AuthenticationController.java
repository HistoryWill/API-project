package com.cognixia.jump.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties.Registration.Signing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Account;
import com.cognixia.jump.model.AuthenticationRequest;
import com.cognixia.jump.model.AuthenticationResponse;
import com.cognixia.jump.model.SignupRequest;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.UserDetailsServiceImpl;
import com.cognixia.jump.util.JwtUtil;
@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	MongoTemplate mt;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid AuthenticationRequest request) throws Exception{
		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(request.getUsername());

		Authentication authentication;
		try {
		authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		}catch(BadCredentialsException e) {
			throw new Exception("Bad Password");
		}catch(DisabledException e) {
		
			throw new Exception("Disabled!");
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtil.generateTokens(userDetails);
		
		
		
		return ResponseEntity.status(201).body(jwt);
		
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> RegisterUser(@RequestBody SignupRequest signupRequest  ){
		if(userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity.badRequest().body("Error: Username taken");
		}
		
		User user = new User(signupRequest.getUsername(),signupRequest.getPassword(), "ROLE_USER");
		Account account = new Account(signupRequest.getUsername());
		mt.save(user);
		mt.save(account);
		
		
		return ResponseEntity.ok().body("User Registered!");
		
	}

//	
	
}
