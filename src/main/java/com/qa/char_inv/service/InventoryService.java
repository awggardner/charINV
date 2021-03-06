package com.qa.char_inv.service;

import java.util.ArrayList;
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
	
	
	// read all inventories
	public List<InventoryDTO> getInventory() {
		List<Inventory> inventories = inventoryRepo.findAll();
		List<InventoryDTO> inventoriesDTO = new ArrayList<>();
			for (Inventory inventory : inventories) {
				inventoriesDTO.add(this.toDTO(inventory));
			}
			return inventoriesDTO;
	}
	
	// read inventory by id
	public InventoryDTO getInventory(int id) {
		Optional<Inventory> inventory = inventoryRepo.findById(id);
			if (inventory.isPresent()) {
				return this.toDTO(inventory.get());
			}
			throw new EntityNotFoundException("We can't find this inventory...");
	}
	

	
	

	// update inventory
//	public InventoryDTO updateInventory(NewInventoryDTO inventory, int id) {
//		if (inventoryRepo.existsById(id)) {
//			Inventory oldInventory = inventoryRepo.getById(id);
//			oldInventory.setCharacter(inventory.getCharacter());
//			
//		}
//		return null;
//		
//	}
	
	//delete inventory
	public void deleteInventory(int id) {
		if (inventoryRepo.existsById(id)) {
			inventoryRepo.deleteById(id);
			return;
		}
		throw new EntityNotFoundException("We can't find Inventory with id " + id + ", perhaps it has been stolen by a sneaky thief!");
	}
	
}