package com.cognixia.jump.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognixia.jump.model.User;

// contain the necessary user details/information needed to authenticate a user
public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	public int getAccountid() {
		return accountid;
	}



	private String username;
	private String password;
	private int accountid;
	
	private boolean enabled;
	private List<GrantedAuthority> authorities;
	
	
	public UserPrincipal(User user) {
		super();
		this.username = user.getUsername();
		this.password = user.getPassword();
		
		// set up the list of granted authorities by accessing the user's role(s)
		this.authorities = Arrays.asList( new SimpleGrantedAuthority( user.getRole().name() ) );
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return enabled;
	}

}
