// KnowingHostDetailsApp.java
package com.jdojo.scene;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import netscape.javascript.JSObject;

public class KnowingHostDetailsApp extends Application {
	public static void main(String[] args) {            
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		String yahooURL = "http://www.yahoo.com";
		Button openURLButton = new Button("Go to Yahoo!");
		openURLButton.setOnAction(e -> getHostServices().showDocument(yahooURL));
	
		Button showAlert = new Button("Show Alert");
		showAlert.setOnAction(e -> showAlert());
	
		VBox root = new VBox();
		
		// Add buttons and all host related details to the VBox
		root.getChildren().addAll(openURLButton, showAlert);
		
		Map<String, String> hostdetails = getHostDetails();
		for(Map.Entry<String, String> entry : hostdetails.entrySet()) {
			String desc = entry.getKey() + ": " + entry.getValue();
			root.getChildren().add(new Label(desc));
		}
		
		Scene scene = new Scene(root); 
		stage.setScene(scene);
		stage.setTitle("Knowing the Host");
		stage.show();
	}
	
	protected Map<String, String> getHostDetails() {
		Map<String, String> map = new HashMap<>();
		HostServices host = this.getHostServices();
		
		String codeBase = host.getCodeBase();
		map.put("CodeBase", codeBase);
		
		String documentBase = host.getDocumentBase();
		map.put("DocumentBase", documentBase);
				
		String splashImageURI = host.resolveURI(documentBase, "splash.jpg");
		map.put("Splash Image URI", splashImageURI);
		
		return map;
	}
	
	protected void showAlert() {
		Stage s = new Stage(StageStyle.UTILITY);
		s.initModality(Modality.WINDOW_MODAL);
		
		Label msgLabel = new Label("This is an FX alert!");
		Group root = new Group(msgLabel);
		Scene scene = new Scene(root);
		s.setScene(scene);
		
		s.setTitle("FX Alert");
		s.show();
	}
}
