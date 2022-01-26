// MenuButtonTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MenuButtonTest extends Application {
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
	
		// Add menu items to the MenuButton
		MenuButton links = new MenuButton("Visit");
		links.getItems().addAll(jdojo, yahoo, google); 

		BorderPane root = new BorderPane();
		root.setTop(links);
		BorderPane.setAlignment(links, Pos.TOP_RIGHT);
		root.setCenter(webview);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using MenuButton Controls");
		stage.show();
	}
	
	public void loadPage(String url) {
		webview.getEngine().load(url);
	}
}
