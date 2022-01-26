// PersonView.java
package com.jdojo.mvc.view;

import com.jdojo.mvc.model.Person;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PersonView extends GridPane {
	private final Person model;

	// Labels
	Label personIdLbl = new Label("Person Id:");
	Label fNameLbl = new Label("First Name:");
	Label lNameLbl = new Label("Last Name:");
	Label bDateLbl = new Label("Birth Date:");
	Label ageCategoryLbl = new Label("Age Category:");

	// Fields
	TextField personIdFld = new TextField();
	TextField fNameFld = new TextField();
	TextField lNameFld = new TextField();
	TextField bDateFld = new TextField();
	TextField ageCategoryFld = new TextField();
	
	// Buttons
	Button saveBtn = new Button("Save");
	Button closeBtn = new Button("Close");

	// Date format
	String dateFormat;

	public PersonView(Person model, String dateFormat) {
		this.model = model;
		this.dateFormat = dateFormat;
		layoutForm();
		initFieldData();
		bindFieldsToModel();
	}

	private void initFieldData() {
		// Id and names are populated using bindings. 
		// Populate birth date and age category
		syncBirthDate();
	}

	private void layoutForm() {
		this.setHgap(5);
		this.setVgap(5);

		this.add(personIdLbl, 1, 1);
		this.add(fNameLbl, 1, 2);
		this.add(lNameLbl, 1, 3);
		this.add(bDateLbl, 1, 4);
		this.add(ageCategoryLbl, 1, 5);

		this.add(personIdFld, 2, 1);
		this.add(fNameFld, 2, 2);
		this.add(lNameFld, 2, 3);
		this.add(bDateFld, 2, 4);
		this.add(ageCategoryFld, 2, 5);

		// Add buttons and make them the same width
		VBox buttonBox = new VBox(saveBtn, closeBtn);
		saveBtn.setMaxWidth(Double.MAX_VALUE);
		closeBtn.setMaxWidth(Double.MAX_VALUE);

		this.add(buttonBox, 3, 1, 1, 5);

		// Disable the personId field
		personIdFld.setDisable(true);
		ageCategoryFld.setDisable(true);

		// Set the prompt text for the birth date field
		bDateFld.setPromptText(dateFormat.toLowerCase());
	}

	public void bindFieldsToModel() {
		personIdFld.textProperty().bind(model.personIdProperty().asString());
		fNameFld.textProperty().bindBidirectional(model.firstNameProperty());
		lNameFld.textProperty().bindBidirectional(model.lastNameProperty());
	}

	public void syncBirthDate() {
		LocalDate bdate = model.getBirthDate();
		if (bdate != null) {
			bDateFld.setText(bdate.format(DateTimeFormatter.ofPattern(dateFormat)));
		}

		syncAgeCategory();
	}

	public void syncAgeCategory() {
		ageCategoryFld.setText(model.getAgeCategory().toString());
	}
}
