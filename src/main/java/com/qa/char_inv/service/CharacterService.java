package com.qa.char_inv.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.qa.char_inv.data.entity.Character;
import com.qa.char_inv.data.entity.Inventory;
import com.qa.char_inv.data.repository.CharacterRepo;

@Service // specialisation of @Component, registers this class as a bean
public class CharacterService {

	private CharacterRepo characterRepo;
	
	@Autowired // Instructs the Spring IoC container to inject the required dependency
	public CharacterService(CharacterRepo characterRepo) {
		this.characterRepo = characterRepo;
	}
	
	public List<Character> readAll() {
		return characterRepo.findAll();
	}
	
	public Character readById(int id) {
		Optional<Character> character = characterRepo.findById(id);
		
		if (character.isPresent()) {
			return character.get();
		}
		throw new EntityNotFoundException("Character with id " + id + " was not found");
	}
	
	public Inventory readInventoryByCharacterId(int id) {
		Character character = this.readById(id);
		return character.getInventory();
	}
	
	public Character create(Character character) {
		return characterRepo.save(character);
	}
	
}