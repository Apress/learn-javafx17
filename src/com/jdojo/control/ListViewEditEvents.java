// ListViewEditEvents.java
package com.jdojo.control;

import javafx.application.Application; 
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ListViewEditEvents extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		ListView<String> breakfasts = new ListView<>();
		breakfasts.setPrefSize(200, 120);
		breakfasts.getItems().addAll("Apple", "Banana", "Donut", "Hash Brown"); 
		breakfasts.setEditable(true);
		breakfasts.setCellFactory(TextFieldListCell.forListView());
	
		// Add Edit-related event handlers
		breakfasts.setOnEditStart(this::editStart);
		breakfasts.setOnEditCommit(this::editCommit);
		breakfasts.setOnEditCancel(this::editCancel);

		HBox root = new HBox(new Label("Breakfast:"), breakfasts);
		root.setSpacing(20);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);	    
		stage.setScene(scene);		
		stage.setTitle("Using ListView Edit Events");
		stage.show();	
	}	
	
	public void editStart(ListView.EditEvent<String> e) {
		System.out.println("Edit Start: Index=" + e.getIndex() + 
		                   ", Item=" + e.getNewValue());
	}
	
	public void editCommit(ListView.EditEvent<String> e) {		
		System.out.println("Edit Commit: Index=" + e.getIndex() + 
		                   ", Item=" + e.getNewValue());
	}   
	
	public void editCancel(ListView.EditEvent<String> e) {
		System.out.println("Edit Cancel: Index=" + e.getIndex() + 
		                   ", Item=" + e.getNewValue());
	}
}
