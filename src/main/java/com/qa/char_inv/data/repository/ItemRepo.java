package com.qa.char_inv.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.char_inv.data.entity.Item;

public interface ItemRepo extends JpaRepository<Item, Integer>{
	
	List<Item> findByInventoryId(int id);

}
