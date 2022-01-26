// JavaScriptToJavaFX.java
package com.jdojo.web;

import java.net.URL;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import netscape.javascript.JSObject; // Add plugin.jar to CLASSPATH

public class JavaScriptToJavaFX extends Application {
	// An inner class
	public class FXAdder {
		public double add(double n1, double n2) {
			return n1 + n2;
		}
	}
	
	private String HOME_PAGE = "resources\\html\\javascript_to_javafx.html"; 

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		String homePageUrl = getHomePageUrl();
		BrowserPane browser = new BrowserPane(homePageUrl, stage);

		VBox root = new VBox(browser);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		// Let JavaScript know about the FXAdder object
		WebEngine webEngine = browser.getWebView().getEngine();	

		// Set the member for the window object after the document loads
	 	webEngine.getLoadWorker().stateProperty().addListener(
			(prop, oldState, newState) -> {
				if (newState == Worker.State.SUCCEEDED) {
					JSObject jsWindow = 
						(JSObject)webEngine.executeScript("window");
					jsWindow.setMember("fxAdder", new FXAdder());
				}
		});
	}

	public String getHomePageUrl() {
		String pageUrl = null;
		URL url = this.getClass().getClassLoader().getResource(HOME_PAGE);
		if (url == null) {
			System.out.println("Could not find " + HOME_PAGE + " in CLASSPATH." +
				"Use the OPen button in teh navigation bar to open it." );
		}
		else {
			pageUrl = url.toExternalForm();
		}
		return pageUrl;
	}
}
