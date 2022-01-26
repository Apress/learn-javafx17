// CheckBoxTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CheckBoxTest extends Application {
	Label userSelectionMsg = new Label("Do you agree? No");
	CheckBox agreeCbx;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a CheckBox to support only two states
		CheckBox hungryCbx = new CheckBox("Hungry");
	 	
		// Create a CheckBox to support three states
		agreeCbx = new CheckBox("I agree");
		agreeCbx.setAllowIndeterminate(true);
		
		// Track the state change for the "I agree" CheckBox
		// Text for the Label userSelectionMsg will be updated
		agreeCbx.selectedProperty().addListener(this::changed);
		agreeCbx.indeterminateProperty().addListener(this::changed);

		VBox root = new VBox(userSelectionMsg, hungryCbx, agreeCbx);
		root.setSpacing(20);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root, 200, 130);
		stage.setScene(scene);
		stage.setTitle("Using CheckBoxes");
		stage.show();	   
	}
	
	// A change listener to track the state change in agreeCbx
	public void changed(ObservableValue<? extends Boolean> observable, 
	                    Boolean oldValue, 
	                    Boolean newValue) {
		String msg;
		if (agreeCbx.isIndeterminate()) {
		    msg = "Not sure";
		} else if (agreeCbx.isSelected()) {
		    msg = "Yes";
		} else {
			msg = "No";
		}
		this.userSelectionMsg.setText("Do you agree? " + msg);
	}
}
