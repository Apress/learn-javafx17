// SimplestTableView.java
package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings("unchecked")
public class SimplestTableView extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a TableView with a list of persons
		TableView<Person> table = new TableView<>(PersonTableUtil.getPersonList());
		
		// Add columns to the TableView
		table.getColumns().addAll(PersonTableUtil.getIdColumn(), 
		                          PersonTableUtil.getFirstNameColumn(),
		                          PersonTableUtil.getLastNameColumn(), 
		                          PersonTableUtil.getBirthDateColumn());

		VBox root = new VBox(table);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Simplest TableView");
		stage.show();
	}
}
