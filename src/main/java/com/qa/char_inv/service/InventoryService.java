package com.qa.char_inv.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.char_inv.data.dto.InventoryDTO;
import com.qa.char_inv.data.dto.NewInventoryDTO;
import com.qa.char_inv.data.entity.Inventory;
import com.qa.char_inv.data.repository.InventoryRepo;

@Service
public class InventoryService {

	private InventoryRepo inventoryRepo;
	private ModelMapper modelMapper;
	
	@Autowired 
	public InventoryService(InventoryRepo inventoryRepo, ModelMapper modelMapper) {
		super();
		this.inventoryRepo = inventoryRepo;
		this.modelMapper = modelMapper;
	}
	
	// inventory toDTO
	private InventoryDTO toDTO(Inventory inventory) {
		return modelMapper.map(inventory, InventoryDTO.class);
	}
	
	// create inventory
	public InventoryDTO createInventory(NewInventoryDTO inventoryDTO) {
		Inventory create = this.modelMapper.map(inventoryDTO, Inventory.class);
		Inventory newInventory = inventoryRepo.save(create);
		return this.toDTO(newInventory);
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