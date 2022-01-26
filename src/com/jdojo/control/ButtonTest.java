// ButtonTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ButtonTest extends Application {
	Label msgLbl = new Label("Press Enter or Esc key to see the message");
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// A normal button with N as its mnemonic 
		Button newBtn = new Button("_New");
		newBtn.setOnAction(e -> newDocument());
		
		// A default button with S as its mnemonic
		Button saveBtn = new Button("_Save");
		saveBtn.setDefaultButton(true);		
		saveBtn.setOnAction( e -> save());
		
		// A cancel button with C as its mnemonic
		Button cancelBtn = new Button("_Cancel");
		cancelBtn.setCancelButton(true);		
		cancelBtn.setOnAction(e -> cancel());
		
		HBox buttonBox = new HBox(newBtn, saveBtn, cancelBtn);
		buttonBox.setSpacing(15);
		VBox root = new VBox(msgLbl, buttonBox); 
		root.setSpacing(15);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Command Buttons");
		stage.show();
	}
	
	public void newDocument() {
		msgLbl.setText("Creating a new document...");
	}
	
	public void save() {
		msgLbl.setText("Saving...");
	}
	
	public void cancel() {
		msgLbl.setText("Cancelling...");
	}
}
