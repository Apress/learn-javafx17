// AnchorPaneTest.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnchorPaneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button topLeft = new Button("Top Left");
		AnchorPane.setTopAnchor(topLeft, 10.0);
		AnchorPane.setLeftAnchor(topLeft, 10.0);

		Button bottomRight = new Button("Botton Right");
		AnchorPane.setBottomAnchor(bottomRight, 10.0);
		AnchorPane.setRightAnchor(bottomRight, 10.0);

		AnchorPane root = new AnchorPane();
		root.getChildren().addAll(topLeft, bottomRight);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using an AnchorPane");
		stage.show();
	}
}
