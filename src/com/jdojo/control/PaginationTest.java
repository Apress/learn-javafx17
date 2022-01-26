// PaginationTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaginationTest extends Application {
	private static final int PAGE_COUNT = 5;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Pagination pagination = new Pagination(PAGE_COUNT);
		
		// Set the page factory
		pagination.setPageFactory(this::getPage);

		VBox root = new VBox(pagination);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Pagination Controls");
		stage.show();
	}
	
	public Label getPage(int pageIndex) {
		Label content = null;
		
		if (pageIndex >= 0 && pageIndex < PAGE_COUNT) {
			content = new Label("Content for page " + (pageIndex + 1));		
		}
		return content;
	}
}
