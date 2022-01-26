// ComboBoxCellFactory.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ComboBoxCellFactory extends Application {			
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label shapeLbl = new Label("Shape:");
		ComboBox<String> shapes = new ComboBox<>();		
		shapes.getItems().addAll("Line", "Rectangle", "Circle");
		
		// Set the cellFactory property
		shapes.setCellFactory(new ShapeCellFactory());

		// Set the buttonCell property
		shapes.setButtonCell(new StringShapeCell());
		
		HBox root = new HBox(shapeLbl, shapes);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);		
		stage.setTitle("Using CellFactory in ComboBox");
		stage.show();
	}
}
