package com.qa.char_inv.data.entity;

import javax.persistence.*;

@Entity // specifies class is an entity
@Table(name = "inventory") // specifies primary table for entity and names it
public class Inventory {
	
	@Id // Primary Key
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
	private int id;

	//empty constructor
	public Inventory() {
		super();
	}

	public Inventory(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}