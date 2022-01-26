// BindingListContent.java
package com.jdojo.collections;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class BindingListContent {

	public static void main(String[] args) {
		ListProperty<String> lp1 = 
			new SimpleListProperty<>(FXCollections.observableArrayList());
		ListProperty<String> lp2 =
			new SimpleListProperty<>(FXCollections.observableArrayList());

		// Bind the content of lp1 to the content of lp2
		lp1.bindContent(lp2);

		/* At this point, you can change the content of lp1. However, 
		 * that will defeat the purpose of content binding, because the
		 * content of lp1 is no longer in sync with the content of lp2.
		 * Do not do this:
		 * lp1.addAll("X", "Y");
		 */
		print("Before lp2.addAll():", lp1, lp2);
		lp2.addAll("1", "2");
		print("After lp2.addAll():", lp1, lp2);

		lp1.unbindContent(lp2);
		print("After lp1.unbindContent(lp2):", lp1, lp2);

		// Bind lp1 and lp2 contents bidirectionally
		lp1.bindContentBidirectional(lp2);

		print("Before lp1.addAll():", lp1, lp2);
		lp1.addAll("3", "4");
		print("After lp1.addAll():", lp1, lp2);

		print("Before lp2.addAll():", lp1, lp2);
		lp2.addAll("5", "6");
		print("After lp2.addAll():", lp1, lp2);
	}

	public static void print(String msg, ListProperty<String> lp1, ListProperty<String> lp2) {
		System.out.println(msg + " lp1: " + lp1.get() + ", lp2: " + lp2.get());
	}
}
