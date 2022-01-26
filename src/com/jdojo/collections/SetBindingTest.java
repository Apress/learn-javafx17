// SetBindingTest.java
package com.jdojo.collections;

import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class SetBindingTest {
	public static void main(String[] args) {
		SetProperty<String> sp1 = 
			new SimpleSetProperty<>(FXCollections.observableSet());
		
		// Bind the size and empty properties of the SetProperty
		// to create a description of the set
		StringProperty initStr = new SimpleStringProperty("Size: " );
		StringProperty desc = new SimpleStringProperty();
		desc.bind(initStr.concat(sp1.sizeProperty())
		                  .concat(", Empty: ")
		                  .concat(sp1.emptyProperty())
		                  .concat(", Set: " )
		                  .concat(sp1.asString())
		         );
		
		System.out.println("Before sp1.add(): " + desc.get());
		sp1.add("John");
		sp1.add("Jacobs");
		System.out.println("After sp1.add(): " + desc.get());
		
		SetProperty<String> sp2 = 
			new SimpleSetProperty<>(FXCollections.observableSet());
		
		// Bind the content of sp1 to the content of sp2
		sp1.bindContent(sp2);	    
		System.out.println("Called sp1.bindContent(sp2)...");
		
		/* At this point, you can change the content of sp1. However, 
		 * that will defeat the purpose of content binding, because the
		 * content of sp1 is no longer in sync with the content of sp2.
		 * Do not do this:
		 * sp1.add("X"); 
		 */	
		print("Before sp2.add():", sp1, sp2);
		sp2.add("1");
		print("After sp2.add():", sp1, sp2);
		
		sp1.unbindContent(sp2);         
		print("After sp1.unbindContent(sp2):", sp1, sp2);

		// Bind sp1 and sp2 contents bidirectionally
		sp1.bindContentBidirectional(sp2);
		
		print("Before sp2.add():", sp1, sp2);
		sp2.add("2");		
		print("After sp2.add():", sp1, sp2);
	}
	
	public static void print(String msg, SetProperty<String> sp1, SetProperty<String> sp2) {
		System.out.println(msg + " sp1: " + sp1.get() + ", sp2: " + sp2.get());                           
	}
}
