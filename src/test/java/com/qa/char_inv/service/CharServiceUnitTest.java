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
import com.qa.char_inv.data.entity.Character;
import com.qa.char_inv.data.dto.CharacterDTO;
import com.qa.char_inv.data.dto.NewCharacterDTO;
import com.qa.char_inv.data.repository.CharacterRepo;

@ExtendWith(MockitoExtension.class)
public class CharServiceUnitTest {
	
	@Mock
	private CharacterRepo characterRepo;
	
	@Mock
	private ModelMapper modelMapper;
	
	@InjectMocks
	private CharacterService mockcharService;
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
	
	@Test //create
	public void createCharTest() {
		Character character = mockchars.get(0);
		
		NewCharacterDTO newcharDTO = new NewCharacterDTO();
		newcharDTO.setName(character.getName());
		newcharDTO.setAge(character.getAge());
		newcharDTO.setGenderIdentity(character.getGenderIdentity());
		newcharDTO.setSpecies(character.getSpecies());
		
		CharacterDTO newMockchar = new CharacterDTO
				(
				 character.getName(),
				 character.getAge(),
				 character.getGenderIdentity(),
				 character.getSpecies()
				 );
		
		when(modelMapper.map(newcharDTO, Character.class)).thenReturn(character);
		when(characterRepo.save(character)).thenReturn(character);
		when(modelMapper.map(character, CharacterDTO.class)).thenReturn(newMockchar);
		
		CharacterDTO actual = mockcharService.createCharacter(newcharDTO);
		
		assertEquals(newMockchar, actual);
		verify(modelMapper).map(newcharDTO, Character.class);
		verify(characterRepo).save(character);
		verify(modelMapper).map(character, CharacterDTO.class);
		
		
	}
	
	@Test // read all
	public void testReadAll() {
		when(characterRepo.findAll()).thenReturn(mockchars);
		when(modelMapper.map(mockchars.get(0), CharacterDTO.class)).thenReturn(mockcharDTOs.get(0));
		when(modelMapper.map(mockchars.get(1), CharacterDTO.class)).thenReturn(mockcharDTOs.get(1));
		when(modelMapper.map(mockchars.get(2), CharacterDTO.class)).thenReturn(mockcharDTOs.get(2));
		when(modelMapper.map(mockchars.get(3), CharacterDTO.class)).thenReturn(mockcharDTOs.get(3));
		
		List<CharacterDTO> actual = mockcharService.getCharacters();
		
		assertEquals(mockcharDTOs, actual);
		verify(characterRepo).findAll();
		verify(modelMapper).map(mockchars.get(0), CharacterDTO.class);
		verify(modelMapper).map(mockchars.get(1), CharacterDTO.class);
		verify(modelMapper).map(mockchars.get(2), CharacterDTO.class);
		verify(modelMapper).map(mockchars.get(3), CharacterDTO.class);
	}
	
	@Test // read by id
	public void testReadbyId() {
		Character character = mockchars.get(3);
		CharacterDTO mockcharDTO = mockcharDTOs.get(3);
		int id = character.getId();
		
		when(characterRepo.findById(id)).thenReturn(Optional.of(character));
		when(modelMapper.map(character, CharacterDTO.class)).thenReturn(mockcharDTO);
		
		CharacterDTO actual = mockcharService.getCharacter(id);
		
		assertEquals(mockcharDTO, actual);
		verify(characterRepo).findById(id);
		verify(modelMapper).map(character, CharacterDTO.class);
	}
	

}
