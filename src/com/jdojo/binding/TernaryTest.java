// TernaryTest.java
package com.jdojo.binding;

import javafx.beans.binding.When;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.binding.StringBinding;

public class TernaryTest {	
	public static void main(String[] args) {
		IntegerProperty num = new SimpleIntegerProperty(10);				
		StringBinding desc = new When(num.divide(2).multiply(2).isEqualTo(num))
		                        .then("even")
		                        .otherwise("odd");
		
		System.out.println(num.get() + " is " + desc.get());
		
		num.set(19);
		System.out.println(num.get() + " is " + desc.get());	
	}
}
