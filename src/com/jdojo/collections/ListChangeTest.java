// ListChangeTest.java
package com.jdojo.collections;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class ListChangeTest {
	public static void main(String[] args) {
		Callback<Person, Observable[]> cb = 
			(Person p) -> new Observable[] {
				p.firstNameProperty(), 
				p.lastNameProperty()
			};

		// Create a list
		ObservableList<Person> list = FXCollections.observableArrayList(cb);
		
		// Add a change listener to the list
		list.addListener(new PersonListChangeListener());

		Person p1 = new Person("Li", "Na");
		System.out.println("Before adding " + p1 + ": " + list);
		list.add(p1);
		System.out.println("After adding " + p1 + ": " + list) ; 
		
		Person p2 = new Person("Vivi", "Gin");
		Person p3 = new Person("Li", "He");
		System.out.println("\nBefore adding " + p2 + " and " + p3 + ": " + list);
		list.addAll(p2, p3);
		System.out.println("After adding " + p2 + " and " + p3 + ": " + list);  
		
		System.out.println("\nBefore sorting the list:" + list);		
		FXCollections.sort(list);			    
		System.out.println("After sorting the list:" + list);
		
		System.out.println("\nBefore updating " + p1 + ": " + list);
		p1.setLastName("Smith");		
		System.out.println("After updating " + p1 + ": " + list);
		
		Person p = list.get(0);
		Person p4 = new Person("Simon", "Ng");
		System.out.println("\nBefore replacing " + p + 
		                   " with " + p4 + ": " + list);
		list.set(0, p4);	    
		System.out.println("After replacing " + p + " with " + p4 + ": " + list);
		
		System.out.println("\nBefore setAll(): " + list);
		Person p5 = new Person("Lia", "Li");
		Person p6 = new Person("Liz", "Na");
		Person p7 = new Person("Li", "Ho");
		list.setAll(p5, p6, p7);
		System.out.println("After setAll(): " + list);
		
		System.out.println("\nBefore removeAll(): " + list);
		list.removeAll(p5, p7); // Leave p6 in the list
		System.out.println("After removeAll(): " + list);		
	}
}
