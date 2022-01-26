// TableViewEditing.java
package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;

public class TableViewEditing extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TableView<Person> table = new TableView<>(PersonTableUtil.getPersonList());
		
		// Make the TableView editable
		table.setEditable(true);
		
		// Add columns with appropriate editing features
		addIdColumn(table);
		addFirstNameColumn(table);
		addLastNameColumn(table);
		addBirthDateColumn(table);
		addBabyColumn(table);
		addGenderColumn(table);
		
		HBox root = new HBox(table);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Editing Data in a TableView");
		stage.show();
	}
	
	public void addIdColumn(TableView<Person> table) {
		// Id column is non-editable
		table.getColumns().add(PersonTableUtil.getIdColumn());
	}
	
	public void addFirstNameColumn(TableView<Person> table) {
		// First Name is a String, editable column
		TableColumn<Person, String> fNameCol = 
				PersonTableUtil.getFirstNameColumn();
		
		// Use a TextFieldTableCell, so it can be edited
		fNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
		
		table.getColumns().add(fNameCol);
	}
	
	public void addLastNameColumn(TableView<Person> table) {
		// Last Name is a String, editable column
		TableColumn<Person, String> lNameCol = PersonTableUtil.getLastNameColumn();
		
		// Use a TextFieldTableCell, so it can be edited
		lNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
		
		table.getColumns().add(lNameCol);
	}
	
	public void addBirthDateColumn(TableView<Person> table) {
		// Birth Date is a LocalDate, editable column
		TableColumn<Person, LocalDate> birthDateCol = PersonTableUtil.getBirthDateColumn();

		// Use a TextFieldTableCell, so it can be edited
		LocalDateStringConverter converter = new LocalDateStringConverter();
		birthDateCol.setCellFactory(
			TextFieldTableCell.<Person, LocalDate>forTableColumn(converter));
		
		table.getColumns().add(birthDateCol);
	}
	
	public void addBabyColumn(TableView<Person> table) {
		// Baby? is a Boolean, non-editable column
		TableColumn<Person, Boolean> babyCol = new TableColumn<>("Baby?");
		babyCol.setEditable(false);
		
		// Set a cell value factory
		babyCol.setCellValueFactory(cellData -> {
	        Person p = cellData.getValue();
			Boolean v =  (p.getAgeCategory() == Person.AgeCategory.BABY);
			return new ReadOnlyBooleanWrapper(v);
		});

		// Use a CheckBoxTableCell to display the boolean value
		babyCol.setCellFactory(
			CheckBoxTableCell.<Person>forTableColumn(babyCol));

		table.getColumns().add(babyCol);
	}
	
	public void addGenderColumn(TableView<Person> table) {
		// Gender is a String, editable, ComboBox column
		TableColumn<Person, String> genderCol = new TableColumn<>("Gender");
		genderCol.setMinWidth(80);

		// By default, all cells are have null values
		genderCol.setCellValueFactory(
				cellData -> new ReadOnlyStringWrapper(null));
		
		// Set a ComboBoxTableCell, so we can selects a value from a list
		genderCol.setCellFactory(
			ComboBoxTableCell.<Person, String>forTableColumn("Male", "Female"));
		
		// Add an event handler to handle the edit commit event.
		// It displays the selected value on the standard output
		genderCol.setOnEditCommit(e -> {
			int row = e.getTablePosition().getRow();		    
			Person person = e.getRowValue();
			System.out.println("Gender changed for " + 
			                    person.getFirstName() + " " + person.getLastName() + 
			                   " at row " + (row + 1) + " to " + e.getNewValue());
		});

		table.getColumns().add(genderCol);
	}
}
