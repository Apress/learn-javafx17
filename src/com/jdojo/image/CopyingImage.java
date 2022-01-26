// CopyingImage.java
package com.jdojo.image;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CopyingImage extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		String imagePath = ResourceUtil.getResourceURLStr("picture/ksharan.jpg");
		Image image = new Image(imagePath, 200, 100, true, true);

		int width = (int)image.getWidth();
		int height = (int)image.getHeight(); 

		// Create three WritableImage instances
		// one will be a darker, one brighter, and one semi-transparent
		WritableImage darkerImage = new WritableImage(width, height);
		WritableImage brighterImage = new WritableImage(width, height);
		WritableImage semiTransparentImage = new WritableImage(width, height);

		// Copy source pixels to the destinations 
		this.createImages(image, 
		                 darkerImage, 
		                 brighterImage, 
		                 semiTransparentImage, 
		                 width, 
		                 height);
		
		ImageView imageView = new ImageView(image);
		ImageView darkerView = new ImageView(darkerImage);
		ImageView brighterView = new ImageView(brighterImage);
		ImageView semiTransparentView = new ImageView(semiTransparentImage);

		HBox root = new HBox(10, 
		       new VBox(imageView, new Text("Original")), 
		       new VBox(darkerView, new Text("Darker")), 
		       new VBox(brighterView, new Text("Brighter")), 
		       new VBox(semiTransparentView, new Text("Semi-Transparent")));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Writing Pixels to an Image");
		stage.show();
	}
	
	private void createImages(Image image, 
	                          WritableImage darkerImage, 
	                          WritableImage brighterImage, 
	                          WritableImage semiTransparentImage,
	                          int width, int height) {
	// Obtain the pixel reader from the image
	PixelReader pixelReader = image.getPixelReader();
	PixelWriter darkerWriter = darkerImage.getPixelWriter();
	PixelWriter brighterWriter = brighterImage.getPixelWriter();
	PixelWriter semiTransparentWriter = semiTransparentImage.getPixelWriter();

		// Read one pixel at a time from the source and 
		// write it to the destinations
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				Color color = pixelReader.getColor(x, y);

				// Write a darker pixel to the new image
				darkerWriter.setColor(x, y, color.darker());

				// Write a brighter pixel to the new image
				brighterWriter.setColor(x, y, color.brighter());

				// Write a semi-transparent pixel to the new image
				semiTransparentWriter.setColor(x, y, 
					Color.color(color.getRed(), 
					            color.getGreen(), 
					            color.getBlue(), 
					            0.50));
			}
		}
	}
}
