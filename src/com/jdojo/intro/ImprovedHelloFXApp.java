// ImprovedHelloFXApp.java
package com.jdojo.intro;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImprovedHelloFXApp extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label nameLbl = new Label("Enter your name:");
		TextField nameFld = new TextField();

		Label msg = new Label();
		msg.setStyle("-fx-text-fill: blue;");

		// Create buttons 
		Button sayHelloBtn = new Button("Say Hello");
		Button exitBtn = new Button("Exit");

		// Add the event handler for the Say Hello button
		sayHelloBtn.setOnAction(e -> {
			String name = nameFld.getText();
			if (name.trim().length() > 0) {
				msg.setText("Hello " + name);
			} else {
				msg.setText("Hello there");
			}
		});

		// Add the event handler for the Exit button
		exitBtn.setOnAction(e -> Platform.exit());

		// Create the root node
		VBox root = new VBox();

		// Set the vertical spacing between children to 5px
		root.setSpacing(5);

		// Add children to the root node
		root.getChildren().addAll(nameLbl, nameFld, msg, sayHelloBtn, exitBtn);

		Scene scene = new Scene(root, 350, 150);
		stage.setScene(scene);
		stage.setTitle("Improved Hello JavaFX Application");
		stage.show();
	}
}
