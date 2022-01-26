// BindNestedProperty.java
package com.jdojo.binding;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BindNestedProperty {
	public static class Address {
		private StringProperty zip = new SimpleStringProperty("36106");

		public StringProperty zipProperty() {
			return zip;
		}

		public String getZip() {
			return zip.get();
		}

		public void setZip(String newZip) {
			zip.set(newZip);
		}
	}

	public static class Person {
		private ObjectProperty<Address> addr = new SimpleObjectProperty<>(new Address());

		public ObjectProperty<Address> addrProperty() {
			return addr;
		}

		public Address getAddr() {
			return addr.get();
		}

		public void setZip(Address newAddr) {
			addr.set(newAddr);
		}
	}

	public static void main(String[] args) {
		ObjectProperty<Person> p = new SimpleObjectProperty<>(new Person());
		
		// Bind p.addr.zip
		StringBinding zipBinding = Bindings.selectString(p, "addr", "zip");
		System.out.println(zipBinding.get());

		// Change the zip
		p.get().addrProperty().get().setZip("35217");
		System.out.println(zipBinding.get());

		// Bind p.addr.state, which does not exist
		StringBinding stateBinding = Bindings.selectString(p, "addr", "state");
		System.out.println(stateBinding.get());
	}
}
