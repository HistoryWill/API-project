package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cognixia.jump.model.Inventory;
import com.cognixia.jump.model.User;

public interface InventoryRepository extends MongoRepository<User, String> {
	@Query("{itemId:'?0'}")
public List<Inventory> findByName(String name);
	
	// do the same thing as above and in addition ignore casing 
@Query("{itemId:'?0'}")
	public List<Inventory> findByitemId(int id);
	

	
	@Query(" { 'quantity': { $gt: ?0 } } ")
	public List<Inventory> quantityAbove(double quantity);
	
	
	
}
