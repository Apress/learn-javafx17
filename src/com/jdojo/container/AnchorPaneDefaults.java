// AnchorPaneDefaults.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnchorPaneDefaults extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button bigBtn = new Button("This is a big button.");
		Button smallBtn = new Button("Small button");

		// Create an AnchorPane with two buttons
		AnchorPane root = new AnchorPane(bigBtn, smallBtn);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Defaults in AnchorPane");
		stage.show();
	}
}
