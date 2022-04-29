package com.qa.char_inv.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qa.char_inv.data.entity.Inventory;
import com.qa.char_inv.data.repository.InventoryRepo;

@Service
public class InventoryService {

	private InventoryRepo inventoryRepo;
	
	@Autowired 
	public InventoryService(InventoryRepo inventoryRepo) {
		this.inventoryRepo = inventoryRepo;
	}
	
	public List<Inventory> readAll() {
		return inventoryRepo.findAll();
	}
	
	public Inventory readById(int id) {
		Optional<Inventory> inventory = inventoryRepo.findById(id);
		
		if (inventory.isPresent()) {
			return inventory.get();
		}
		throw new EntityNotFoundException("Inventory with id " + id + " was not found");
	}
	

	public Inventory create(Inventory inventory) {
		return inventoryRepo.save(inventory);
	}
	
}