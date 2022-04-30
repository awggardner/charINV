package com.qa.char_inv.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.char_inv.data.dto.CharacterDTO;
import com.qa.char_inv.data.dto.NewCharacterDTO;
import com.qa.char_inv.service.CharacterService;

@RestController
@RequestMapping(path = "/character") // accepts requests at localhost:8090/character
@CrossOrigin("*")
public class CharacterControl {
	
	private CharacterService characterService;
	
	@Autowired // Instructs the Spring IoC container to inject the required dependency
	public CharacterControl(CharacterService characterService) {
		this.characterService = characterService;
	}
	
	@GetMapping
	// read all
	public ResponseEntity<List<CharacterDTO>> getCharacters() {
		return ResponseEntity.ok(characterService.getCharacters());
	}
	
	@GetMapping(path = "/{id}")
	// read by id
	public ResponseEntity<CharacterDTO> getCharacter(@PathVariable(name = "id") int id) {
		CharacterDTO character = characterService.getCharacter(id);
		return new ResponseEntity<>(character, HttpStatus.OK);
	}
	
	
	@PostMapping
	// create
	public ResponseEntity<CharacterDTO> createCharacter(@Valid @RequestBody NewCharacterDTO character) {
		CharacterDTO newCharacter = characterService.createCharacter(character);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8090/character/" + newCharacter.getId());

		return new ResponseEntity<>(newCharacter, headers, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	// update
	public ResponseEntity<CharacterDTO> updateCharacter(@RequestBody NewCharacterDTO character, @PathVariable(name = "id") int id) {
		
		return ResponseEntity.ok(characterService.updateCharacter(character, id));
	}
	
	@DeleteMapping(path = "/{id}")
	// delete
	public ResponseEntity<?> deleteCharacter(@PathVariable(name = "id") int id) {
		CharacterDTO characterDeleted = characterService.getCharacter(id);
		characterService.deleteCharacter(id);
		return ResponseEntity.ok(characterDeleted);
	}
}
