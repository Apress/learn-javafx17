// ChoiceBoxTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChoiceBoxTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {		
		Label seasonLbl = new Label("Select a Season:");
		ChoiceBox<String> seasons = new ChoiceBox<>();
		seasons.getItems().addAll("Spring", "Summer", "Fall", "Winter");
		
		// Select the first season from the list
		seasons.getSelectionModel().selectFirst();
		
		// Add ChangeListeners to track change in selected index and item. Only 
		// one listener is necessary if you want to track change in selection
		seasons.getSelectionModel().selectedItemProperty()
		                           .addListener(this::itemChanged);
		seasons.getSelectionModel().selectedIndexProperty()
		                           .addListener(this::indexChanged);
		
		Label selectionMsgLbl = new Label("Your selection:");
		Label selectedValueLbl = new Label("None");

		// Bind the value property to the text property of the Label
		selectedValueLbl.textProperty().bind(seasons.valueProperty());

		// Display controls in a GridPane
		GridPane root = new GridPane();
		root.setVgap(10);
		root.setHgap(10);
		root.addRow(0, seasonLbl, seasons);
		root.addRow(1, selectionMsgLbl, selectedValueLbl);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using ChoiceBox Controls");
		stage.show();
	}

	// A change listener to track the change in selected item
	public void itemChanged(ObservableValue<? extends String> observable, 
	                        String oldValue, 
	                        String newValue) { 		
		System.out.println("Itemchanged: old = " + oldValue + 
		                   ", new = " + newValue);
	}
	
	// A change listener to track the change in selected index
	public void indexChanged(ObservableValue<? extends Number> observable, 
	                         Number oldValue, 
	                         Number newValue) {         
		System.out.println("Indexchanged: old = " + oldValue + ", new = " + newValue);
	}
}
