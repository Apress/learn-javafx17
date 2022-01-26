// TreeTableViewAddDeleteRows.java
package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import static javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TreeTableViewAddDeleteRows  extends Application {
	private final TreeTableView<Person> treeTable = new TreeTableView<>();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		// Create the model
		TreeItem<Person> rootNode = TreeTableUtil.getModel();
		rootNode.setExpanded(true);
		treeTable.setRoot(rootNode);
		treeTable.setPrefWidth(400);
		treeTable.setEditable(true);
		treeTable.getSelectionModel().selectFirst();

		// Set appropariate cell factories for columns
		TreeTableColumn<Person, String> fNameCol = TreeTableUtil.getFirstNameColumn();
		fNameCol.setCellFactory(TextFieldTreeTableCell.<Person>forTreeTableColumn());

		TreeTableColumn<Person, String> lNameCol = 
				TreeTableUtil.getLastNameColumn();
		lNameCol.setCellFactory(TextFieldTreeTableCell.<Person>forTreeTableColumn());

		TreeTableColumn<Person, LocalDate> birthDateCol = 
				TreeTableUtil.getBirthDateColumn();
		LocalDateStringConverter converter = new LocalDateStringConverter();
		birthDateCol.setCellFactory(
		TextFieldTreeTableCell.<Person, LocalDate>forTreeTableColumn(converter));
	
		// Add Columns
		treeTable.getColumns().addAll(fNameCol, lNameCol, birthDateCol);

		// Add a placeholder to the TreeTableView.
		// It is displayed when the root node is deleted.
		treeTable.setPlaceholder(new Label("Click the Add button to add a row."));
	
		Label msgLbl = new Label("Please select a row to add/delete.");
		HBox buttons = this.getButtons();
		VBox root = new VBox(msgLbl, buttons, treeTable);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" +
		              "-fx-border-style: solid inside;" +
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" +
		              "-fx-border-radius: 5;" +
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Adding/Deleting Rows in a TreeTableView");
		stage.show();
	}

	private HBox getButtons() {
		Button addBtn = new Button("Add");
		addBtn.setOnAction(e -> addRow());
		
		Button deleteBtn = new Button("Delete");
		deleteBtn.setOnAction(e -> deleteRow());

		return new HBox(20, addBtn, deleteBtn);
	}

	private void addRow() {
		if (treeTable.getExpandedItemCount() == 0 ) {	
			// There is no row in teh TreeTableView
			addNewRootItem();
		} else if (treeTable.getSelectionModel().isEmpty()) {
			System.out.println("Select a row to add.");
			return;
		} else {
			addNewChildItem();
		}
	}

	private void addNewRootItem() {
		// Add a root Item
		TreeItem<Person> item = new TreeItem<>(new Person("New", "New", null));
		treeTable.setRoot(item);
	
		// Edit the item
		this.editItem(item);
	}

	private void addNewChildItem() {
		// Prepare a new TreeItem with a new Person object
		TreeItem<Person> item = new TreeItem<>(new Person("New", "New", null));

		// Get the selection model
		TreeTableViewSelectionModel<Person> sm = treeTable.getSelectionModel();

		// Get the selected row index
		int rowIndex = sm.getSelectedIndex();

		// Get the selected TreeItem
		TreeItem<Person> selectedItem = sm.getModelItem(rowIndex);

		// Add the new item as children to the selected item
		selectedItem.getChildren().add(item);

		// Make sure the new item is visible
		selectedItem.setExpanded(true);

		// Edit the item
		this.editItem(item);
	}

	private void editItem(TreeItem<Person> item) {
		// Scroll to the new item
		int newRowIndex = treeTable.getRow(item);
		treeTable.scrollTo(newRowIndex);

		// Put the first column in editing mode
		TreeTableColumn<Person, ?> firstCol = treeTable.getColumns().get(0);
		treeTable.getSelectionModel().select(item);
		treeTable.getFocusModel().focus(newRowIndex, firstCol);
		treeTable.edit(newRowIndex, firstCol);
	}

	private void deleteRow() {
		// Get the selection model
		TreeTableViewSelectionModel<Person> sm = treeTable.getSelectionModel();
		if (sm.isEmpty()) {
			System.out.println("Select a row to delete.");
			return;
		}

		int rowIndex = sm.getSelectedIndex();
		TreeItem<Person> selectedItem = sm.getModelItem(rowIndex);
	
		TreeItem<Person> parent = selectedItem.getParent();	
		if (parent != null) {
			parent.getChildren().remove(selectedItem);
		} else {
			// Must be deleting the root item
			treeTable.setRoot(null);
		}
	}
}
