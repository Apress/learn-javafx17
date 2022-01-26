// ComboBoxTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ComboBoxTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label seasonsLbl = new Label("Season:");
		ComboBox<String> seasons = new ComboBox<>();
		seasons.getItems().addAll("Spring", "Summer", "Fall", "Winter");

		Label breakfastsLbl = new Label("Breakfast:");
		ComboBox<String> breakfasts = new ComboBox<>();
		breakfasts.getItems().addAll("Apple", "Banana", "Strawberry");
		breakfasts.setEditable(true);

		// Show the user's selection in a Label
		Label selectionLbl = new Label();
		StringProperty str = new SimpleStringProperty("Your selection: ");
		selectionLbl.textProperty().bind(str.concat("Season=")
		                                    .concat(seasons.valueProperty())
		                                    .concat(", Breakfast=")
		                                    .concat(breakfasts.valueProperty()));
		
		HBox row1 = new HBox(seasonsLbl, seasons, breakfastsLbl, breakfasts);
		row1.setSpacing(10);
		VBox root = new VBox(row1, selectionLbl);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using ComboBox Controls");
		stage.show();
	}
}
