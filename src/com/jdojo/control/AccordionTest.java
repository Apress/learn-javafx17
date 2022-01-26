// AccordionTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AccordionTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TitledPane generalPane = this.getGeneralPane();
		TitledPane addressPane = this.getAddressPane();
		TitledPane phonePane = this.getPhonePane();

		Accordion root = new Accordion();
		root.getPanes().addAll(generalPane, addressPane, phonePane);
		root.setExpandedPane(generalPane);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Accordion Controls");
		stage.show();		
	}

	public TitledPane getGeneralPane() {
		GridPane grid = new GridPane();
		grid.addRow(0, new Label("First Name:"), new TextField());
		grid.addRow(1, new Label("Last Name:"), new TextField());
		grid.addRow(2, new Label("DOB:"), new DatePicker());

		TitledPane generalPane = new TitledPane("General", grid);
		return generalPane;
	}

	public TitledPane getAddressPane() {
		GridPane grid = new GridPane();
		grid.addRow(0, new Label("Street:"), new TextField());
		grid.addRow(1, new Label("City:"), new TextField());
		grid.addRow(2, new Label("State:"), new TextField());
		grid.addRow(3, new Label("ZIP:"), new TextField());

		TitledPane addressPane = new TitledPane("Address", grid);
		return addressPane;
	}
	
	public TitledPane getPhonePane() {
		GridPane grid = new GridPane();
		grid.addRow(0, new Label("Home:"), new TextField());
		grid.addRow(1, new Label("Work:"), new TextField());
		grid.addRow(2, new Label("Cell:"), new TextField());

		TitledPane phonePane = new TitledPane("Phone", grid);
		return phonePane;
	}
}
