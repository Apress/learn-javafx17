// BindingToListElements.java
package com.jdojo.collections;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class BindingToListElements {
	public static void main(String[] args) {
		ListProperty<String> lp = 
			new SimpleListProperty<>(FXCollections.observableArrayList());
		
		// Create a binding to the last element of the list
		ObjectBinding<String> last = lp.valueAt(lp.sizeProperty().subtract(1));
		System.out.println("List:" + lp.get() + ", Last Value: " + last.get());
		
		lp.add("John");
		System.out.println("List:" + lp.get() + ", Last Value: " + last.get());
		
		lp.addAll("Donna", "Geshan");
		System.out.println("List:" + lp.get() + ", Last Value: " + last.get());

		lp.remove("Geshan");
		System.out.println("List:" + lp.get() + ", Last Value: " + last.get());
		
		lp.clear();
		System.out.println("List:" + lp.get() + ", Last Value: " + last.get());
	}
}
