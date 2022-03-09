package com.cognixia.jump.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.cognixia.jump.model.User;

public interface UserRepository extends MongoRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}
