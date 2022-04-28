package com.qa.char_inv.data.entity;

import javax.validation.constraints.*;
import javax.persistence.*;

@Entity
@Table(name = "character")
public class Character {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
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

	
	//empty constructor
	public Character() {
		super();
	}

	// all args constructor
	public Character(int id,
			@NotNull @NotBlank @Size(min = 3, max = 20, message = "please insert a character name between 3 and 20 characters long") String name,
			@NotEmpty @Min(value = 18, message = "Age should be no less than 18") @Max(value = 2000, message = "Age should not be greater than 2000") int age,
			@NotNull @NotBlank @Max(20) String genderIdentity,
			@NotNull @NotBlank @Max(value = 20, message = "Choose a species for your character, ie. human, elf, dwarf etc. Feel free to make up your own as long as it's less than 20 characters long") String species) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.genderIdentity = genderIdentity;
		this.species = species;
	}

	//constructor without id as this will be generated for us
	public Character(
			@NotNull @NotBlank @Size(min = 3, max = 20, message = "please insert a character name between 3 and 20 characters long") String name,
			@NotEmpty @Min(value = 18, message = "Age should be no less than 18") @Max(value = 2000, message = "Age should not be greater than 2000") int age,
			@NotNull @NotBlank @Max(20) String genderIdentity,
			@NotNull @NotBlank @Max(value = 20, message = "Choose a species for your character, ie. human, elf, dwarf etc. Feel free to make up your own as long as it's less than 20 characters long") String species) {
		super();
		this.name = name;
		this.age = age;
		this.genderIdentity = genderIdentity;
		this.species = species;
	}

	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", age=" + age + ", genderIdentity=" + genderIdentity
				+ ", species=" + species + "]";
	}
	
	
	

}