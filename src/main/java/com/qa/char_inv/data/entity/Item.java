package com.qa.char_inv.data.entity;

import javax.validation.constraints.*;
import javax.persistence.*;

@Entity // specifies class is an entity
@Table(name = "item") // specifies primary table for entity and names it
public class Item {
	
	@Id // sets primary key
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // acts as our AUTO INCREMENT in mySQL
	private int id;
	
	@NotNull // NOT NULL - must have an entry
	@NotBlank // Entry cannot be blank (whitespace)
	@Size(min = 3, max = 26, message = "please insert an item name between 3 and 26 characters long")
	private String itemName;
	
	@NotNull
	@NotBlank
	@Max(value = 200, message = "please provide a description of the item, less than 200 characters")
	private String description;
	
	@NotNull
	@NotBlank
	@Max(value = 20, message = "If item is magic 'true' if not 'false'")
	private boolean isMagic;

	
	//empty constructor
	public Item() {
		super();
	}

	
	//full args constructor
	public Item(int id,
			@NotNull @NotBlank @Size(min = 3, max = 26, message = "please insert an item name between 3 and 26 characters long") String itemName,
			@NotNull @NotBlank @Max(value = 200, message = "please provide a description of the item, less than 200 characters") String description,
			@NotNull @NotBlank @Max(value = 20, message = "If item is magic 'true' if not 'false'") boolean isMagic) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.description = description;
		this.isMagic = isMagic;
	}

	
	//constructor without id as this will be generated for us
	public Item(
			@NotNull @NotBlank @Size(min = 3, max = 26, message = "please insert an item name between 3 and 26 characters long") String itemName,
			@NotNull @NotBlank @Max(value = 200, message = "please provide a description of the item, less than 200 characters") String description,
			@NotNull @NotBlank @Max(value = 20, message = "If item is magic 'true' if not 'false'") boolean isMagic) {
		super();
		this.itemName = itemName;
		this.description = description;
		this.isMagic = isMagic;
	}
	
	
	

}
