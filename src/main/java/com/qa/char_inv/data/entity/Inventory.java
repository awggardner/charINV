package com.qa.char_inv.data.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // specifies class is an entity
@Table(name = "inventory") // specifies primary table for entity and names it
public class Inventory implements List<Inventory> {
	
	@Id // Primary Key
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
	private int id;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "inventory_items", // name of the join table
	           joinColumns = @JoinColumn(name = "inventory_id", referencedColumnName = "id"), // name of the key of the domain which owns the relationship
	           inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")) // inverse of above
	private List<Item> items;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "character_id") 
	@JsonIgnore 
	
	
	private Character character;
	
	
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

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public List<Item> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", items=" + items + "]";
	}


	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Iterator<Inventory> iterator() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean add(Inventory e) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean addAll(Collection<? extends Inventory> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean addAll(int index, Collection<? extends Inventory> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Inventory get(int index) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Inventory set(int index, Inventory element) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void add(int index, Inventory element) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Inventory remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public ListIterator<Inventory> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ListIterator<Inventory> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Inventory> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}


	public void setCharacter(java.lang.Character character2) {
		// TODO Auto-generated method stub
		
	}
	
	

}