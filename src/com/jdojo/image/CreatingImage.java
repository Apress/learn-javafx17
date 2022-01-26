// CreatingImage.java
package com.jdojo.image;

import java.nio.ByteBuffer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CreatingImage extends Application {	
	private static final int RECT_WIDTH = 20;
	private static final int RECT_HEIGHT = 20;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		WritableImage newImage = new WritableImage(350, 100);
		
		// Get the pixels data
		byte[] pixels = getPixelsData();
	
		// Write pixels data to the image
		this.writePattern(newImage, pixels);
		
		// Display the new image in an ImageView
		ImageView newImageView = new ImageView(newImage);

		HBox root = new HBox(newImageView);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Creating an Image from Scratch");
		stage.show();
	}

	private byte[] getPixelsData() {
		// Each pixel takes 3 bytes
		byte[] pixels = new byte[RECT_WIDTH * RECT_HEIGHT * 3];

		// Height to width ratio
		double ratio = 1.0 * RECT_HEIGHT/RECT_WIDTH;

		// Generate pixels data
		for (int y = 0; y < RECT_HEIGHT; y++) {
			for (int x = 0; x < RECT_WIDTH; x++) {
				int i = y * RECT_WIDTH * 3 + x * 3;
				if (x <= y/ratio) {
					pixels[i] = -1;  // red -1 means 255 (-1 & 0xff = 255)
					pixels[i+1] = 0; // green = 0
					pixels[i+2] = 0; // blue = 0
				} else {
					pixels[i] = 0;    // red = 0
					pixels[i+1] = -1; // Green 255
					pixels[i+2] = 0;  // blue = 0
				}
			}
		}

		return pixels;
	}
	
	private void writePattern(WritableImage newImage, byte[] pixels) {
		PixelWriter pixelWriter = newImage.getPixelWriter();

		// Our data is in BYTE_RGB format
		PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteRgbInstance();

		int spacing = 5;
		int imageWidth = (int)newImage.getWidth();
		int imageHeight = (int)newImage.getHeight();
		
		// Roughly compute the number of rows and columns
		int rows = imageHeight/(RECT_HEIGHT + spacing);
		int columns = imageWidth/(RECT_WIDTH + spacing);
		
		// Write the pixels to the image
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {
				// Compute the current location inside the image where 
				// the rectangular region to be written
				int xPos = x * (RECT_WIDTH + spacing);
				int yPos = y * (RECT_HEIGHT + spacing);

				// Write the pixels data at he current location
				// defined by xPos and yPos
				pixelWriter.setPixels(xPos, yPos, 
				                      RECT_WIDTH, RECT_HEIGHT, 
				                      pixelFormat, 
				                      pixels, 0, 
				                      RECT_WIDTH * 3);	
			}
		}
	}
}
