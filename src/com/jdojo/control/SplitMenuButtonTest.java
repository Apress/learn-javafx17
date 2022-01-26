// SplitMenuButtonTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class SplitMenuButtonTest extends Application {
	private WebView webview;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
	    // Must create a WebView object from the JavaFX Application Thread
		webview = new WebView(); 

		MenuItem jdojo = new MenuItem("JDojo");	    
		jdojo.setOnAction(e -> loadPage("http://www.jdojo.com"));

		MenuItem yahoo = new MenuItem("Yahoo");
		yahoo.setOnAction(e -> loadPage("http://www.yahoo.com"));

		MenuItem google = new MenuItem("Google");
		google.setOnAction(e -> loadPage("http://www.google.com"));

		// Create a SplitMenuButton
		SplitMenuButton splitBtn = new SplitMenuButton();
		splitBtn.setText("Home");

		// Add menu items to the SplitMenuButton
		splitBtn.getItems().addAll(jdojo, yahoo, google); 

		// Add ActionEvent handler when "Home" is clicked
		splitBtn.setOnAction(e -> loadPage("http://www.jdojo.com"));

		BorderPane root = new BorderPane();
		root.setTop(splitBtn);
		BorderPane.setAlignment(splitBtn, Pos.TOP_RIGHT);
		root.setCenter(webview); 

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using SplitMenuButton Controls");
		stage.show();
	}
	
	public void loadPage(String url) {
		webview.getEngine().load(url);
	}
}
