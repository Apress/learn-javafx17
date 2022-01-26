// ImageInputTest.java
package com.jdojo.effect;

import java.net.URL;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ImageInputTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// TODO: book text
		URL url = ResourceUtil.getResourceURL("picture/randomness.jpg"); //getClass().getClassLoader().getResource(path);

		Node node = null;
		ImageInput imageInputEffect = new ImageInput();
		double requestedWidth = 100;
		double requestedHeight = 50;
		boolean preserveRation = false;
		boolean smooth = true;
		Image image = new Image(url.toExternalForm(), 
		                        requestedWidth, 
		                        requestedHeight, 
		                        preserveRation, 
		                        smooth);
		imageInputEffect.setSource(image);

		node = new Rectangle(100, 50);
		GaussianBlur dsEffect = new GaussianBlur();
		dsEffect.setInput(imageInputEffect);
		node.setEffect(dsEffect);
		

		HBox root = new HBox(node);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the ImageInput Effect");
		stage.show();
	}
}
