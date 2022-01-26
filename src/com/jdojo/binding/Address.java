// Address.java
package com.jdojo.binding;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Address {
	private StringProperty zip = new SimpleStringProperty("36106");

	public StringProperty zipProperty() {
		return zip;
	}
}

