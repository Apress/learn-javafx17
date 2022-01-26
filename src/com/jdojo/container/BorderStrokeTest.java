// BorderStrokeTest.java
package com.jdojo.container;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class BorderStrokeTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Pane p1 = this.getCSSStyledPane();
		Pane p2 = this.getObjectStyledPane();

		// Place p1 and p2
		p1.setLayoutX(20);
		p1.setLayoutY(20);
		p2.layoutYProperty().bind(p1.layoutYProperty());
		p2.layoutXProperty().bind(
				p1.layoutXProperty().add(p1.widthProperty()).add(40));

		Pane root = new Pane(p1, p2);
		root.setPrefSize(300, 120);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Setting Background Fills for a Region");
		stage.show();

		// Print borders details
		printBorderDetails(p1.getBorder(), p2.getBorder());
	}

	public Pane getCSSStyledPane() {
		Pane p = new Pane();
		p.setPrefSize(100, 50);
		p.setStyle("-fx-padding: 10;" + 
				"-fx-border-color: red, green, black;" + 
				"-fx-border-style: solid inside, solid outside, dashed centered;" + 
				"-fx-border-width: 10, 8, 1;" + 
				"-fx-border-insets: 12, -10, 0;" + 
				"-fx-border-radius: 0, 0, 0;");

		return p;
	}

	public Pane getObjectStyledPane() {
		Pane p = new Pane();
		p.setPrefSize(100, 50);
		p.setBackground(Background.EMPTY);
		p.setPadding(new Insets(10));

		// Create three border strokes
		BorderStroke redStroke = new BorderStroke(Color.RED,
				BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY,
				new BorderWidths(10),
				new Insets(12));

		BorderStrokeStyle greenStrokeStyle = new BorderStrokeStyle(StrokeType.OUTSIDE,
				StrokeLineJoin.MITER,
				StrokeLineCap.BUTT,
				10,
				0,
				null);
		BorderStroke greenStroke = new BorderStroke(Color.GREEN,
				greenStrokeStyle,
				CornerRadii.EMPTY,
				new BorderWidths(8),
				new Insets(-10));

		List<Double> dashArray = new ArrayList<>();
		dashArray.add(2.0);
		dashArray.add(1.4);

		BorderStrokeStyle blackStrokeStyle
				= new BorderStrokeStyle(StrokeType.CENTERED,
						StrokeLineJoin.MITER,
						StrokeLineCap.BUTT,
						10,
						0,
						dashArray);
		BorderStroke blackStroke = new BorderStroke(Color.BLACK,
				blackStrokeStyle,
				CornerRadii.EMPTY,
				new BorderWidths(1),
				new Insets(0));

		// Create a Border object with three BorderStroke objects
		Border b = new Border(redStroke, greenStroke, blackStroke);
		p.setBorder(b);

		return p;
	}

	private void printBorderDetails(Border cssBorder, Border objectBorder) {
		System.out.println("cssBorder insets:" + cssBorder.getInsets());
		System.out.println("cssBorder outsets:" + cssBorder.getOutsets());
		System.out.println("objectBorder insets:" + objectBorder.getInsets());
		System.out.println("objectBorder outsets:" + objectBorder.getOutsets());

		if (cssBorder.equals(objectBorder)) {
			System.out.println("Borders are equal.");
		} else {
			System.out.println("Borders are not equal.");
		}
	}
}
