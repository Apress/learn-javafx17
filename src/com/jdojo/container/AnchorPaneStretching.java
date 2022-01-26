// AnchorPaneStretching.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnchorPaneStretching extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button leftRight = new Button("A button");		
		AnchorPane.setTopAnchor(leftRight, 10.0);
		AnchorPane.setLeftAnchor(leftRight, 10.0);
		AnchorPane.setRightAnchor(leftRight, 10.0);

		AnchorPane root = new AnchorPane();
		root.getChildren().addAll(leftRight);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Streching Children in an AnchorPane");
		stage.show();
	}
}
