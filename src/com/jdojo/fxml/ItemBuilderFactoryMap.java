// ItemBuilderFactoryMap.java
package com.jdojo.fxml;

import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;
import javafx.util.BuilderFactory;

public class ItemBuilderFactoryMap implements BuilderFactory {
	private final JavaFXBuilderFactory fxFactory = new JavaFXBuilderFactory();

	@Override
	public Builder<?> getBuilder(Class<?> type) {
		if (type == Item.class) {
			return new ItemBuilderMap();
		}
		return fxFactory.getBuilder(type);
	}
}
