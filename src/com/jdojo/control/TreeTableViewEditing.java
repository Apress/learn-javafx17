// TreeTableViewEditing.java
package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TreeTableViewEditing extends Application {		
	public static void main(String[] args) {		
		Application.launch(args);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		// Create the model
		TreeItem<Person> rootNode = TreeTableUtil.getModel();
		rootNode.setExpanded(true);
		
		// Create a TreeTableView with a model
		TreeTableView<Person> treeTable = new TreeTableView<Person>(rootNode);	
		treeTable.setPrefWidth(400);
		
		// Must make the TreeTableView editable
		treeTable.setEditable(true);

		// Set appropariate cell factories for 
		TreeTableColumn<Person, String> fNameCol = TreeTableUtil.getFirstNameColumn();
		fNameCol.setCellFactory(TextFieldTreeTableCell.<Person>forTreeTableColumn());

		TreeTableColumn<Person, String> lNameCol =	TreeTableUtil.getLastNameColumn();
		lNameCol.setCellFactory(TextFieldTreeTableCell.<Person>forTreeTableColumn());

		TreeTableColumn<Person, LocalDate> birthDateCol = TreeTableUtil.getBirthDateColumn();
		LocalDateStringConverter converter = new LocalDateStringConverter();
		birthDateCol.setCellFactory(
		TextFieldTreeTableCell.<Person, LocalDate>forTreeTableColumn(converter));

		// Add Columns
		treeTable.getColumns().addAll(fNameCol, lNameCol, birthDateCol);
		
		HBox root = new HBox(treeTable);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Editing Data in a TreeTableView");
		stage.show();	
	}
}
