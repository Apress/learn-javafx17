// StackPaneAlignment.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StackPaneAlignment extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		StackPane topLeft = createStackPane(Pos.TOP_LEFT);
		StackPane topRight = createStackPane(Pos.TOP_RIGHT);
		StackPane bottomLeft = createStackPane(Pos.BOTTOM_LEFT);
		StackPane bottomRight = createStackPane(Pos.BOTTOM_RIGHT);
		StackPane center = createStackPane(Pos.CENTER);

		double spacing = 10.0;
		HBox root = new HBox(spacing, 
		                     topLeft, 
		                     topRight, 
		                     bottomLeft, 
		                     bottomRight, 
		                     center);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using StackPane");
		stage.show();
	}

	public StackPane createStackPane(Pos alignment) {
		Rectangle rect = new Rectangle(80, 50);
		rect.setFill(Color.LAVENDER);

		Text text = new Text(alignment.toString());
		text.setStyle("-fx-font-size: 7pt;");

		StackPane spane = new StackPane(rect, text);
		spane.setAlignment(alignment);
	 	spane.setStyle("-fx-padding: 10;" + 
		               "-fx-border-style: solid inside;" + 
		               "-fx-border-width: 2;" +
		               "-fx-border-insets: 5;" + 
		               "-fx-border-radius: 5;" + 
		               "-fx-border-color: blue;");
		return spane;
	}
}
