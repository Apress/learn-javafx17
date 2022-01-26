// FileSystemBrowser.java
package com.jdojo.control;

import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FileSystemBrowser extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a root node using the current directory. 
		PathTreeItem rootNode = new PathTreeItem(Paths.get("."));
		TreeView<Path> treeView = new TreeView<>(rootNode);
		treeView.setShowRoot(false);

		// Set a cell factory to display only file name
		treeView.setCellFactory((TreeView<Path> tv) -> {
			TreeCell<Path> cell = new TreeCell<Path>() {
				@Override
				public void updateItem(Path item, boolean empty) {
					super.updateItem(item, empty);
					if (item != null && !empty) {
						Path fileName = item.getFileName();
						if (fileName == null) {
							this.setText(item.toString());
						} else {
							this.setText(fileName.toString());
						}
						this.setGraphic(this.getTreeItem().getGraphic());
					} else {
						this.setText(null);
						this.setGraphic(null);
					}
				}
			};
			return cell;
		});

		HBox root = new HBox(treeView);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" + 
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("File System Browser");
		stage.show();
	}
}
