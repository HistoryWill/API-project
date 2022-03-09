package com.cognixia.jump.repository;

import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cognixia.jump.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	
	@Query("{username:'?0'}")
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
}
