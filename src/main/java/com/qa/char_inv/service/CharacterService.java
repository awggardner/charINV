package com.qa.char_inv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.char_inv.data.dto.CharacterDTO;
import com.qa.char_inv.data.dto.NewCharacterDTO;
import com.qa.char_inv.data.entity.Character;
import com.qa.char_inv.data.repository.CharacterRepo;

@Service
public class CharacterService {

	private CharacterRepo characterRepo;
	private ModelMapper modelMapper;
	
	@Autowired 
	public CharacterService(CharacterRepo characterRepo, ModelMapper modelMapper, InventoryService inventoryService) {
		super();
		this.characterRepo = characterRepo;
		this.modelMapper = modelMapper;
		
	}
	
	// Character toDTO
	private CharacterDTO toDTO(Character character) {
		return this.modelMapper.map(character, CharacterDTO.class);
	}
	
	// create character
	public CharacterDTO createCharacter(NewCharacterDTO character) {
		Character create = this.modelMapper.map(character,  Character.class);
		Character newCharacter = characterRepo.save(create);
		return this.toDTO(newCharacter);
	}
	

	public List<CharacterDTO> getCharacters() {
		List<Character> characters = characterRepo.findAll();
		List<CharacterDTO> charactersDTO = new ArrayList<>();
			for (Character character: characters) {
				charactersDTO.add(this.toDTO(character));
			}
			return charactersDTO;
	}
	
	// read by id
	public CharacterDTO getCharacter(int id) {
		Optional<Character> character = characterRepo.findById(id);
		
		if (character.isPresent()) {
			return this.toDTO(character.get());
		}
		throw new EntityNotFoundException("We can't find Character with id " + id + ", perhaps they are away on another adventure");
	}
	
	

	
	// update character
	public CharacterDTO updateCharacter(NewCharacterDTO character, int id) {
		if (characterRepo.existsById(id)) {
			Character oldCharacter = characterRepo.getById(id);
			oldCharacter.setName(character.getName());
			oldCharacter.setAge(character.getAge());
			oldCharacter.setGenderIdentity(character.getGenderIdentity());
			oldCharacter.setInventory(character.getInventory());
			return this.toDTO(oldCharacter);
		}
		throw new EntityNotFoundException("We can't find Character with id " + id + ", perhaps they were born anew on another adventure");
	}
	
	// delete character
	public void deleteCharacter(int id) {
		if (characterRepo.existsById(id)) {
			characterRepo.deleteById(id);
			return;
		}
		throw new EntityNotFoundException("We can't find Character with id " + id + ", perhaps they are trapped forever in a dungeon");
	}
	
	
}