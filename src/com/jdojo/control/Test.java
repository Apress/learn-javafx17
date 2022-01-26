// TableViewDataTest.java
package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import static java.time.temporal.ChronoUnit.YEARS;

public class Test extends Application {		
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		// Create a TableView with data		
		TableView<Person> table = 
				new TableView<>(PersonTableUtil.getPersonList());

		table.setTableMenuButtonVisible(true);
		
		// Create an "Age" computed column
		TableColumn<Person, String> ageCol = new TableColumn<>("Age");
		ageCol.setCellValueFactory(cellData -> {
				Person p = cellData.getValue();
				LocalDate dob = p.getBirthDate();
				String ageInYear = "Unknown";

				if (dob != null) {
					long years = YEARS.between(dob, LocalDate.now());
					if (years == 0) {
						ageInYear = "< 1 year";
					} else if (years == 1) {
						ageInYear = years + " year";
					} else {
						ageInYear = years + " years";
					}
				}
			return new ReadOnlyStringWrapper(ageInYear);
		});
	
		// Create an "Age Cotegory" column
		TableColumn<Person, Person.AgeCategory> ageCategoryCol = 
				new TableColumn<>("Age Category");
		ageCategoryCol.setCellValueFactory(
				new PropertyValueFactory<>("ageCategory"));

		// Add columns to the TableView		
		table.getColumns().addAll(PersonTableUtil.getIdColumn(), 
		                          PersonTableUtil.getFirstNameColumn(),
		                          PersonTableUtil.getLastNameColumn(), 
		                          PersonTableUtil.getBirthDateColumn(),
		                          ageCol,
		                          ageCategoryCol);
		
		HBox root = new HBox(table);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Populating TableViews");
		stage.show();	    
	}
}
