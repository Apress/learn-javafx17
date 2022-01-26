// SaveImage.java
package com.jdojo.image;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SaveImage  extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		String imagePath = ResourceUtil.getResourceURLStr("picture/ksharan.jpg");
		Image image = new Image(imagePath);
		ImageView imageView = new ImageView(image);
		
		Button saveBtn = new Button("Save Image");
		saveBtn.setOnAction(e -> ImageUtil.saveToFile(image));

		VBox root = new VBox(10, imageView, saveBtn);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Saving an Image to a File");
		stage.show();
	}
}
