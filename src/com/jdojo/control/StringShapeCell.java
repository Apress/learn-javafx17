// StringShapeCell.java
package com.jdojo.control;

import javafx.scene.control.ListCell;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class StringShapeCell extends ListCell<String> {
	@Override
	public void updateItem(String item, boolean empty) {
		// Need to call the super first
		super.updateItem(item, empty);

		// Set the text and graphic for the cell
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			setText(item);
			Shape shape = this.getShape(item);
			setGraphic(shape);
		}
	}

	public Shape getShape(String shapeType) {
		Shape shape = null;
		switch (shapeType.toLowerCase()) {
			case "line":
				shape = new Line(0, 10, 20, 10);
				break;
			case "rectangle":
				shape = new Rectangle(0, 0, 20, 20);
				break;
			case "circle":
				shape = new Circle(20, 20, 10);
				break;
			default:
				shape = null;
		}
		return shape;
	}
}
