// AddressTab.java
package com.jdojo.control;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddressTab extends Tab {
	TextField streetFld = new TextField();
	TextField cityFld = new TextField();
	TextField stateFld = new TextField();
	TextField zipFld = new TextField();

	public AddressTab(String text, Node graphic) {
		this.setText(text);
		this.setGraphic(graphic);
		init();
	}

	public void init() {
		GridPane grid = new GridPane();
		grid.addRow(0, new Label("Street:"), streetFld);
		grid.addRow(1, new Label("City:"), cityFld);
		grid.addRow(2, new Label("State:"), stateFld);
		grid.addRow(3, new Label("ZIP:"), zipFld);
		this.setContent(grid);
	}
}
