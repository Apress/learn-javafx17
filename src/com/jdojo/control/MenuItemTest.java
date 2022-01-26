// MenuItemTest.java
package com.jdojo.control;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuItemTest extends Application {
	// A canvas to draw shapes
	Canvas canvas = new Canvas(200, 200);
	
	// Create three RadioMenuItems for shapes
	RadioMenuItem rectItem = new RadioMenuItem("_Rectangle");
	RadioMenuItem circleItem = new RadioMenuItem("_Circle");
	RadioMenuItem ellipseItem = new RadioMenuItem("_Ellipse");

	// A menu item to draw stroke
	CheckMenuItem strokeItem = new CheckMenuItem("Draw _Stroke");

	// To adjust the stroke width
	Slider strokeWidthSlider = new Slider(1, 10, 1);
	CustomMenuItem strokeWidthItem = new CustomMenuItem(strokeWidthSlider, false);

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Menu fileMenu = getFileMenu();
		Menu optionsMenu = getOptionsMenu();

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, optionsMenu);

		// Draw the default shape, which is a Rectangle
		this.draw();
		
		BorderPane root = new BorderPane();
		root.setTop(menuBar);
		root.setCenter(canvas);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Different Types of Menu Items");
		stage.show(); 
	}
	
	public void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, 200, 200); // First clear the canvas
		
		// Set drawing parameters
		gc.setFill(Color.TAN);
		gc.setStroke(Color.RED);
		gc.setLineWidth(strokeWidthSlider.getValue());

		String shapeType = getSelectedShape();
		switch(shapeType) {
			case "Rectangle":
				gc.fillRect(0, 0, 200, 200);
				if (strokeItem.isSelected()) {
					gc.strokeRect(0, 0, 200, 200);
				}
				break;
			case "Circle":
				gc.fillOval(10, 10, 180, 180);
				if (strokeItem.isSelected()) {
					gc.strokeOval(10, 10, 180, 180);
				}
				break;
			case "Ellipse":
				gc.fillOval(10, 10, 180, 150);
				if (strokeItem.isSelected()) {
					gc.strokeOval(10, 10, 180, 150);
				}
				break;
			default:
				clear(); // Do not know the shape type
		}
	}
	
	public void clear() {
		canvas.getGraphicsContext2D().clearRect(0, 0, 200, 200); 
		this.rectItem.setSelected(false);
		this.circleItem.setSelected(false);
		this.ellipseItem.setSelected(false);
	}	
	
	public Menu getFileMenu() {
		Menu fileMenu = new Menu("_File");

		// Make Rectangle the default option
		rectItem.setSelected(true);

		// Set Key Combinations for shapes
		KeyCombination kr = 
				new KeyCodeCombination(KeyCode.R, KeyCombination.ALT_DOWN);
		KeyCombination kc = 
				new KeyCodeCombination(KeyCode.C, KeyCombination.ALT_DOWN);
		KeyCombination ke = 
				new KeyCodeCombination(KeyCode.E, KeyCombination.ALT_DOWN);
		rectItem.setAccelerator(kr);
		circleItem.setAccelerator(kc);
		ellipseItem.setAccelerator(ke);

		// Add ActionEvent handler to all shape radio menu items
		rectItem.setOnAction(e -> draw());
		circleItem.setOnAction(e -> draw());
		ellipseItem.setOnAction(e -> draw());

		// Add RadioMenuItems to a ToggleGroup to make them mutually exclusive
		ToggleGroup shapeGroup = new ToggleGroup();
		shapeGroup.getToggles().addAll(rectItem, circleItem, ellipseItem);

		MenuItem clearItem = new MenuItem("Cle_ar");
		clearItem.setOnAction(e -> clear());

		MenuItem exitItem = new MenuItem("E_xit");
		exitItem.setOnAction(e -> Platform.exit());

		// Add menu items to the File menu
		fileMenu.getItems().addAll(rectItem, 
		                           circleItem, ellipseItem, 
		                           new SeparatorMenuItem(),
		                           clearItem,
		                           new SeparatorMenuItem(),
		                           exitItem);
		return fileMenu;
	}
	
	public Menu getOptionsMenu() {
		// Draw stroke by default		
		strokeItem.setSelected(true);

		// Redraw the shape when draw stroke option toggles
		strokeItem.setOnAction(e -> syncStroke());
		
		// Configure the slider
		strokeWidthSlider.setShowTickLabels(true);
		strokeWidthSlider.setShowTickMarks(true);
		strokeWidthSlider.setMajorTickUnit(2);	    
		strokeWidthSlider.setSnapToPixel(true);
		strokeWidthSlider.valueProperty().addListener(this::strokeWidthChanged);

		Menu optionsMenu = new Menu("_Options"); 
		optionsMenu.getItems().addAll(strokeItem, this.strokeWidthItem);
		
		return optionsMenu;
	}
	
	public void strokeWidthChanged (ObservableValue<? extends Number> prop, 
	                                Number oldValue, 
	                                Number newValue) {
		draw();
	}
	
	public String getSelectedShape() {
		if (rectItem.isSelected()) {
			return "Rectangle";
		}
		else if (circleItem.isSelected()) {
			return "Circle";
		}
		else if (ellipseItem.isSelected()) {
			return "Ellipse";
		}
		else {
			return "";
		}
	}
	
	public void syncStroke() {
		// Enable/disable the slider
		strokeWidthSlider.setDisable(!strokeItem.isSelected());
		draw();
	}
}
