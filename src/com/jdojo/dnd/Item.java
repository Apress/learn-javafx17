// Item.java
package com.jdojo.dnd;

import java.io.Serializable;

public class Item implements Serializable {
	private String name = "Unknown";

	public Item(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
