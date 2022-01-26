// MainDocController.java
package com.jdojo.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainDocController {
	@FXML 
	private Button includedCloseBtn;
		
	@FXML
	private CloseBtnController includedCloseBtnController;
	
	@FXML
	public void initialize() {
		System.out.println("MainDocController.initialize()");	    
		// You can use the nested controller here
	}
}
