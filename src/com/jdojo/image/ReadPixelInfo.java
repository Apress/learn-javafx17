// ReadPixelInfo.java
package com.jdojo.image;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ReadPixelInfo extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		String imagePath = ResourceUtil.getResourceURLStr("picture/ksharan.jpg");
		Image image = new Image(imagePath);
		ImageView imageView = new ImageView(image);
		HBox root = new HBox(imageView);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Reading Pixels from an Image");
		stage.show();
		
		// Read pixels from the image
		this.readPixelsInfo(image);
	}
	
	private void readPixelsInfo(Image image) {
		// Obtain the pixel reader from the image
		PixelReader pixelReader = image.getPixelReader();		
		if (pixelReader == null) {
			System.out.println("Connot read pixels from the image");
			return;
		}
		
		// Get image width and height
		int width = (int)image.getWidth();
		int height = (int)image.getHeight();

		// Read all pixels
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				Color color = pixelReader.getColor(x, y);
				System.out.println("Color at (" + x + ", " + y + ") = " + color);
			}
		}

		PixelFormat format = pixelReader.getPixelFormat();
		PixelFormat.Type formatType = format.getType();
		System.out.println("Pixel format type: " + formatType);
	}
}
