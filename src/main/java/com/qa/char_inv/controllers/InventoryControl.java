package com.qa.char_inv.controllers;

import java.util.List;

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

@RestController
@RequestMapping(path = "/user") // accepts requests at localhost:8080/user
public class InventoryControl {
	
	private InventoryService inventoryService;
	
	@Autowired // Instructs the Spring IoC container to inject the required dependency
	public InventoryControl(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
	
	@GetMapping
	public ResponseEntity<List<Character>> getCharacters() {
		return ResponseEntity.ok(inventoryService.readAll());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Character> getCharacter(@PathVariable(name = "id") int id) {
		Character character = inventoryService.readById(id);
		return new ResponseEntity<Character>(character, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}/posts")
	public ResponseEntity<Inventory> getCharacterInventory(@PathVariable(name = "id") int characterId) {
		<Inventory> inventory = inventoryService.readInventoryByCharacterId(characterId);
		return ResponseEntity.ok(inventory);
	}
	
	@PostMapping
	public ResponseEntity<Character> createCharacter(@Valid @RequestBody Character character) {
		Character newCharacter = inventoryService.create(character);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/user/" + newCharacter.getId());

		return new ResponseEntity<Character>(newCharacter, headers, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Character> updateUser(@RequestBody Character character, @PathVariable(name = "id") int id) {
		// TODO: Put your implementation here
		return null;
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteCharacter(@PathVariable(name = "id") int id) {
		// TODO: Put your implementation here
		return null;
	}
}

