// BasicWebBrowser.java
package com.jdojo.web;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class BasicWebBrowser extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		WebView webView = new WebView();

		// Update the stage title when a new web page title is available
		webView.getEngine().titleProperty().addListener(
			(ObservableValue<? extends String> p, String oldTitle, 
			 String newTitle) -> stage.setTitle(newTitle));

		// Load the Google web page
		// TODO: book text
		String homePageUrl = "https://www.qwant.com";

		MenuButton options = new WebOptionsMenu(webView);
		NavigationBar navBar = new NavigationBar(webView, homePageUrl, true);
		navBar.getChildren().add(options);
	
		VBox root = new VBox(navBar, webView);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
