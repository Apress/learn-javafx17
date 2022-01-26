// CustomDataTransfer.java
package com.jdojo.dnd;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CustomDataTransfer extends Application {
	ListView<Item> lv1 = new ListView<>();
	ListView<Item> lv2 = new ListView<>();
	
	// Our custom Data Format
	static final DataFormat ITEM_LIST = new DataFormat("jdojo/itemlist");
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Build the UI
		GridPane root = getUIs();

		// Add event handlers for the source and target
		// text fields of the the DnD operation
		this.addDnDEventHanders();

		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Drag-and-Drop Test");
		stage.show();
	}
	
	private GridPane getUIs() {
		Label msgLbl = new Label("Select one or more items from a list, " + 
		                         "drag and drop them to another list");
		
		lv1.setPrefSize(200, 200);
		lv2.setPrefSize(200, 200);
		lv1.getItems().addAll(this.getList());
		
		// Allow multi-select in lists
		lv1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lv2.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.add(msgLbl, 0, 0, 3, 1);
		pane.addRow(1, new Label("List 1:" ), new Label("List 2:" ));
		pane.addRow(2, lv1, lv2);		
		return pane;
	}

	private ObservableList<Item> getList() {
		ObservableList<Item> list = FXCollections.<Item>observableArrayList();
		list.addAll(new Item("Apple"), new Item("Orange"), 
		            new Item("Papaya"), new Item("Mango"),
		            new Item("Grape"), new Item("Guava"));
		return list;
	}
	
	private void addDnDEventHanders() {
		lv1.setOnDragDetected(e -> dragDetected(e, lv1));
		lv2.setOnDragDetected(e -> dragDetected(e, lv2));

		lv1.setOnDragOver(e -> dragOver(e, lv1));
		lv2.setOnDragOver(e -> dragOver(e, lv2));

		lv1.setOnDragDropped(e -> dragDropped(e, lv1));
		lv2.setOnDragDropped(e -> dragDropped(e, lv2));

		lv1.setOnDragDone(e -> dragDone(e, lv1));
		lv2.setOnDragDone(e -> dragDone(e, lv2));
	}

	private void dragDetected(MouseEvent e, ListView<Item> listView) {
		// Make sure at least one item is selected
		int selectedCount = listView.getSelectionModel().getSelectedIndices().size();
		if (selectedCount == 0) {
			e.consume();
			return;
		}

		// Initiate a drag-and-drop gesture
		Dragboard dragboard = listView.startDragAndDrop(TransferMode.COPY_OR_MOVE);

		// Put the the selected items to the dragboard
		ArrayList<Item> selectedItems = this.getSelectedItems(listView);
		ClipboardContent content = new ClipboardContent();		
		content.put(ITEM_LIST, selectedItems);
		dragboard.setContent(content);

		e.consume();
	}
	
	private void dragOver(DragEvent e, ListView<Item> listView) {
		// If drag board has an ITEM_LIST and it is not being dragged
		// over itself, we accept the MOVE transfer mode
		Dragboard dragboard = e.getDragboard();
		
		if (e.getGestureSource() != listView &&
			dragboard.hasContent(ITEM_LIST)) {		
			e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		}
				
		e.consume();
	}
	
	@SuppressWarnings("unchecked")
	private void dragDropped(DragEvent e, ListView<Item> listView) {
		boolean dragCompleted = false;
		
		// Transfer the data to the target
		Dragboard dragboard = e.getDragboard();
		
		if(dragboard.hasContent(ITEM_LIST)) {
			ArrayList<Item> list = (ArrayList<Item>)dragboard.getContent(ITEM_LIST);
			listView.getItems().addAll(list);
			
			// Data transfer is successful
			dragCompleted = true;
		}

		// Data transfer is not successful
		e.setDropCompleted(dragCompleted);
		
		e.consume();
	}

	private void dragDone(DragEvent e, ListView<Item> listView) {        
		// Check how data was transfered to the target 
		// If it was moved, clear the selected items
		TransferMode tm = e.getTransferMode();
		
		if (tm == TransferMode.MOVE) {
			removeSelectedItems(listView);
		}
		
		e.consume();
	}
	
	private ArrayList<Item> getSelectedItems(ListView<Item> listView) {
		// Return the list of selected item in an ArratyList, so it is
		// serializable and can be stored in a Dragboard.
		ArrayList<Item> list = 
			new ArrayList<>(listView.getSelectionModel().getSelectedItems());
		return list;
	}

	private void removeSelectedItems(ListView<Item> listView) {
		// Get all selected items in a separate list to avoid the shared list issue
		List<Item> selectedList = new ArrayList<>();
		for(Item item : listView.getSelectionModel().getSelectedItems()) {
			selectedList.add(item);
		}

		// Clear the selection 
		listView.getSelectionModel().clearSelection();
		
		// Remove items from the selected list
		listView.getItems().removeAll(selectedList);
	}
}
