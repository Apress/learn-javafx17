// LoginControl.java
package com.jdojo.fxml;

import java.io.IOException;
import java.net.URL;

import com.jdojo.util.ResourceUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {
	@FXML
	private TextField userId;
	
	@FXML
	private PasswordField pwd;
	
	public LoginControl() {	
		// Load the FXML
		URL fxmlUrl = ResourceUtil.getResourceURL("fxml/login.fxml");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(fxmlUrl);
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} 
		catch (IOException exception) {
		   throw new RuntimeException(exception);
		}
	}
	
	@FXML
	private void initialize() {
		// Do some work
	}
	
	@FXML
	private void okClicked() {
		System.out.println("Ok cliked");
	}
	
	@FXML
	private void cancelClicked() {
	    System.out.println("Cancel cliked");
	}
	
	public String getUserId() {
		return userId.getText();
	}
			
	public String getPassword() {
		return pwd.getText();
	}
}
