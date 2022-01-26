// TreeViewCellFactory.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TreeViewCellFactory extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TreeView<String> treeView = TreeViewUtil.getTreeView();

		// Set a cell factory to prepend the row index to the TreeItem value
		treeView.setCellFactory( (TreeView<String> tv) -> {
			TreeCell<String> cell = new TreeCell<String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						this.setText(null);
						this.setGraphic(null);
					}
					else {
						String value = this.getTreeItem().getValue();
						this.setText(this.getIndex() + ". " + value);
					}
				}};
			return cell;
		});
				
		HBox root = new HBox(treeView);
		root.setSpacing(20);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using a Cell Factory in a TreeView");
		stage.show();	
	}
}
