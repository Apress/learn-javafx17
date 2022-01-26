// PaneTest.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PaneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");
		okBtn.relocate(10, 10);
		cancelBtn.relocate(60, 10);

		Pane root = new Pane();
		root.getChildren().addAll(okBtn, cancelBtn);
		root.setStyle("-fx-border-style: solid inside;" + 
		              "-fx-border-width: 3;" + 
		              "-fx-border-color: red;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Panes");
		stage.show();
	}
}
