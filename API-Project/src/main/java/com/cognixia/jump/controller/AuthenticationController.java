package com.cognixia.jump.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.AuthenticationRequest;
import com.cognixia.jump.model.AuthenticationResponse;
import com.cognixia.jump.model.User;
import com.cognixia.jump.util.JwtUtil;
@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception{
		try {
            Authentication authenticate = authenticationManager
                .authenticate(
                    new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                    )
                );
            
		 } catch (BadCredentialsException ex) {
	        	throw new Exception("Incorrect username or password");
	        }
            final UserDetails userDetails = userDetailsService.loadUserByUsername( request.getUsername() );
    		
    		// generate the token for that user
    		final String jwt = jwtUtil.generateTokens(userDetails);
    		
    		
       
		return ResponseEntity.status(201).body( new AuthenticationResponse(jwt) );
////		try {
//			System.out.print(request.getUsername());
//			final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
//			System.out.println(userDetails.getPassword());	
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
////			final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
//			//System.out.print(userDetails.getUsername());
//			final String jwt = jwtUtil.generateTokens(userDetails);
//			
////			
////		}catch(BadCredentialsException e) {
////			throw new Exception("Bad Password");
////		}
////		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
////		System.out.print(userDetails);
////		final String jwt = jwtUtil.generateTokens(userDetails);
////		
		
	//	return ResponseEntity.status(201).body( new AuthenticationResponse(jwt) );
		
	}
	
}
