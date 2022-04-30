package com.qa.char_inv.service;

import java.util.ArrayList;
import java.util.List;
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
	
	// read inventory by character id
	public List<InventoryDTO> getInventoryByCharacterId(int id) {
		List<Inventory> characterInventory = inventoryRepo.findByCharacterId(id);
		List<InventoryDTO> characterInventoryDTO = new ArrayList<>();
			for (Inventory inventory: characterInventory) {
				characterInventoryDTO.add(this.toDTO(inventory));
				return characterInventoryDTO;
		}
			throw new EntityNotFoundException("We can't find Character with id " + id + ", perhaps they are away on another adventure");
	}
	

	// update inventory
	public InventoryDTO updateInventory(NewInventoryDTO inventory, int id) {
		return null;
		
	}

	
}