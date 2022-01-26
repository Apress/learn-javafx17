// DragAndDropTest.java
package com.jdojo.dnd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DragAndDropTest extends Application {
	TextField sourceFld = new TextField("JavaFX");
	TextField targetFld = new TextField("Drag and drop the source text here");

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Build UI
		GridPane root = getUIs();

		// Add event handlers for the source and target
		this.addDnDEventHanders();

		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Performing a Drag-and-Drop Gesture");
		stage.show();
	}

	private GridPane getUIs() {
		// Set prompt text
		sourceFld.setPromptText("Enter text to drag");
		targetFld.setPromptText("Drag the source text here");

		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setVgap(20);
		pane.add(new Label("Drag and drop the source text field" + 
		                   " onto the target text field."), 0, 0, 2, 1);
		pane.addRow(1, new Label("DnD Gesture Source:"), sourceFld);
		pane.addRow(2, new Label("DnD Gesture Target:"), targetFld);
		return pane;
	}

	private void addDnDEventHanders() {
		sourceFld.setOnDragDetected(this::dragDetected);
		targetFld.setOnDragOver(this::dragOver);
		targetFld.setOnDragDropped(this::dragDropped);
		sourceFld.setOnDragDone(this::dragDone);		
	}

	private void dragDetected(MouseEvent e) {
		// User can drag only when there is text in the source field
		String sourceText = sourceFld.getText();
		if (sourceText == null || sourceText.trim().equals("")) {
			e.consume();
			return;
		}

		// Initiate a drag-and-drop gesture
		Dragboard dragboard = 
			sourceFld.startDragAndDrop(TransferMode.COPY_OR_MOVE);

		// Add the source text to the Dragboard
		ClipboardContent content = new ClipboardContent();
		content.putString(sourceText);
		dragboard.setContent(content);

		e.consume();
	}

	private void dragOver(DragEvent e) {
		// If drag board has a string, let the event know that
		// the target accepts copy and move transfer modes
		Dragboard dragboard = e.getDragboard();
		if (dragboard.hasString()) {
			e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		}
		
		e.consume();
	}

	private void dragDropped(DragEvent e) {
		// Transfer the data to the target
		Dragboard dragboard = e.getDragboard();
		if (dragboard.hasString()) {
			String text = dragboard.getString();
			targetFld.setText(text);

			// Data transfer is successful
			e.setDropCompleted(true);
		} else {
			// Data transfer is not successful
			e.setDropCompleted(false);
		}

		e.consume();
	}

	private void dragDone(DragEvent e) {
		// Check how data was transfered to the target. If it was moved, clear the text 
		// in the source.
		TransferMode modeUsed = e.getTransferMode();

		if (modeUsed == TransferMode.MOVE) {
			sourceFld.setText("");
		}
		
		e.consume();
	}
}
