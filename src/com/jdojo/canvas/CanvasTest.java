// CanvasTest.java
package com.jdojo.canvas;

import java.nio.ByteBuffer;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CanvasTest extends Application {
	private static final int RECT_WIDTH = 20;
	private static final int RECT_HEIGHT = 20;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Canvas canvas = new Canvas(400, 100);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Set line width and fill color
		gc.setLineWidth(2.0);
		gc.setFill(Color.RED);

		// Draw a rounded rectangle
		gc.strokeRoundRect(10, 10, 50, 50, 10, 10);
		
		// Fill an oval
	    gc.fillOval(70, 10, 50, 20);
	    
	    // Draw text
	    gc.strokeText("Hello Canvas", 10, 85);
	    
		// Draw an Image
		String imagePath = ResourceUtil.getResourceURLStr("picture/ksharan.jpg");
		Image image = new Image(imagePath); 
		gc.drawImage(image, 130, 10, 60, 80);
	    
		// Write custom pixels to create a pattern
		writePixels(gc);

		Pane root = new Pane();
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Drawing on a Canvas");
		stage.show();
	}
		
	private void writePixels(GraphicsContext gc) {
		byte[] pixels = this.getPixelsData();
		PixelWriter pixelWriter = gc.getPixelWriter();
		
		// Our data is in BYTE_RGB format
		PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteRgbInstance();
		
		int spacing = 5;
		int imageWidth = 200;
		int imageHeight = 100;
		
		// Roughly compute the number of rows and columns
		int rows = imageHeight/(RECT_HEIGHT + spacing);
		int columns = imageWidth/(RECT_WIDTH + spacing);
		
		// Write the pixels to the canvas
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {
				int xPos = 200 + x * (RECT_WIDTH + spacing);
				int yPos = y * (RECT_HEIGHT + spacing);
				pixelWriter.setPixels(xPos, yPos, 
				                      RECT_WIDTH, RECT_HEIGHT, 
				                      pixelFormat, 
				                      pixels, 0, 
				                      RECT_WIDTH * 3);
			}
		}
	}
	
	private byte[] getPixelsData() {
		// Each pixel in the w X h region will take 3 bytes
		byte[] pixels = new byte[RECT_WIDTH * RECT_HEIGHT * 3];

		// Height to width ration
		double ratio = 1.0 * RECT_HEIGHT/RECT_WIDTH;
		
		// Generate pixel data
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
}
