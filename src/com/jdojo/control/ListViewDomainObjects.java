// ListViewDomainObjects.java
package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListViewDomainObjects extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		ListView<Person> persons = new ListView<>();
		persons.setPrefSize(150, 120);
		persons.getItems().addAll(new Person("John", "Jacobs", null),
		                          new Person("Donna", "Duncan", null), 
		                          new Person("Layne", "Estes", null), 
		                          new Person("Mason", "Boyd", null));

		// Add a custom cell factory to display formatted names of persons
		persons.setCellFactory( 
			new Callback<ListView<Person>,ListCell<Person>>() {
			@Override
			public ListCell<Person> call(ListView<Person> listView) {
				return new ListCell<Person>() {
					@Override
					public void updateItem(Person item, boolean empty) {
						// Must call super
						super.updateItem(item, empty);

						int index = this.getIndex();
						String name = null;

						// Format name 
						if (item == null || empty) {
							// No action to perform
						} else {
							name = (index + 1) + ". " + 
							       item.getLastName() + ", " + 
							       item.getFirstName();
						}
						
						this.setText(name);
						setGraphic(null);
					}
				}; 
		}});

		HBox root = new HBox(new Label("Persons:"), persons);
		root.setSpacing(20);
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
