// SayHelloController.java
package com.jdojo.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SayHelloController {
	// The refernce of msgLbl will be injected by the FXML loader
	@FXML
	private Label msgLbl;

	// location and resources will be automatically injected by the FXML laoder
	@FXML 
	private URL location;

	@FXML 
	private ResourceBundle resources;
	
	// Add a public no-args construtcor explicitly just to 
	// emphasize that it is needed for a controller
	public SayHelloController() {
	}

	@FXML
	private void initialize() {
		System.out.println("Initializing SayHelloController...");
		System.out.println("Location = " + location);
		System.out.println("Resources = " + resources);
	}
	
	@FXML
	private void sayHello() {
		msgLbl.setText("Hello from FXML!");
	}
}
