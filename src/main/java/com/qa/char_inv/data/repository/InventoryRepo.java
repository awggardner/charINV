package com.qa.char_inv.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.char_inv.data.entity.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory, Integer>{

	Inventory findByCharacterId(int id);
}
