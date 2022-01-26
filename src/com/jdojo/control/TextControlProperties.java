// TextControlProperties.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TextControlProperties extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TextField nameFld = new TextField();
		Label anchorLbl = new Label("");
		Label caretLbl = new Label("");
		Label lengthLbl = new Label("");
		Label selectedTextLbl = new Label("");
		Label selectionLbl = new Label("");
		Label textLbl = new Label("");
		
		// Bind text property of the Labels to the properties of the TextField
		anchorLbl.textProperty().bind(nameFld.anchorProperty().asString());
		caretLbl.textProperty().bind(nameFld.caretPositionProperty().asString());
		lengthLbl.textProperty().bind(nameFld.lengthProperty().asString());
		selectedTextLbl.textProperty().bind(nameFld.selectedTextProperty());
		selectionLbl.textProperty().bind(nameFld.selectionProperty().asString());
		textLbl.textProperty().bind(nameFld.textProperty());

		GridPane root = new GridPane();
		root.setHgap(10);
		root.setVgap(5);
		root.addRow(0, new Label("Name:"), nameFld);
		root.addRow(1, new Label("Anchor Position:"), anchorLbl);
		root.addRow(2, new Label("Caret Postion:"), caretLbl);
		root.addRow(3, new Label("Length:"), lengthLbl);
		root.addRow(4, new Label("Selected Text:"), selectedTextLbl);
		root.addRow(5, new Label("Selection:"), selectionLbl);
		root.addRow(6, new Label("Text:"), textLbl);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Text Input Control Properties");
		stage.show();
	}
}
