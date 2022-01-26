// HelloJavaFX.java
package com.jdojo.fxml;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {
	private final Label msgLbl = new Label("FXML is cool!");
	private final Button sayHelloBtn = new Button("Say Hello");
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Set the preferred width of the lable
		msgLbl.setPrefWidth(150);
		
		// Set the ActionEvent handler for the button
		sayHelloBtn.setOnAction(this::sayHello);

		VBox root = new VBox(10);
		root.getChildren().addAll(msgLbl, sayHelloBtn);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Hello FXML");
		stage.show();		
	}
	
	public void sayHello(ActionEvent e) {
		msgLbl.setText("Hello from FXML!");
	}
}
