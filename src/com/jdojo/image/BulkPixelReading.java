// BulkPixelReading.java
package com.jdojo.image;

import java.nio.ByteBuffer;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BulkPixelReading extends Application {
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
		stage.setTitle("Reading Pixels in Bulk");
		stage.show();
		
		// Read pixels in bulk from the image
		this.readPixelsInfo(image);
	}
	
	private void readPixelsInfo(Image image) {
		// Obtain the pixel reader from the image
		PixelReader pixelReader = image.getPixelReader();
		if (pixelReader == null) {
			System.out.println("Connot read pixels from the image");
			return;
		}

		// Read all pixels in a byte array in one go
		int x = 0;
		int y = 0;
		int width = (int)image.getWidth();
		int height = (int)image.getHeight();
		int offset = 0;
		int scanlineStride = width * 4;
		byte[] buffer = new byte[width * height * 4];

		// Get a WritablePixelFormat
		WritablePixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteBgraInstance();

		// Read all pixels at once
		pixelReader.getPixels(x, y, 
		                      width, height, 
		                      pixelFormat, 
		                      buffer, 
		                      offset, 
		                      scanlineStride);

		// Read the color of the pixel at (0, 0)
		int blue = (buffer[0] & 0xff);
		int green = (buffer[1] & 0xff);
		int red = (buffer[2] & 0xff);
		int alpha = (buffer[3] & 0xff);
		System.out.println("red=" + red +  ", green=" + green + 
		                   ", blue=" + blue +  ", alpha=" + alpha);

		// Get the color of the pixel at (0, 0)
		Color c = pixelReader.getColor(0, 0); 
		System.out.println("red=" + (int)(c.getRed() * 255) + 
		                   ", green=" + (int)(c.getGreen() * 255) + 
		                   ", blue=" + (int)(c.getBlue() * 255) + 
		                   ", alpha=" + (int)(c.getOpacity() * 255));
	}
}
