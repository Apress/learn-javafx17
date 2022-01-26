// HelloFXApp.java
package com.jdojo.intro;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloFXApp extends Application {
	public static void main(String[] args) {
		// Launch the JavaFX application
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("Hello JavaFX Application");
		stage.show();
	}
}
