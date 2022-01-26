// MultipleImageViews.java
package com.jdojo.image;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MultipleImageViews extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Load an image in its original size
		String imagePath = ResourceUtil.getResourceURLStr("picture/school_bus.jpg");
		Image image = new Image(imagePath);
		
		// Create three views of different sizes of the same image
		ImageView view1 = getImageView(image, 100, 50, false);
		ImageView view2 = getImageView(image, 100, 50, true);
		ImageView view3 = getImageView(image, 100, 100, true);

		HBox root = new HBox(10, view1, view2, view3);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Multiple Views of an Image");
		stage.show();
	}
	
	private ImageView getImageView(Image image, 
	                               double fitWidth, 
	                               double fitHeight, 
	                               boolean preserveRation) {
		ImageView view = new ImageView(image);
		view.setFitWidth(fitWidth);
		view.setFitHeight(fitHeight);
		view.setPreserveRatio(preserveRation);
		view.setSmooth(true);
		return view;
	}
}
