// ListViewCheckBoxEditing.java
package com.jdojo.control;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListViewCheckBoxEditing extends Application {
	Map<String, ObservableValue<Boolean>> map = new HashMap<>();
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Populate the map with ListView items as its keys and 
		// their selected state as the value 
		map.put("Apple", new SimpleBooleanProperty(false));
		map.put("Banana", new SimpleBooleanProperty(false));
		map.put("Donut", new SimpleBooleanProperty(false));
		map.put("Hash Brown", new SimpleBooleanProperty(false));
		
		ListView<String> breakfasts = new ListView<>();
		breakfasts.setPrefSize(200, 120);
		breakfasts.setEditable(true);
		breakfasts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		// Add all keys from the map as items to the ListView		
		breakfasts.getItems().addAll(map.keySet());
		
		// Create a Callback object
		Callback<String, ObservableValue<Boolean>> itemToBoolean = (String item) -> map.get(item);
		
		// Set the cell factory
		breakfasts.setCellFactory(CheckBoxListCell.forListView(itemToBoolean));
		
		Button printBtn = new Button("Print Selection");
		printBtn.setOnAction(e -> printSelection());
		
		VBox root = new VBox(new Label("Breakfasts:"), breakfasts, printBtn);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);	    
		stage.setScene(scene);		
		stage.setTitle("Using ListView Cell Factory");
		stage.show();
	}
	
	public void printSelection() {
		System.out.println("Selected items: ");
		for(String key: map.keySet()) {
			ObservableValue<Boolean> value = map.get(key);
			if (value.getValue()) {
				System.out.println(key);		
			}
		}

		System.out.println();
	}
}
