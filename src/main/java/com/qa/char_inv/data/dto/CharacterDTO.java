package com.qa.char_inv.data.dto;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.qa.char_inv.data.entity.Inventory;



public class CharacterDTO {
	
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
		public CharacterDTO() {
			super();
		}

		// full args constructor
		public CharacterDTO(int id,
				String name,
				int age,
				String genderIdentity,
				String species) {
			super();
			this.id = id;
			this.name = name;
			this.age = age;
			this.genderIdentity = genderIdentity;
			this.species = species;
		}

		//constructor without id as this will be generated for us
		public CharacterDTO(
				String name,
				int age,
				String genderIdentity,
				String species) {
			super();
			this.name = name;
			this.age = age;
			this.genderIdentity = genderIdentity;
			this.species = species;
		}
		
		@OneToOne(mappedBy = "inventory", // mappedBy is the name of the field in UserProfile representing the other side of the relationship
				  optional = false, // is the relationship optional
				  cascade = {CascadeType.ALL}, // cascade is used to cascade operations, for example if I delete a user, their user profile will also be automatically deleted
				  targetEntity = Inventory.class, // the type of the class on the other side of the relationship
				  fetch = FetchType.EAGER)
		@JsonProperty(access = Access.READ_WRITE)
		private Inventory inventory;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getGenderIdentity() {
			return genderIdentity;
		}

		public void setGenderIdentity(String genderIdentity) {
			this.genderIdentity = genderIdentity;
		}

		public String getSpecies() {
			return species;
		}

		public void setSpecies(String species) {
			this.species = species;
		}


		public Inventory getInventory() {
			return inventory;
		}

		public void setInventory(Inventory inventory) {
			this.inventory = inventory;
		}
	
}

	