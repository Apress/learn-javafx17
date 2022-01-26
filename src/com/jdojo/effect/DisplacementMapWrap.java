// DisplacementMapWrap.java
package com.jdojo.effect;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.FloatMap;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DisplacementMapWrap extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a FloatMap
		int width = 200;
		int height = 25;
		
		FloatMap map = new FloatMap(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// Move all pixels 100 pixels to the left
				double u = 100.0/width; 
				map.setSamples(i, j, (float)u, 0.0f);
			}
		}

		Text t1 = new Text("Displaced Text");
		t1.setFont(Font.font(24));
		DisplacementMap effect1 = new DisplacementMap();
		effect1.setMapData(map);
		t1.setEffect(effect1);

		Text t2 = new Text("Displaced Text");
		t2.setFont(Font.font(24));
		DisplacementMap effect2 = new DisplacementMap();
		effect2.setWrap(true);
		effect2.setMapData(map);
		t2.setEffect(effect2);

		VBox root = new VBox(t1, t2);
		root.setSpacing(5);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" +
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using the warps proeprty in DisplacementMap");
		stage.show();
	}
}
