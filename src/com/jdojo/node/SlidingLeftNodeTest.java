// SlidingLeftNodeTest.java
package com.jdojo.node;

import javafx.application.Application;
import javafx.beans.binding.When;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SlidingLeftNodeTest extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button b1 = new Button("B1");
		Button b2 = new Button("B2");
		Button b3 = new Button("B3");
		Button visibleBtn = new Button("Make Invisible");

		// Add an action listener to the button to make b2 visible
		// if it is invisible and invisible if it is visible
		visibleBtn.setOnAction(e -> b2.setVisible(!b2.isVisible()));

		// Bind the text property of the button to the visible
		// property of the b2 button
		visibleBtn.textProperty().bind(new When(b2.visibleProperty())
				.then("Make Invisible")
				.otherwise("Make Visible"));

		// Bind the managed property of b2 to its visible property
		b2.managedProperty().bind(b2.visibleProperty());

		HBox root = new HBox();
		root.getChildren().addAll(visibleBtn, b1, b2, b3);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Sliding to the Left");
		stage.show();
	}
}
