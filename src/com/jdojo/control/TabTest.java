// TabTest.java
package com.jdojo.control;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TabTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		ImageView privacyIcon = getImage("privacy_icon.png");
		GeneralTab generalTab = new GeneralTab("General", privacyIcon);
		
		ImageView addressIcon = getImage("address_icon.png");
		AddressTab addressTab = new AddressTab("Address", addressIcon);
		
		TabPane tabPane = new TabPane();
		tabPane.getTabs().addAll(generalTab, addressTab);

		BorderPane root = new BorderPane(); 
		root.setCenter(tabPane);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using TabPane and Tab Controls");
		stage.show();
	}

	public ImageView getImage(String fileName) {
		ImageView imgView = null;
		try {
			// TODO: book text
			String imagePath = ResourceUtil.getResourceURLStr("picture/" + fileName);			
			Image img = new Image(imagePath);
			imgView = new ImageView(img);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return imgView;
	}
}
