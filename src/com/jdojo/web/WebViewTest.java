// WebViewTest.java
package com.jdojo.web;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;

public class WebViewTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		WebView webView = new WebView();
		WebEngine webEngine = webView.getEngine();
		
		// Update the stage title when a new web page title is available
		webEngine.titleProperty().addListener(
			(ObservableValue<? extends String> p, 
			 String oldTitle, String newTitle) -> {
				stage.setTitle(newTitle);
		});

		// Load the Google web page
		// TODO: book text
		webEngine.load("https://www.qwant.com");

		VBox root = new VBox(webView);
		Scene scene = new Scene(root);
		stage.setScene(scene);		
		stage.show();
	}
}
