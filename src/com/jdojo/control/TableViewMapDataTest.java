// TableViewMapDataTest.java
package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TableViewMapDataTest  extends Application {
	private final String idColumnKey = "id";
	private final String firstNameColumnKey = "firstName";
	private final String lastNameColumnKey = "lastName";
	private final String birthDateColumnKey = "birthDate";
		
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TableView<Map> table = new TableView<>();
		ObservableList<Map<String, Object>> items = this.getMapData();
		table.getItems().addAll(items);
		this.addColumns(table);

		HBox root = new HBox(table);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using a Map as items in a TableView");
		stage.show();	    
	}
	
	public ObservableList<Map<String, Object>> getMapData() {
		ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();

		// Extract the person data, add the data to a Map, and add the Map to 
		// the items list
		ObservableList<Person> persons = PersonTableUtil.getPersonList();
		for(Person p : persons) {
			Map<String, Object> map = new HashMap<>();
			map.put(idColumnKey, p.getPersonId());
			map.put(firstNameColumnKey, p.getFirstName());
			map.put(lastNameColumnKey, p.getLastName());
			map.put(birthDateColumnKey, p.getBirthDate());
			items.add(map);
		}
		
		return items;
	}
	
	@SuppressWarnings("unchecked")
	public void addColumns(TableView table) { 
		TableColumn<Map, Integer> idCol = new TableColumn<>("Id");
		idCol.setCellValueFactory(new MapValueFactory<>(idColumnKey));

		TableColumn<Map, String> firstNameCol = new TableColumn<>("First Name");
		firstNameCol.setCellValueFactory(new MapValueFactory<>(firstNameColumnKey));
		
		TableColumn<Map, String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(new MapValueFactory<>(lastNameColumnKey));

		TableColumn<Map, LocalDate> birthDateCol = 	new TableColumn<>("Birth Date");
		birthDateCol.setCellValueFactory(new MapValueFactory<>(birthDateColumnKey));

		table.getColumns().addAll(idCol, firstNameCol, lastNameCol, birthDateCol);
	}
}
