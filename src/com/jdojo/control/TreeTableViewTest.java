// TreeTableViewTest.java
package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TreeTableViewTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		TreeItem<Person> rootNode = TreeTableUtil.getModel();
		rootNode.setExpanded(true);

		// Create a TreeTableView with model
		TreeTableView<Person> treeTable = new TreeTableView<>(rootNode);
		treeTable.setPrefWidth(400);
		
		// Add columns
		treeTable.getColumns().addAll(TreeTableUtil.getFirstNameColumn(),
		                              TreeTableUtil.getLastNameColumn(), 
		                              TreeTableUtil.getBirthDateColumn(),
		                              TreeTableUtil.getAgeCategoryColumn());

		HBox root = new HBox(treeTable);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using a TreeTableView");
		stage.show();	
	}
}
