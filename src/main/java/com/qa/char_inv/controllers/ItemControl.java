package com.qa.char_inv.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.char_inv.data.entity.Inventory;
import com.qa.char_inv.data.entity.Item;
import com.qa.char_inv.data.repository.InventoryRepo;
import com.qa.char_inv.data.repository.ItemRepo;

@RestController
@RequestMapping(path = "/item")
public class ItemControl {
	
	private ItemRepo itemRepo;
	private InventoryRepo inventoryRepo;
	
	@Autowired
	public ItemControl(ItemRepo itemRepo, InventoryRepo inventoryRepo) {
		this.itemRepo = itemRepo;
		this.inventoryRepo = inventoryRepo;
	}
	
	@GetMapping
	public ResponseEntity<List<Item>> getItems() {
		return ResponseEntity.ok(itemRepo.findAll());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Item> getItem(@PathVariable(name = "id") int id) {
		Optional<Item> item = itemRepo.findById(id);
		
		if (item.isPresent()) {
			return new ResponseEntity<>(item.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// When I create a post, it is required that the user id also be provided
	// of the user that is creating the post.
	// - if that user doesn't exist, we will return a 404 - user not found
	@PostMapping(path = "/{inventoryId}")
	public ResponseEntity<Item> createItem(@Valid @RequestBody Item item, @PathVariable(name = "inventoryId") int inventoryId) {
		Optional<Inventory> inventory = inventoryRepo.findById(inventoryId);
		
		if (inventory.isPresent()) {
			item.setInventory(inventory.get());
			Item newItem = itemRepo.save(item);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", "http://localhost:8080/post/" + newItem.getId());
			
			return new ResponseEntity<>(newItem, headers, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(path = "/{id}")
	public Item updateItem(@RequestBody Item item, @PathVariable(name = "id") int id) {
		// TODO: 2. Implement me
		return null;
	}
	
	@DeleteMapping(path = "/{id}")
	public Item deleteItem(@PathVariable(name = "id") int id) {
		// TODO: 3. Implement me
		return null;
	}

}
