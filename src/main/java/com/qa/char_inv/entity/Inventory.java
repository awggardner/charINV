package com.qa.char_inv.entity;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	

}
