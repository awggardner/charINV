package com.qa.char_inv.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.char_inv.data.dto.InventoryDTO;
import com.qa.char_inv.data.dto.NewInventoryDTO;
import com.qa.char_inv.service.InventoryService;

@RestController
@RequestMapping(path = "/inventory") // accepts requests at localhost:8090/inventory
@CrossOrigin("*")
public class InventoryControl {
	
	private InventoryService inventoryService;
	
	
	@Autowired // Instructs the Spring IoC container to inject the required dependency
	public InventoryControl(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
	
	@PostMapping
	// create inventory
	public ResponseEntity<InventoryDTO> createInventory(@Valid @RequestBody NewInventoryDTO inventory) {
		InventoryDTO newInventory = inventoryService.createInventory(inventory);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8090/Inventory/" + newInventory.getId());

		return new ResponseEntity<>(newInventory, headers, HttpStatus.CREATED);
	}
	
	// read all inventories
	@GetMapping
	public ResponseEntity<List<InventoryDTO>> getInventory() {
		return ResponseEntity.ok(inventoryService.getInventory());
	}
	
	// read by id
	@GetMapping(path = "/{id}")
	public ResponseEntity<InventoryDTO> getInventory(@PathVariable(name = "id") int id) {
		InventoryDTO inventory = inventoryService.getInventory(id);
		return new ResponseEntity<>(inventory, HttpStatus.OK);

	}

	
	// delete
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteInventory(@PathVariable(name = "id") int id) {
		InventoryDTO dltInventory = inventoryService.getInventory(id);
		inventoryService.deleteInventory(id);
		return ResponseEntity.ok(dltInventory);
		
	}
}

