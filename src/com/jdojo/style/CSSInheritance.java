// CSSInheritance.java
package com.jdojo.style;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CSSInheritance extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");

		HBox root = new HBox(10); // 10px spacing
		root.getChildren().addAll(okBtn, cancelBtn);

		// Set styles for the OK button and its parent HBox
		root.setStyle("-fx-cursor: hand;-fx-border-color: blue;-fx-border-width: 5px;");
		okBtn.setStyle("-fx-border-color: red;-fx-border-width: inherit;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("CSS Inheritance");
		stage.show();
	}
}
