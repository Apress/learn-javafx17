// TilePaneAlignmentConstraint.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TilePaneAlignmentConstraint extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button b12 = new Button("One\nTwo");
		Button b3 = new Button("Three");
		Button b4 = new Button("Four");
		Button b5 = new Button("Five");
		Button b6 = new Button("Six");

		// Set the tile alignment constraint on b3 to BOTTOM_RIGHT
		TilePane.setAlignment(b3, Pos.BOTTOM_RIGHT);

		TilePane root = new TilePane(b12, b3, b4, b5, b6);
		root.setPrefColumns(3);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Alignment Constraints in TilePane");
		stage.show();
	}
}
