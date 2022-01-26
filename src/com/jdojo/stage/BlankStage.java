// BlankStage.java
package com.jdojo.stage;

import javafx.application.Application;
import javafx.stage.Stage;

public class BlankStage extends Application {
	public static void main(String[] args) {
		Application.launch(args); 
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("Blank Stage");
		stage.show();		
	}
}
