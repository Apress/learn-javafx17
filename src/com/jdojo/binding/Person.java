// Person.java
package com.jdojo.binding;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Person {
	private ObjectProperty<Address> addr = new SimpleObjectProperty<>(new Address());

	public ObjectProperty<Address> addrProperty() {
		return addr;
	}
}
