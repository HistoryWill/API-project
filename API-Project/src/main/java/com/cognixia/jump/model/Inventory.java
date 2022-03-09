package com.cognixia.jump.model;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document("inventory")
public class Inventory {

	@Id
	private String id;
	@NotBlank
	private int itemId;
	@NotBlank
	private String itemName;
	@NotBlank
	private int quantity;
	public Inventory(String id, @NotBlank int itemId, @NotBlank String itemName, @NotBlank int quantity) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
