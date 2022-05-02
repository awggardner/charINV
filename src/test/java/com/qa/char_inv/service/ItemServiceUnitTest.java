package com.qa.char_inv.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.qa.char_inv.data.entity.Item;
import com.qa.char_inv.data.dto.ItemDTO;
import com.qa.char_inv.data.dto.NewItemDTO;
import com.qa.char_inv.data.repository.ItemRepo;

@ExtendWith(MockitoExtension.class)

public class ItemServiceUnitTest {
	
	@Mock
	private ItemRepo itemRepo;
	
	@Mock
	private ModelMapper modelMapper;
	
	@InjectMocks
	private ItemService mockitService;
	private List<Item> mockItems;
	private List<ItemDTO> mockItemsDTO;
	
	@BeforeEach
	public void mock() {
		
		mockItems = List.of(
				new Item(1, "healing potion", 
						"a favourite amongst adventurers, heals almost any injury",
						true),
				new Item(2, "purse",
						"for holding your gold",
						false),
				new Item(3, "dragonskin hat",
						"a magic hat that smells like it's on fire",
						true));
		
		mockItemsDTO = List.of(
				new ItemDTO(1, "healing potion", 
						"a favourite amongst adventurers, heals almost any injury",
						true),
				new ItemDTO(2, "purse",
						"for holding your gold",
						false),
				new ItemDTO(3, "dragonskin hat",
						"a magic hat that smells like it's on fire",
						true));
			
		}
	
	@Test // create
	public void testCreate() {
		Item item = mockItems.get(0);
		
		NewItemDTO newItemDTO = new NewItemDTO();
		newItemDTO.setItemName(item.getItemName());
		newItemDTO.setDescription(item.getDescription());
		newItemDTO.setMagic(item.getMagic());
		
		ItemDTO newMockItem = new ItemDTO
				(
				 item.getItemName(),
				 item.getDescription(),
				 item.getMagic()
				);
		
		when(modelMapper.map(newItemDTO, Item.class)).thenReturn(item);
		when(itemRepo.save(item)).thenReturn(item);
		when(modelMapper.map(item, ItemDTO.class)).thenReturn(newMockItem);
		
		ItemDTO actual = mockitService.createItem(newItemDTO);
		
		assertEquals(newMockItem, actual);
		verify(modelMapper).map(newItemDTO, Item.class);
		verify(itemRepo).save(item);
		verify(modelMapper).map(item, ItemDTO.class);
		
		
	}
	
	@Test // read all
	public void testReadAll() {
		when(itemRepo.findAll()).thenReturn(mockItems);
		when(modelMapper.map(mockItems.get(0), ItemDTO.class)).thenReturn(mockItemsDTO.get(0));
		when(modelMapper.map(mockItems.get(1), ItemDTO.class)).thenReturn(mockItemsDTO.get(1));
		when(modelMapper.map(mockItems.get(2), ItemDTO.class)).thenReturn(mockItemsDTO.get(2));
		
		
		List<ItemDTO> actual = mockitService.getItems();
		
		assertEquals(mockItemsDTO, actual);
		verify(itemRepo).findAll();
		verify(modelMapper).map(mockItems.get(0), ItemDTO.class);
		verify(modelMapper).map(mockItems.get(1), ItemDTO.class);
		verify(modelMapper).map(mockItems.get(2), ItemDTO.class);
		
	}
	
	@Test // read by id
	public void testReadbyId() {
		Item item = mockItems.get(1);
		ItemDTO mockcharDTO = mockItemsDTO.get(1);
		int id = item.getId();
		
		when(itemRepo.findById(id)).thenReturn(Optional.of(item));
		when(modelMapper.map(item, ItemDTO.class)).thenReturn(mockcharDTO);
		
		ItemDTO actual = mockitService.getItem(id);
		
		assertEquals(mockcharDTO, actual);
		verify(itemRepo).findById(id);
		verify(modelMapper).map(item, ItemDTO.class);
	}
	
	@Test // update
	public void testUpdate() {
		Item item = mockItems.get(2);
		int id = item.getId();
		
		NewItemDTO itemDTO = new NewItemDTO();
		itemDTO.setItemName(item.getItemName());
		itemDTO.setDescription(item.getDescription());
		itemDTO.setMagic(item.getMagic());
		
		
		ItemDTO updatedChar = new ItemDTO
				(
				 item.getItemName(),
				 item.getDescription(),
				 item.getMagic()
				);
		
		when(itemRepo.existsById(id)).thenReturn(true);
		when(itemRepo.getById(id)).thenReturn(item);
		when(modelMapper.map(item,  ItemDTO.class)).thenReturn(updatedChar);
		
		ItemDTO actual = mockitService.updateItem(itemDTO, id);
		
		assertEquals(updatedChar, actual);
		verify(itemRepo).existsById(id);
		verify(itemRepo).getById(id);
		verify(modelMapper).map(item, ItemDTO.class);
		
	}
	
	@Test // delete
	public void testDelete() {
		int deleteId = 3;
		when(itemRepo.existsById(deleteId)).thenReturn(true);
		
		mockitService.deleteItem(deleteId);
		
		verify(itemRepo).existsById(deleteId);
		
		
	}


}
	
	
	


