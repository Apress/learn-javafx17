// TextCustomFont.java
package com.jdojo.shape;

import java.net.MalformedURLException;
import java.net.URL;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TextCustomFont extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Text t1 = new Text();
		t1.setLineSpacing(10);

		Text t2 = new Text("Another Text node");

		// Load the custom font
		// TODO: book text
		var url = ResourceUtil.getResourceURL("font/4starfac.ttf");
		if (url != null) {
			String urlStr = url.toExternalForm();
			Font customFont = Font.loadFont(urlStr, 16);
			if (customFont != null ) {
				// Set the custom font  for the first Text node
				t1.setFont(customFont);

				// Set the text and line spacing
				t1.setText("Hello from the custom font!!! \nFont Family: " + 
				           customFont.getFamily());
				
				// Create an object of the custom font and use it
				Font font2 = Font.font(customFont.getFamily(), FontWeight.BOLD, 
				                       FontPosture.ITALIC, 24);

				// Set the custom font for the second Text node
				t2.setFont(font2);
			} else {
				t1.setText("Could not load the custom font from " + urlStr);
			}
		} else {
			t1.setText("Could not find the custom font file " + 
					"\"font/4starfac.ttf\"" + ". Used the default font.");
		}

		HBox root = new HBox(t1, t2);
		root.setSpacing(20);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Loading and Using Custom Font");
		stage.show();
	}
}
