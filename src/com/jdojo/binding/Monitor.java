// Monitor.java
package com.jdojo.binding;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Monitor {
	public static final String DEFAULT_SCREEN_TYPE = "flat";
	private StringProperty screenType;

	public String getScreenType() {
		return (screenType == null) ? DEFAULT_SCREEN_TYPE : screenType.get();
	}

	public void setScreenType(String newScreenType) {
		if (screenType != null || !DEFAULT_SCREEN_TYPE.equals(newScreenType)) {
			screenTypeProperty().set(newScreenType);
		}
	}

	public StringProperty screenTypeProperty() {
		if (screenType == null) {
			screenType = new SimpleStringProperty(this, "screenType",
					DEFAULT_SCREEN_TYPE);
		}

		return screenType;
	}
}
