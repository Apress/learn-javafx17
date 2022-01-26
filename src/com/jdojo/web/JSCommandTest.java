// JSCommandTest.java
package com.jdojo.web;

import java.net.URL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JSCommandTest extends Application {
	private final String DEFAULT_HOME_PAGE = "resources\\html\\jshandlers.html"; 
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		String homePageUrl = getDefaultHomePageUrl(); 
		BrowserPane root = new BrowserPane(homePageUrl, stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);		
		stage.show();
	}
	
	public String getDefaultHomePageUrl() {
		String pageUrl = "http://www.google.com";           
		URL url = this.getClass().getClassLoader()
		              .getResource(DEFAULT_HOME_PAGE);
		if (url == null) {
			System.out.println(
				"Could not find " + DEFAULT_HOME_PAGE + " in CLASSPATH. " +
				"Using " + pageUrl + " as the default home page." );
		}
		else {
			pageUrl = url.toExternalForm();
		}       
		return pageUrl;
	}
}
