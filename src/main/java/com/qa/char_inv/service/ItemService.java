package com.qa.char_inv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.char_inv.data.dto.ItemDTO;
import com.qa.char_inv.data.dto.NewItemDTO;
import com.qa.char_inv.data.entity.Item;
import com.qa.char_inv.data.repository.ItemRepo;

@Service
public class ItemService {
	
	private ItemRepo itemRepo;
	private ModelMapper modelMapper;
	
	@Autowired
	public ItemService(ItemRepo itemRepo, ModelMapper modelmapper) {
		super();
		this.itemRepo = itemRepo;
		this.modelMapper = modelmapper;
	}

	// Item toDTO
		private ItemDTO toDTO(Item item) {
			return this.modelMapper.map(item, ItemDTO.class);
		}
		
		// create item
		public ItemDTO createItem(NewItemDTO item) {
			Item create = this.modelMapper.map(item,  Item.class);
			Item newItem = itemRepo.save(create);
			return this.toDTO(newItem);
		}
		
		
		// read all items
		public List<ItemDTO> getItems() {
			List<Item> items = itemRepo.findAll();
			List<ItemDTO> itemsDTO = new ArrayList<>();
				for (Item item: items) {
					itemsDTO.add(this.toDTO(item));
				}
				return itemsDTO;
		}
		
		// read by id
		public ItemDTO getItem(int id) {
			Optional<Item> item = itemRepo.findById(id);
			
			if (item.isPresent()) {
				return this.toDTO(item.get());
			}
			throw new EntityNotFoundException("We can't find Item with id " + id + ", did you drop it?");
		}
		
		// read by inventory id
		public List<ItemDTO> getItemsByInvId(int id) {
			List<Item> items = itemRepo.findByInventoryId(id);
			List<ItemDTO> inventoryItems = new ArrayList<>();
				for (Item item : items) {
					inventoryItems.add(this.toDTO(item));
					return inventoryItems;
				}
				throw new EntityNotFoundException("This inventory seems to be empty");
		}
		
		
		// update item
		public ItemDTO updateItem(NewItemDTO item, int id) {
			if (itemRepo.existsById(id)) {
				Item oldItem = itemRepo.getById(id);
				oldItem.setItemName(item.getItemName());
				oldItem.setDescription(item.getDescription());
				oldItem.setMagic(item.getMagic());
				return this.toDTO(oldItem);
			}
			throw new EntityNotFoundException("We can't find Item with id " + id + ", perhaps this item is invisible...");
		}
		
		// delete item
		public void deleteItem(int id) {
			if (itemRepo.existsById(id)) {
				itemRepo.deleteById(id);
			}
			throw new EntityNotFoundException("We can't find Item with id " + id + ", perhaps it ran off");
		}
}
