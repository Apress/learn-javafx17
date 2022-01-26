// BorderImageTest.java
package com.jdojo.container;

import java.util.ArrayList;
import java.util.List;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderImage;
import javafx.scene.layout.BorderRepeat;
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

public class BorderImageTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) {
	 	// Get the URL of the image
		// TODO: book text
		var url = ResourceUtil.getResourceURLStr("picture/border_with_traingles.jpg");
		
		Pane p1 = this.getCSSStyledPane(url);
		Pane p2 = this.getObjectStyledPane(url);

		// Place p1 and p2
		p1.setLayoutX(20);
		p1.setLayoutY(20);
		p2.layoutYProperty().bind(p1.layoutYProperty());
		p2.layoutXProperty().bind(p1.layoutXProperty().add(p1.widthProperty()).add(20));

		Pane root = new Pane(p1, p2); 
		root.setPrefSize(260, 100);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Strokes and Images as a Border");
		stage.show();
	}

		
	public Pane getCSSStyledPane(String imageURL) {
		Pane p = new Pane();
		p.setPrefSize(100, 70);		
		p.setStyle("-fx-border-image-source: url('" + imageURL + "') ;" + 
		           "-fx-border-image-repeat: no-repeat;" + 
		           "-fx-border-image-slice: 9;" +  
		           "-fx-border-image-width: 9;" +  
		           "-fx-border-image-insets: 10;" + 
		           "-fx-border-color: black;" + 
		           "-fx-border-width: 1;" + 
		           "-fx-border-style: dashed inside;");

		return p;
	}

	public Pane getObjectStyledPane(String imageURL) {
		Pane p = new Pane();
		p.setPrefSize(100, 70);
		p.setBackground(Background.EMPTY);
		
		// Create a BorderImage object
		BorderWidths regionWidths = new BorderWidths(9);
		BorderWidths sliceWidth = new BorderWidths(9);
		boolean filled = false;
		BorderRepeat repeatX = BorderRepeat.STRETCH;
		BorderRepeat repeatY = BorderRepeat.STRETCH;
		BorderImage borderImage = new BorderImage(new Image(imageURL),
		                                          regionWidths, 
		                                          new Insets(10),
		                                          sliceWidth,
		                                          filled,
		                                          repeatX,
		                                          repeatY);

		// Set the Pane's boundary with a dashed stroke
		List<Double> dashArray = new ArrayList<>();
		dashArray.add(2.0);
		dashArray.add(1.4);
		BorderStrokeStyle blackStrokeStyle =
					new BorderStrokeStyle(StrokeType.INSIDE, 
										StrokeLineJoin.MITER, 
										StrokeLineCap.BUTT, 
										10, 
										0,
										dashArray);
		BorderStroke borderStroke = new BorderStroke(Color.BLACK, 
		                                             blackStrokeStyle, 
		                                             CornerRadii.EMPTY, 
		                                             new BorderWidths(1), 
		                                             new Insets(0));

		// Create a Border object with a stroke and an image
		BorderStroke[] strokes = new BorderStroke[] { borderStroke };
		BorderImage[] images = new BorderImage[] { borderImage };
		Border b = new Border(strokes, images);
		
		p.setBorder(b);

		return p;
	}
}
