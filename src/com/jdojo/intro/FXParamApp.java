// FXParamApp.java
package com.jdojo.intro;

import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class FXParamApp extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Get application parameters
		Parameters p = this.getParameters();
		Map<String, String> namedParams = p.getNamed();
		List<String> unnamedParams = p.getUnnamed();
		List<String> rawParams = p.getRaw();
		
		String paramStr = "Named Parameters: " + namedParams + "\n" +
			"Unnamed Parameters: " + unnamedParams + "\n" +
			"Raw Parameters: " + rawParams;

		TextArea ta = new TextArea(paramStr);
		Group root = new Group(ta);
		stage.setScene(new Scene(root));
		stage.setTitle("Application Parameters");
		stage.show();
	}
}
