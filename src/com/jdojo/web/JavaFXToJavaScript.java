// JavaFXToJavaScript.java
package com.jdojo.web;

import java.net.URL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;

public class JavaFXToJavaScript  extends Application {
	private final String HOME_PAGE = "resources\\html\\javafx_to_javascript.html"; 
	private Integer jsTimerId = null;
	private WebEngine webEngine;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		String homePageUrl = getHomePageUrl();
		BrowserPane browser = new BrowserPane(homePageUrl, stage);

		// Save the web engine reference to call JavaScript code later
		webEngine = browser.getWebView().getEngine();

		Button startTimeBtn = new Button("Start Showing Time");
		startTimeBtn.setOnAction(e -> startJSTimer());

		Button stopTimeBtn = new Button("Stop Showing Time");
		stopTimeBtn.setOnAction(e -> stopJSTimer());

		HBox buttons = new HBox(10, startTimeBtn, stopTimeBtn);
		VBox root = new VBox(10, buttons, browser);
		Scene scene = new Scene(root);
		stage.setScene(scene);		
		stage.show();
	}

	public void startJSTimer() {
		try {
			jsTimerId = (Integer)webEngine.executeScript("startShowingTime()");			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void stopJSTimer() {
		if (jsTimerId != null) {
			String script = "stopShowingTime(" + jsTimerId + ")";
			webEngine.executeScript(script);
			jsTimerId = null;
		}
	}

	public String getHomePageUrl() {
		String pageUrl = null;
		URL url = this.getClass().getClassLoader().getResource(HOME_PAGE);
		if (url == null) {
			System.out.println("Could not find " + HOME_PAGE + " in CLASSPATH." +
			      " Use the Open button in teh navigation bar to open it." );
		}
		else {
			pageUrl = url.toExternalForm();
		}
		return pageUrl;
	}
}
