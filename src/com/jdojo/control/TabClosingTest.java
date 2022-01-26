// TabClosingTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import static javafx.scene.control.TabPane.TabClosingPolicy;

public class TabClosingTest extends Application {
	GeneralTab generalTab = new GeneralTab("General", null);
	AddressTab addressTab = new AddressTab("Address", null);
	TabPane tabPane = new TabPane();
	
	CheckBox allowClosingTabsFlag = new CheckBox("Are Tabs closable?");
	Button restoreTabsBtn = new Button("Restore Tabs");
	ChoiceBox<TabPane.TabClosingPolicy> tabClosingPolicyChoices = new ChoiceBox<>();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Add Tabs to the TabPane
		tabPane.getTabs().addAll(generalTab, addressTab);

		// Set a tab close request event handler for tabs
		generalTab.setOnCloseRequest(this::tabClosingRequested);
		addressTab.setOnCloseRequest(this::tabClosingRequested);

		// Set a closed event handler for the tabs
		generalTab.setOnClosed(e -> tabClosed(e));
		addressTab.setOnClosed(e -> tabClosed(e));

		// Set an action event handler for the restore button
		restoreTabsBtn.setOnAction(e -> restoreTabs());
		
		// Add choices to the choice box
		tabClosingPolicyChoices.getItems()
		                       .addAll(TabClosingPolicy.ALL_TABS, 
		                               TabClosingPolicy.SELECTED_TAB,
		                               TabClosingPolicy.UNAVAILABLE);

		// Set the default value for the tab closing policy
		tabClosingPolicyChoices.setValue(tabPane.getTabClosingPolicy());


		// Bind the tabClosingPolicy of the tabPane to the value property of the 
		// of the ChoiceBoxx
		tabPane.tabClosingPolicyProperty().bind(
						tabClosingPolicyChoices.valueProperty());

		BorderPane root = new BorderPane();
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setStyle("-fx-padding: 10;");
		grid.addRow(0, allowClosingTabsFlag, restoreTabsBtn);
		grid.addRow(1, new Label("Tab Closing Policy:"), 			
		               tabClosingPolicyChoices);
		root.setTop(grid);
		root.setCenter(tabPane);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Closing Tabs");
		stage.show();
	}

	public void tabClosingRequested(Event e) {
		if (!allowClosingTabsFlag.isSelected()) {
			e.consume(); // Closing tabs is not allowed
		}
	}
	
	public void tabClosed(Event e) {
		Tab tab = (Tab)e.getSource();
		String text = tab.getText();
		System.out.println(text + " tab has been closed.");
	}
	
	public void restoreTabs() {
		ObservableList<Tab> list = tabPane.getTabs();
		if (!list.contains(generalTab)) {
			list.add(0, generalTab);
		}
		
		if (!list.contains(addressTab)) {
			list.add(1, addressTab);
		}
	}
	
	public void closingPolicyChanged(
				ObservableValue<? extends TabPane.TabClosingPolicy> prop, 
				TabPane.TabClosingPolicy oldPolicy,
				TabPane.TabClosingPolicy newPolicy) {
		tabPane.setTabClosingPolicy(newPolicy);
	}
}
