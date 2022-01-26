// StackPaneAlignmentConstraint.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StackPaneAlignmentConstraint extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect = new Rectangle(200, 60);
		rect.setFill(Color.LAVENDER);

		// Create a Text node with the default CENTER alignment
		Text center = new Text("Center"); 

		// Create a Text node with a TOP_LEFT alignemnt constraint
		Text topLeft = new Text("top-left");
		StackPane.setAlignment(topLeft, Pos.TOP_LEFT);
		
		// Create a Text node with a BOTTOM_LEFT alignemnt constraint
		Text bottomRight = new Text("bottom-right");
		StackPane.setAlignment(bottomRight, Pos.BOTTOM_RIGHT);

		StackPane root = new StackPane(rect, center, topLeft, bottomRight);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("StackPane Alignment Constraint");
		stage.show();
	}
}
