// BookPropertyTest.java
package com.jdojo.binding;

import javafx.beans.property.ReadOnlyProperty;

public class BookPropertyTest {
	public static void main(String[] args) {
		Book book = new Book("Harnessing JavaFX", 9.99, "0123456789");

		System.out.println("After creating the Book object...");

		// Print Property details
		printDetails(book.titleProperty());
		printDetails(book.priceProperty());
		printDetails(book.ISBNProperty());

		// Change the book's properties
		book.setTitle("Harnessing JavaFX 8.0");
		book.setPrice(9.49);

		System.out.println("\nAfter changing the Book properties...");

		// Print Property details
		printDetails(book.titleProperty());
		printDetails(book.priceProperty());
		printDetails(book.ISBNProperty());
	}

	public static void printDetails(ReadOnlyProperty<?> p) {
		String name = p.getName();
		Object value = p.getValue();
		Object bean = p.getBean();
		String beanClassName
				= (bean == null) ? "null" : bean.getClass().getSimpleName();
		String propClassName = p.getClass().getSimpleName();

		System.out.print(propClassName);
		System.out.print("[Name:" + name);
		System.out.print(", Bean Class:" + beanClassName);
		System.out.println(", Value:" + value + "]");
	}
}
