package com.qa.char_inv.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity // specifies class is an entity
@Table(name = "inventory") // specifies primary table for entity and names it
public class Inventory {
	
	@Id // Primary Key
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
	private int id;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "inventory_items", // name of the join table
	           joinColumns = @JoinColumn(name = "inventory_id", referencedColumnName = "id"), // name of the key of the domain which owns the relationship
	           inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")) // inverse of above
	private List<Item> items;
	
	
	protected Inventory() {
		super();
		this.items = new ArrayList<>();
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
	
	public List<Item> getTags() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}


	@Override
	public String toString() {
		return "Inventory [id=" + id + ", items=" + items + "]";
	}
	
	

}