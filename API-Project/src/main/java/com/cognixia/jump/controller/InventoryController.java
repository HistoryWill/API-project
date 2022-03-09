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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Inventory;
import com.cognixia.jump.repository.InventoryRepository;

@RequestMapping("/api/inventory")
@RestController
public class InventoryController {
	@Autowired
	MongoTemplate mt;
	
	@Autowired
	InventoryRepository inventoryRepository;
	
	@GetMapping("/get/{username}")
	public List<Inventory> getInventory(@PathVariable String username) {
		List<Inventory> Inventory = inventoryRepository.findByName(username);
		return Inventory;
	}
	//Adds elements to the item list
	@PutMapping("/updatequantity/")
	public ResponseEntity<Inventory> updateInventoryQuantity(@RequestBody Inventory inventory){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("itemId").is(inventoryRepository.findByName(inventory.getItemName())));
		Update update = new Update();
		update.set("items", inventory.getQuantity());
		mt.updateFirst(query, update, Inventory.class);
		return new ResponseEntity<>( HttpStatus.OK);	
	}
	//Removes Items from item list
	@DeleteMapping("/delete//{username}")
	public ResponseEntity<Inventory> removeInventory(@RequestBody Inventory inventory) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(inventory.getItemName()));
		Update update = new Update();
		update.set("items", inventory.getItemName());
		mt.remove(inventory);
		mt.updateFirst(query, update, Inventory.class);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
