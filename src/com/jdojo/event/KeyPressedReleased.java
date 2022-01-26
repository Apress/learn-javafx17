// KeyPressedReleased.java
package com.jdojo.event;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class KeyPressedReleased extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label nameLbl = new Label("Name:");
		TextField nameTfl = new TextField();

		HBox root = new HBox();
		root.setPadding(new Insets(20));
		root.setSpacing(20);
		root.getChildren().addAll(nameLbl, nameTfl);

		// Add key pressed and released events to the TextField
		nameTfl.setOnKeyPressed(e -> handle(e));
		nameTfl.setOnKeyReleased(e -> handle(e));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Key Pressed and Released Events");
		stage.show();
	}

	public void handle(KeyEvent e) {
		String type = e.getEventType().getName();
		KeyCode keyCode = e.getCode();
		System.out.println(type + ": Key Code=" + keyCode.getName() + 
		                   ", Text=" + e.getText());

		// Show the help window when the F1 key is pressed
		if (e.getEventType() == KEY_PRESSED && e.getCode() == KeyCode.F1) {
			displayHelp();
			e.consume();
		}
	}

	public void displayHelp() {
		Text helpText = new Text("Please enter a name.");
		HBox root = new HBox();
		root.setStyle("-fx-background-color: yellow;");
		root.getChildren().add(helpText);

		Scene scene = new Scene(root, 200, 100);
		Stage helpStage = new Stage();
		helpStage.setScene(scene);
		helpStage.setTitle("Help");
		helpStage.show();
	}
}
