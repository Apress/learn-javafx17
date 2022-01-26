// ImageDragAndDrop.java
package com.jdojo.dnd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImageDragAndDrop extends Application {
	ImageView imageView = new ImageView();
	Button clearBtn = new Button("Clear Image");
	Scene scene; 
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Build UI
		VBox root = getUIs();
		scene = new Scene(root);
		stage.setScene(scene);

		// Add event handlers for the source and target
		this.addDnDEventHanders();

		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" +
		              "-fx-border-color: blue;");
		stage.setTitle("Performing a Drag-and-Drop Gesture");
		stage.show();
	}

	private VBox getUIs() {
		Label msgLbl = new Label(
			"Drag and drop an image, an image file, or an image URL below.");
		
		// Set the size for the image view
		imageView.setFitWidth(300);
		imageView.setFitHeight(300);
		imageView.setSmooth(true);
		imageView.setPreserveRatio(true);

		clearBtn.setOnAction(e -> imageView.setImage(null));

		VBox box = new VBox(20, msgLbl, imageView, clearBtn);
		return box;
	}

	private void addDnDEventHanders() {
		scene.setOnDragOver(this::dragOver);
		scene.setOnDragDropped(this::dragDropped);
	}

	private void dragOver(DragEvent e) {
		// You can drag an image, a URL or a file
		Dragboard dragboard = e.getDragboard();

		if (dragboard.hasImage() || dragboard.hasFiles() || dragboard.hasUrl()) {
			e.acceptTransferModes(TransferMode.ANY);
		}
		
		e.consume();
	}

	private void dragDropped(DragEvent e) {
		boolean isCompleted = false;
		
		// Transfer the data to the target
		Dragboard dragboard = e.getDragboard();
		
		if (dragboard.hasImage()) {
			this.transferImage(dragboard.getImage());
			isCompleted = true;
		} else if (dragboard.hasFiles()) {
			isCompleted = this.transferImageFile(dragboard.getFiles());
		} else if (dragboard.hasUrl()) {
		    isCompleted = this.transferImageUrl(dragboard.getUrl());
		} else {
			System.out.println("Dragboard does not contain an image" + 
			                   " in the expected format: Image, File, URL");
		}

		// Data transfer is not successful
		e.setDropCompleted(isCompleted);
		
		e.consume();
	}
	
	private void transferImage(Image image) {
		imageView.setImage(image);
	}
	
	private boolean transferImageFile(List<File> files) {
		// Look at the mime typeof all file.
		// Use the first file having the mime type as "image/xxx"
		for(File file : files) {
			String mimeType;
			try {
				mimeType = Files.probeContentType(file.toPath());
				if (mimeType != null && mimeType.startsWith("image/")) {
					this.transferImageUrl(file.toURI().toURL().toExternalForm());
					return true;
				}
			} 
			catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return false;
	}
	
	private boolean transferImageUrl(String imageUrl) {
		try {
			imageView.setImage(new Image(imageUrl));
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
}
