// ListViewChoiceBoxEditing.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.ChoiceBoxListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListViewChoiceBoxEditing extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		ListView<String> breakfasts = new ListView<>();
		breakfasts.setPrefSize(200, 120);
		breakfasts.setEditable(true);
		breakfasts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		// Let the user select a maximum of four breakfast items
		breakfasts.getItems().addAll("[Double click to select]", 
		                             "[Double click to select]", 
		                             "[Double click to select]", 
		                             "[Double click to select]");

		// The breakfast items to select from
		ObservableList<String> items = FXCollections.<String>observableArrayList(
								"Apple", "Banana", "Donut", "Hash Brown");
		
		// Set a ChoiceBox cell factory for editing
		breakfasts.setCellFactory(ChoiceBoxListCell.forListView(items));
		
		VBox root = new VBox(new Label("Double click an item to select."), 
		                     new Label("Breakfasts:"),
		                     breakfasts);
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
}
