package com.qa.char_inv.service;

import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import com.qa.char_inv.data.entity.Character;
import com.qa.char_inv.data.dto.CharacterDTO;
import com.qa.char_inv.data.repository.CharacterRepo;

@ExtendWith(MockitoExtension.class)
public class CharServiceUnitTest {
	
	@Mock
	private CharacterRepo characterRepo;
	
	@InjectMocks
	private CharacterService characterService;
	private List<Character> mockchars;
	private List<CharacterDTO> mockcharDTOs;
	
	@BeforeEach
	public void mock() {
		
		mockchars = List.of(
				new Character(1, "Bob", 21, "male", "human" ),
				new Character(2, "Cargis", 100, "uknown", "ethereal"),
				new Character(3, "Drunga", 43, "female", "ork"),
				new Character(4, "Edinald", 37, "non-binary", "Dwarf"));
		
		mockcharDTOs = List.of(
				new CharacterDTO(1, "Bob", 21, "male", "human" ),
				new CharacterDTO(2, "Cargis", 100, "uknown", "ethereal"),
				new CharacterDTO(3, "Drunga", 43, "female", "ork"),
				new CharacterDTO(4, "Edinald", 37, "non-binary", "Dwarf"));
			
		
	}
	
	
	
	
}
