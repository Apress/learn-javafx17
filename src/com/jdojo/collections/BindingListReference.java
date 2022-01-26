// BindingListReference.java
package com.jdojo.collections;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class BindingListReference {

	public static void main(String[] args) {
		ListProperty<String> lp1 = 
			new SimpleListProperty<>(FXCollections.observableArrayList());
		ListProperty<String> lp2 = 
			new SimpleListProperty<>(FXCollections.observableArrayList());

		lp1.bind(lp2);

		print("Before addAll():", lp1, lp2);
		lp1.addAll("One", "Two");
		print("After addAll():", lp1, lp2);

		// Change the reference of the ObservableList in lp2
		lp2.set(FXCollections.observableArrayList("1", "2"));
		print("After lp2.set():", lp1, lp2);

		// Cannot do the following as lp1 is a bound property
		// lp1.set(FXCollections.observableArrayList("1", "2"));
		// Unbind lp1
		lp1.unbind();
		print("After unbind():", lp1, lp2);

		// Bind lp1 and lp2 bidirectionally
		lp1.bindBidirectional(lp2);
		print("After bindBidirectional():", lp1, lp2);

		lp1.set(FXCollections.observableArrayList("X", "Y"));
		print("After lp1.set():", lp1, lp2);
	}

	public static void print(String msg, ListProperty<String> lp1, ListProperty<String> lp2) {
		System.out.println(msg);
		System.out.println("lp1: " + lp1.get() + ", lp2: " + lp2.get() + 
		                   ", lp1.get() == lp2.get(): " + (lp1.get() == lp2.get()));
		System.out.println("---------------------------");
	}
}
