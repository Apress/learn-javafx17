// TreeViewEditingData.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TreeViewEditingData extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TreeView<String> treeView = TreeViewUtil.getTreeView();

		// Make the TreeView editable
		treeView.setEditable(true);
	
		// Set a cell factory to use TextFieldTreeCell
		treeView.setCellFactory(TextFieldTreeCell.forTreeView());
	
		// Set editing related event handlers
		treeView.setOnEditStart(this::editStart);
		treeView.setOnEditCommit(this::editCommit);
		treeView.setOnEditCancel(this::editCancel);
	
		HBox root = new HBox(treeView);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A Editing Cells in a TreeView");
		stage.show();
	}

	public void editStart(TreeView.EditEvent<String> e) {
		System.out.println("Started editng: " + e.getTreeItem() );
	}

	public void editCommit(TreeView.EditEvent<String> e) {
		System.out.println(e.getTreeItem() +  " changed." + 
		                   " old = " + e.getOldValue() + 
		                   ", new = " + e.getNewValue());
	}

	public void editCancel(TreeView.EditEvent<String> e) {
		System.out.println("Cancelled editng: " + e.getTreeItem() );
	}
}
