// LabelTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LabelTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TextField fNameFld = new TextField();
		Label fNameLbl = new Label("_First Name:");
		fNameLbl.setLabelFor(fNameFld);
		fNameLbl.setMnemonicParsing(true);

		TextField lNameFld = new TextField();
		Label lNameLbl = new Label("_Last Name:");
		lNameLbl.setLabelFor(lNameFld);
		lNameLbl.setMnemonicParsing(true);

		GridPane root = new GridPane();
		root.addRow(0, fNameLbl, fNameFld);
		root.addRow(1, lNameLbl, lNameFld);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Labels");
		stage.show();
	}
}
