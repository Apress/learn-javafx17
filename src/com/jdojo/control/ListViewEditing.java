// ListViewEditing.java
package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class ListViewEditing extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		ListView<String> breakfasts = getBreakfastListView();
		ListView<Person> persons = getPersonListView();
		
		GridPane root = new GridPane();
		root.setHgap(20);
		root.setVgap(10);
		root.add(new Label("Double click an item to edit."), 0, 0, 2, 1);
		root.addRow(1, new Label("Persons:"), new Label("Breakfasts:"));
		root.addRow(2, persons, breakfasts);
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
	
	public ListView<Person> getPersonListView() {
		ListView<Person> persons = new ListView<>();
		persons.setPrefSize(200, 120);
		persons.setEditable(true);
		persons.getItems().addAll(new Person("John", "Jacobs", null),
		                          new Person("Donna", "Duncan", null), 
		                          new Person("Layne", "Estes", null), 
		                          new Person("Mason", "Boyd", null));
		
		// Set a TextField cell factory to edit the Person items. Also use a
		// StringConverter to convert a String to a Person and vice-versa
		StringConverter<Person> converter = new PersonStringConverter();
		Callback<ListView<Person>, ListCell<Person>> cellFactory = 
					TextFieldListCell.forListView(converter);
		persons.setCellFactory(cellFactory);

		return persons;
	}
	
	public ListView<String> getBreakfastListView() {
		ListView<String> breakfasts = new ListView<>();
		breakfasts.setPrefSize(200, 120);
		breakfasts.setEditable(true);
		breakfasts.getItems().addAll("Apple", "Banana", "Donut", "Hash Brown");	

		// Set a TextField cell factory to edit the String items
		Callback<ListView<String>, ListCell<String>> cellFactory = 
										TextFieldListCell.forListView();
		breakfasts.setCellFactory(cellFactory);
		
		return breakfasts;
	}
}
