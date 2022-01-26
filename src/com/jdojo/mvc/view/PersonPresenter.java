// PersonPresenter.java
package com.jdojo.mvc.view;

import com.jdojo.mvc.model.Person;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PersonPresenter {
	private final Person model;
	private final PersonView view;
	
	public PersonPresenter(Person model, PersonView view) {
		this.model = model;
		this.view = view;
		attachEvents();
	}

	private void attachEvents() {
		// We need to detect the birth date change when the bDate field loses
		// focus or the user presses the Enter key while it still has focus
		view.bDateFld.setOnAction(e -> handleBirthDateChange());
		view.bDateFld.getScene().focusOwnerProperty()
		                        .addListener(this::focusChanged);	

		// Save the data
		view.saveBtn.setOnAction(e -> saveData()); 

		// Close the window when the Close button is pressed
		view.closeBtn.setOnAction(e -> view.getScene().getWindow().hide());
	}

	public void focusChanged(ObservableValue<? extends Node> value,
	                         Node oldNode, 
	                         Node newNode) {
		
		// The birth date field has lost focus
		if (oldNode == view.bDateFld) {
			handleBirthDateChange();
		}
	}
	
	private void handleBirthDateChange() {
		String bdateStr = view.bDateFld.getText();
		if (bdateStr == null || bdateStr.trim().equals("")) {
			model.setBirthDate(null);
			view.syncBirthDate();
		} else {		
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(view.dateFormat);
				LocalDate bdate = LocalDate.parse(bdateStr, formatter);

				List<String> errorList = new ArrayList<>();
				if (model.isValidBirthDate(bdate, errorList)) {
					model.setBirthDate(bdate);
					view.syncAgeCategory();
				} else {
					this.showError(errorList);
					view.syncBirthDate();
				}
			}
			catch (DateTimeParseException e) {
				// Birth date is not in the specified date format
				List<String> errorList = new ArrayList<>();
				errorList.add("Birth date must be in the " + 
				              view.dateFormat.toLowerCase() + " format.");
				this.showError(errorList);
				
				// Refresh the view
				view.syncBirthDate();
			}
		}
	}
	
	private void saveData() {
		List<String> errorList = new ArrayList<>();
		boolean isSaved = model.save(errorList);
		if (!isSaved) {
			this.showError(errorList);
		}		
	}
	
	public void showError(List<String> errorList) {
		String msg = "";
		if (errorList.isEmpty()) {
			msg = "No message to display.";
		} else {
			for (String s : errorList) {
				msg = msg + s + "\n";
			}
		}

		Label msgLbl = new Label(msg);
		Button okBtn = new Button("OK");
		VBox root = new VBox(new StackPane(msgLbl), new StackPane(okBtn));
		root.setSpacing(10);

		Scene scene = new Scene(root);
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setScene(scene);		
		stage.initOwner(view.getScene().getWindow());

		// Set the Action listener for the OK button
	 	okBtn.setOnAction(e -> stage.close());
 
		stage.setTitle("Error");
		stage.sizeToScene();
		stage.showAndWait();
	}
}
