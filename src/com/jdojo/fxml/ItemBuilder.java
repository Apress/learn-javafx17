// ItemBuilder.java
package com.jdojo.fxml;

import javafx.util.Builder;

public class ItemBuilder implements Builder<Item> {
	private Long id;
	private String name;
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public Item build() {
		return new Item(id, name);
	}
}
