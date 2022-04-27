package com.qa.char_inv.entity;

import javax.validation.constraints.*;
import javax.persistence.*;

public class Item {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotBlank
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
	

}