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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.char_inv.data.dto.ItemDTO;
import com.qa.char_inv.data.dto.NewItemDTO;
import com.qa.char_inv.service.ItemService;

@RestController
@RequestMapping(path = "/item")
@CrossOrigin("*")
public class ItemControl {
	
	private ItemService itemService;
	
	@Autowired
	public ItemControl(ItemService itemService) {
		this.itemService = itemService;
		
	}
	
	//create
	@PostMapping
	public ResponseEntity<ItemDTO> createItem(@Valid @RequestBody NewItemDTO item) {
		ItemDTO newItem = itemService.createItem(item);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8090/item/" + newItem.getId());
		
		return new ResponseEntity<>(newItem, headers, HttpStatus.CREATED);
	}
	
	// read all
	@GetMapping
	public ResponseEntity<List<ItemDTO>> getItems() {
		return ResponseEntity.ok(itemService.getItems());
	}
	
	// read by id
	@GetMapping(path = "/{id}")
	public ResponseEntity<ItemDTO> getItem(@PathVariable(name = "id") int id) {
		ItemDTO item = itemService.getItem(id);
		
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	
	// update
	@PutMapping(path = "/{id}")
	public ResponseEntity<ItemDTO> updateItem(@Valid @RequestBody NewItemDTO item, 
		@PathVariable(name = "id") int id ) {
		
		return ResponseEntity.ok(itemService.updateItem(item, id));
	}
	
	// delete
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable(name = "id") int id) {
		ItemDTO dltItem = itemService.getItem(id);
		itemService.deleteItem(id);
		return ResponseEntity.ok(dltItem);
		
	}

}
