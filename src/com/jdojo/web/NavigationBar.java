// NavigationBar.java
package com.jdojo.web;

import java.io.File;
import java.net.MalformedURLException;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class NavigationBar extends HBox {
	private FileChooser fileChooser = new FileChooser();

	public NavigationBar(WebView webView, String homePageUrl, boolean goToHomePage) {
		this.setSpacing(4);
		this.setStyle("-fx-background-color: lightblue;-fx-padding: 5;");
	
		WebEngine webEngine = webView.getEngine();
	
		TextField pageUrl = new TextField();
		Button refreshBtn = new Button("Refresh");
		Button goBtn = new Button("Go");
		Button homeBtn = new Button("Home");
		Button openBtn = new Button("Open");

		// Let the TextField grow horizontallly
		HBox.setHgrow(pageUrl, Priority.ALWAYS);

		// Add an ActionListener to navigate to teh entered URL
		pageUrl.setOnAction(e -> webEngine.load(pageUrl.getText()));
	
		// Update the URL in the TextField when user navigates to another page.
		// for example, by clicking a link on the page
		webEngine.locationProperty().addListener(
			(ObservableValue<? extends String> prop, 
			 String oldValue, String newValue) -> pageUrl.setText(newValue));
	
		// Add an ActionListener for the Refresh button
		refreshBtn.setOnAction(e -> webEngine.reload());

		// Add an ActionListener for the Go Button
		goBtn.setOnAction(e -> webEngine.load(pageUrl.getText()));

		// Add an ActionListener for the Home Button
		homeBtn.setOnAction(e -> webEngine.load(homePageUrl));

		// Configure the FileChooser
		fileChooser.setTitle("Open Web Content");
		fileChooser.getExtensionFilters()
		           .addAll(new ExtensionFilter("HTML Files", "*.html", "*.htm"));
		
		// Add an ActionListener for the Open Button
		openBtn.setOnAction(e -> {
		    File selectedFile = fileChooser.showOpenDialog(webView.getScene().getWindow());
		    if (selectedFile != null) {
				try {
					webEngine.load(selectedFile.toURI().toURL().toExternalForm());
				}
				catch(MalformedURLException e2) {
					e2.printStackTrace();
				}
			}
		});

		this.getChildren().addAll(new Label("URL:"), pageUrl, 
		                          goBtn, refreshBtn, homeBtn, openBtn);
		if (goToHomePage) {
			webEngine.load(homePageUrl);
		}
	}
}
