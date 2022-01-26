// ItemBuilderFactory.java
package com.jdojo.fxml;

import javafx.util.Builder;
import javafx.util.BuilderFactory;
import javafx.fxml.JavaFXBuilderFactory;

public class ItemBuilderFactory implements BuilderFactory {
	private final JavaFXBuilderFactory fxFactory = new JavaFXBuilderFactory();

	@Override
	public Builder<?> getBuilder(Class<?> type) {
		// You supply a Builder only for Item type
		if (type == Item.class) {
			return new ItemBuilder();
		}

		// Let the default Builder do the magic
		return fxFactory.getBuilder(type);
	}
}
