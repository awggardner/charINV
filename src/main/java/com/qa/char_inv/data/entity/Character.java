package com.qa.char_inv.data.entity;

import javax.validation.constraints.*;
import javax.persistence.*;

@Entity
@Table(name = "character")
public class Character {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotBlank
	@Size(min = 3, max = 20, message = "please insert a character name between 3 and 20 characters long")
	private String name;
	
	@NotEmpty
	@Min(value = 18, message = "Age should be no less than 18")
    @Max(value = 2000, message = "Age should not be greater than 2000")
    private int age;
	
	@NotNull
	@NotBlank
	@Max(value = 20)
	private String genderIdentity;
	
	@NotNull
	@NotBlank
	@Max(value = 20, message = "Choose a species for your character, ie. human, elf, dwarf etc. Feel free to make up your own as long as it's less than 20 characters long")
	private String species;
	

}