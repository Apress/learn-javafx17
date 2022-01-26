// TilePaneTileAlignment.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TilePaneTileAlignment extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) { 		
		TilePane tileAlignCenter = createTilePane(Pos.CENTER);
		TilePane tileAlignTopRight = createTilePane(Pos.TOP_LEFT);

		HBox root = new HBox(tileAlignCenter, tileAlignTopRight);
		root.setFillHeight(false);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("The tileAlignment Property for TilePane");
		stage.show();
	}
	
	public TilePane createTilePane(Pos tileAlignment) {
		Button[] buttons = new Button[] {new Button("Tile"),	 
		                                 new Button("are"),
		                                 new Button("aligned"),
		                                 new Button("at"),
		                                 new Button(tileAlignment.toString())};
		
		TilePane tpane = new TilePane(5, 5, buttons);
		tpane.setTileAlignment(tileAlignment);
		tpane.setPrefColumns(3);		
		tpane.setStyle("-fx-padding: 10;" + 
		               "-fx-border-style: solid inside;" + 
		               "-fx-border-width: 2;" +
		               "-fx-border-insets: 5;" + 
		               "-fx-border-radius: 5;" + 
		               "-fx-border-color: blue;");
		return tpane;
	}
}
