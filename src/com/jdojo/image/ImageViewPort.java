// ImageViewPort.java
package com.jdojo.image;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ImageViewPort extends Application {
	private static final double VIEWPORT_WIDTH = 300;
	private static final double VIEWPORT_HEIGHT = 200;
	private double startX;
	private double startY;
	private ImageView imageView;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Load an image in its original size
		String imagePath = ResourceUtil.getResourceURLStr("picture/school_bus.jpg");
		Image image = new Image(imagePath);
		imageView = new ImageView(image);

		// Set a viewport for the ImageView
		Rectangle2D viewport = new Rectangle2D(0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		imageView.setViewport(viewport);

		// Set the mouse pressed and mouse dragged event hanlders
		imageView.setOnMousePressed(this::handleMousePressed);
		imageView.setOnMouseDragged(this::handleMouseDragged);

		HBox root = new HBox(imageView);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Viewing an Image in a Viewport");
		stage.show();
	}

	private void handleMousePressed(MouseEvent e) {
		startX = e.getX();
		startY = e.getY();
	}

	private void handleMouseDragged(MouseEvent e) {
		// How far the mouse was dragged
		double draggedDistanceX = e.getX() - startX;
		double draggedDistanceY = e.getY() - startY;

		// Reset the starting point for the next drag
		// if the user keeps the mouse pressed and drags again
		startX = e.getX();
		startY = e.getY();

		// Get the minX and minY of the current viewport
		double curMinX = imageView.getViewport().getMinX();
		double curMinY = imageView.getViewport().getMinY();

		// Move the new viewport by the dragged distance
		double newMinX = curMinX + draggedDistanceX;
		double newMinY = curMinY + draggedDistanceY;

		// Make sure the viewport does not fall outside the image area
		newMinX = clamp(newMinX, 0, imageView.getImage().getWidth() - VIEWPORT_WIDTH);
		newMinY = clamp(newMinY, 0, imageView.getImage().getHeight() - VIEWPORT_HEIGHT);

		// Set a new viewport
		imageView.setViewport(new Rectangle2D(newMinX, newMinY, VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
	}

	private double clamp(double value, double min, double max) {
		if (value < min) {
			return min;
		} else if (value > max) {
			return max;
		}

		return value;
	}
}
