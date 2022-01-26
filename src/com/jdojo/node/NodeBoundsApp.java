// NodeBoundsApp.java
package com.jdojo.node;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.embed.swing.SwingFXUtils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.util.Duration;
import javafx.util.converter.DoubleStringConverter;

import javax.imageio.ImageIO;

/**
 * Demonstrates how three bounds - layoutBounds, boundsInLocal, and
 * boundsInParent - are computed for a node. JavaFX 2.2 is required to use this
 * class.
 *
 * @author Kishori Sharan (www.jdojo.com)
 * @version 1.0
 *
 *          <b>@copyright</b> You can use, modify, copy-paste the code for any
 *          purpose, in any way you see fit. No restrictions apply.
 */
@SuppressWarnings("deprecation")
public class NodeBoundsApp extends Application {
	private static class VBoxUtil {
		public static VBox fromChildren(Node... c) {
			VBox vb = new VBox();
			vb.getChildren().addAll(c);
			return vb;
		}
	}

	// TextArea printDataTextArea = new TextArea("\n\n\n");

	final static int AXIS_LENGTH = 150;
	final static int ARROW_LENGTH = 5;

	final static int RECTANGLE_WIDTH = 50;
	final static int RECTANGLE_HEIGHT = 20;
	final static double RECTANGLE_OPACITY = 0.5;
	final static Color RECTANGLE_STROKE_COLOR = Color.PURPLE;
	final static String RECTANGLE_STROKE_COLOR_STR = "PURPLE";
	final static double RECTANGLE_STROKE_WIDTH = 0.0;
	final static Color RECTANGLE_FILL_COLOR = Color.DARKGRAY;
	final static String RECTANGLE_FILL_COLOR_STR = "DARKGRAY";

	Slider xSlider = new Slider(-100, 100, 0);
	Slider ySlider = new Slider(-100, 100, 0);
	Slider widthSlider = new Slider(10, 200, RECTANGLE_WIDTH);
	Slider heightSlider = new Slider(10, 200, RECTANGLE_HEIGHT);
	Slider opacitySlider = new Slider(0, 1.0, RECTANGLE_OPACITY);
	Slider strokeSlider = new Slider(0.0, 10.0, RECTANGLE_STROKE_WIDTH);

	ObservableList<String> colorList = FXCollections.observableArrayList("BLUE", "DARKBLUE", "DARKGRAY", "DARKRED",
			"GRAY", "GREEN", "RED", "PURPLE", "YELLOW", "BLACK");
	ChoiceBox<String> rectFillColorChoiceBox = new ChoiceBox<String>(colorList);
	ChoiceBox<String> rectStrokeColorChoiceBox = new ChoiceBox<String>(colorList);

	Slider translateXSlider = new Slider(-200, 200, 0);
	Slider translateYSlider = new Slider(-200, 200, 0);
	Slider rotateSlider = new Slider(0, 360, 0);
	Slider scaleXSlider = new Slider(0.20, 2.0, 1.0);
	Slider scaleYSlider = new Slider(0.20, 2.0, 1.0);
	Slider shearXSlider = new Slider(-1.0, 1.0, 0);
	Slider shearYSlider = new Slider(-1.0, 1.0, 0);

	Label xLabel = new Label("");
	Label yLabel = new Label("");
	Label widthLabel = new Label("");
	Label heightLabel = new Label("");
	Label opacityLabel = new Label("");
	Label strokeLabel = new Label("");

	Label translateXLabel = new Label("");
	Label translateYLabel = new Label("");
	Label rotateLabel = new Label("");
	Label scaleXLabel = new Label("");
	Label scaleYLabel = new Label("");
	Label shearXLabel = new Label("");
	Label shearYLabel = new Label("");

	Rectangle mainRect = new Rectangle(0, 0, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
	Rectangle localBoundsRect = new Rectangle();
	Rectangle parentBoundsRect = new Rectangle();
	Rectangle layoutBoundsRect = new Rectangle();

	Line localXAxis = new Line(0, 0, AXIS_LENGTH, 0);
	Line localYAxis = new Line(0, 0, 0, AXIS_LENGTH);
	Polygon localXArrow = new Polygon(AXIS_LENGTH, -ARROW_LENGTH, AXIS_LENGTH, ARROW_LENGTH, AXIS_LENGTH + ARROW_LENGTH,
			0, AXIS_LENGTH, -ARROW_LENGTH);
	Text localXAxisLabel = new Text("x-axis");

	Polygon localYArrow = new Polygon(0, AXIS_LENGTH + ARROW_LENGTH, -ARROW_LENGTH, AXIS_LENGTH, ARROW_LENGTH,
			AXIS_LENGTH, 0, AXIS_LENGTH + ARROW_LENGTH);
	Text localYAxisLabel = new Text("y-axis");
	Group localXAxisGroup = new Group(localXAxis, localXArrow, localXAxisLabel);
	Group localYAxisGroup = new Group(localYAxis, localYArrow, localYAxisLabel);

	Line parentXAxis = new Line(0, 0, AXIS_LENGTH, 0);
	Line parentYAxis = new Line(0, 0, 0, AXIS_LENGTH);

	Polygon parentXArrow = new Polygon(AXIS_LENGTH, -ARROW_LENGTH, AXIS_LENGTH, ARROW_LENGTH,
			AXIS_LENGTH + ARROW_LENGTH, 0, AXIS_LENGTH, -ARROW_LENGTH);
	Text parentXAxisLabel = new Text("x-axis");

	Polygon parentYArrow = new Polygon(0, AXIS_LENGTH + ARROW_LENGTH, -ARROW_LENGTH, AXIS_LENGTH, ARROW_LENGTH,
			AXIS_LENGTH, 0, AXIS_LENGTH + ARROW_LENGTH);
	Text parentYAxisLabel = new Text("y-axis");
	Group parentXAxisGroup = new Group(parentXAxis, parentXArrow, parentXAxisLabel);
	Group parentYAxisGroup = new Group(parentYAxis, parentYArrow, parentYAxisLabel);

	CheckBox layoutCbx = new CheckBox("Show");
	CheckBox localCbx = new CheckBox("Show");
	CheckBox parentCbx = new CheckBox("Show");

	CheckBox layoutAnimateCbx = new CheckBox("Animate");
	CheckBox localAnimateCbx = new CheckBox("Animate");
	CheckBox parentAnimateCbx = new CheckBox("Animate");

	ToggleGroup effectGroup = new ToggleGroup();
	RadioButton noneEffect = new RadioButton("None");
	RadioButton dropshadowEffect = new RadioButton("DropShadow");
	RadioButton reflectionEffect = new RadioButton("Reflection");
	RadioButton glowEffect = new RadioButton("Glow");

	final Color LOCAL_BOUNDS_RECT_FILL_COLOR = Color.WHITE;
	final Color PARENT_BOUNDS_RECT_FILL_COLOR = Color.WHITE;
	final Color LAYOUT_BOUNDS_RECT_FILL_COLOR = Color.WHITE;

	final Color LOCAL_BOUNDS_RECT_STROKE_COLOR = Color.BLUE;
	final Color PARENT_BOUNDS_RECT_STROKE_COLOR = Color.RED;
	final Color LAYOUT_BOUNDS_RECT_STROKE_COLOR = Color.BLACK;

	final double BOUNDS_STROKE_WIDTH = 2.0;
	final double BOUNDS_PATH_CIRCLE_RADIUS = 4.0;

	// Animating the bounds bounding boxes
	final Circle LOCAL_BOUNDS_PATH_CIRCLE = new Circle(0, 0, BOUNDS_PATH_CIRCLE_RADIUS);
	final Circle PARENT_BOUNDS_PATH_CIRCLE = new Circle(0, 0, BOUNDS_PATH_CIRCLE_RADIUS);
	final Circle LAYOUT_BOUNDS_PATH_CIRCLE = new Circle(0, 0, BOUNDS_PATH_CIRCLE_RADIUS);

	final PathTransition LOCAL_BOUNDS_PATH_TRANSITION = new PathTransition();
	final PathTransition PARENT_BOUNDS_PATH_TRANSITION = new PathTransition();
	final PathTransition LAYOUT_BOUNDS_PATH_TRANSITION = new PathTransition();

	public class BoundsRecord {
		private DoubleProperty x = new SimpleDoubleProperty();
		private DoubleProperty y = new SimpleDoubleProperty();
		private DoubleProperty w = new SimpleDoubleProperty();
		private DoubleProperty h = new SimpleDoubleProperty();

		private StringProperty boundsType = new SimpleStringProperty();

		public BoundsRecord(String boundsType) {
			this.boundsType.set(boundsType);
		}

		public BoundsRecord(String boundsType, Bounds b) {
			this.boundsType.set(boundsType);
			x.set(b.getMinX());
			y.set(b.getMinY());
			w.set(b.getWidth());
			h.set(b.getHeight());
		}

		public void update(Bounds b) {
			x.set(b.getMinX());
			y.set(b.getMinY());
			w.set(b.getWidth());
			h.set(b.getHeight());
		}

		public String getBoundsType() {
			return boundsType.get();
		}

		public double getX() {
			return x.get();
		}

		public double getY() {
			return y.get();
		}

		public double getW() {
			return w.get();
		}

		public double getH() {
			return h.get();
		}

		public StringProperty boundsTypeProperty() {
			return this.boundsType;
		}

		public DoubleProperty xProperty() {
			return this.x;
		}

		public DoubleProperty yProperty() {
			return this.y;
		}

		public DoubleProperty wProperty() {
			return this.w;
		}

		public DoubleProperty hProperty() {
			return this.h;
		}
	}

	BoundsRecord layoutBoundsData = new BoundsRecord("layoutBounds");
	BoundsRecord localBoundsData = new BoundsRecord("boundsInLocal");
	BoundsRecord parentBoundsData = new BoundsRecord("boundsInParent");

	ObservableList<BoundsRecord> boundsList = FXCollections.observableArrayList(layoutBoundsData, localBoundsData,
			parentBoundsData);
	private TableView<BoundsRecord> boundsTableView = new TableView<>(boundsList);

	private Node boundsLayoutNode;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() {
		int axisLabelPos = AXIS_LENGTH + ARROW_LENGTH + 4;
		localXAxisLabel.setLayoutX(axisLabelPos);
		localXAxisLabel.setTextOrigin(VPos.CENTER);
		localYAxisLabel.setLayoutY(axisLabelPos);
		localYAxisLabel.setTextOrigin(VPos.CENTER);

		parentXAxisLabel.setLayoutX(axisLabelPos);
		parentXAxisLabel.setTextOrigin(VPos.CENTER);
		parentYAxisLabel.setLayoutY(axisLabelPos);
		parentYAxisLabel.setTextOrigin(VPos.CENTER);

		String textFont = "-fx-font-size:9;";
		localXAxisLabel.setStyle(textFont);
		localYAxisLabel.setStyle(textFont);
		parentXAxisLabel.setStyle(textFont);
		parentYAxisLabel.setStyle(textFont);

		mainRect.setFill(RECTANGLE_FILL_COLOR);
		rectFillColorChoiceBox.setValue(RECTANGLE_FILL_COLOR_STR);
		rectStrokeColorChoiceBox.setValue(RECTANGLE_STROKE_COLOR_STR);

		initializeBoundsDetails();
		initializeBoundsPathTransition();

		// Bind labels to slider values
		bindLabelsToSliders();

		// Initialize Bounds Table View
		initializeBoundsTableView();
	}

	@Override
	public void start(Stage stage) {
		BorderPane root = new BorderPane();

		Pane parentContainer = new Pane();
		parentContainer.setPrefSize(500, 500);
		parentContainer.setPickOnBounds(false);

		// Pane parent = new Pane();
		Group parent = new Group();
		boundsLayoutNode = parent;
		// parent.setPrefSize(300, 200);
		parent.setLayoutX(200);
		parent.setLayoutY(200);
		parent.setStyle("-fx-background-color:white;");
		parent.getChildren().addAll(new Group(localXAxisGroup, localYAxisGroup),
				new Group(parentXAxisGroup, parentYAxisGroup), new Group(parentBoundsRect, PARENT_BOUNDS_PATH_CIRCLE),
				new Group(localBoundsRect, LOCAL_BOUNDS_PATH_CIRCLE),
				new Group(layoutBoundsRect, LAYOUT_BOUNDS_PATH_CIRCLE), new Group(mainRect));

		parentContainer.getChildren().addAll(parent);

		VBox transformsControls = getTransformationControls();
		VBox resultsControls = getResultsControls();

		BorderPane nestedPane = new BorderPane();
		nestedPane.setCenter(parentContainer);
		nestedPane.setBottom(resultsControls);
		// nestedPane.setTop(printDataTextArea);
		// printDataTextArea.setPrefColumnCount(40);
		// printDataTextArea.setPrefRowCount(3);
		root.setCenter(nestedPane);

		root.setRight(transformsControls);
		// root.setBottom(resultsControls);
		// root.setCenter(parentContainer);

		// Attach event handlers
		attachEventHandlers();

		Scene scene = new Scene(root); // , 600, 400);
		stage.setScene(scene);
		stage.setTitle("Bounds of a Node");
		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
		stage.setX(visualBounds.getMinX());
		stage.setY(visualBounds.getMinY());
		stage.setWidth(visualBounds.getWidth());
		stage.setHeight(visualBounds.getHeight());
		stage.show();

		// Make sure everything is in sync
		relayout();
	}

	private void initializeBoundsDetails() {
		localBoundsRect.setStroke(LOCAL_BOUNDS_RECT_STROKE_COLOR);
		localBoundsRect.setFill(LOCAL_BOUNDS_RECT_FILL_COLOR);
		localBoundsRect.setStrokeWidth(BOUNDS_STROKE_WIDTH);
		localBoundsRect.setOpacity(0.5);

		parentBoundsRect.setStroke(PARENT_BOUNDS_RECT_STROKE_COLOR);
		parentBoundsRect.setFill(PARENT_BOUNDS_RECT_FILL_COLOR);
		parentBoundsRect.setStrokeWidth(BOUNDS_STROKE_WIDTH);
		parentBoundsRect.setOpacity(0.5);

		layoutBoundsRect.setStroke(LAYOUT_BOUNDS_RECT_STROKE_COLOR);
		layoutBoundsRect.setFill(LAYOUT_BOUNDS_RECT_FILL_COLOR);
		layoutBoundsRect.setStrokeWidth(BOUNDS_STROKE_WIDTH);
		layoutBoundsRect.setOpacity(0.5);

		LOCAL_BOUNDS_PATH_CIRCLE.setFill(LOCAL_BOUNDS_RECT_STROKE_COLOR);
		PARENT_BOUNDS_PATH_CIRCLE.setFill(PARENT_BOUNDS_RECT_STROKE_COLOR);
		LAYOUT_BOUNDS_PATH_CIRCLE.setFill(LAYOUT_BOUNDS_RECT_STROKE_COLOR);

		LOCAL_BOUNDS_PATH_CIRCLE.setVisible(false);
		LAYOUT_BOUNDS_PATH_CIRCLE.setVisible(false);
		PARENT_BOUNDS_PATH_CIRCLE.setVisible(false);
	}

	private VBox getResultsControls() {
		VBox vBox = new VBox();
		vBox.setSpacing(5);
		vBox.getChildren().addAll(boundsTableView);
		return vBox;
	}

	private void bindLabelsToSliders() {
		xLabel.textProperty().bind(new SimpleStringProperty("x:").concat(xSlider.valueProperty().asString("%.2f")));
		yLabel.textProperty().bind(new SimpleStringProperty("y:").concat(ySlider.valueProperty().asString("%.2f")));

		widthLabel.textProperty()
				.bind(new SimpleStringProperty("width:").concat(widthSlider.valueProperty().asString("%.2f")));
		heightLabel.textProperty()
				.bind(new SimpleStringProperty("height:").concat(heightSlider.valueProperty().asString("%.2f")));
		opacityLabel.textProperty()
				.bind(new SimpleStringProperty("opacity:").concat(opacitySlider.valueProperty().asString("%.2f")));
		strokeLabel.textProperty()
				.bind(new SimpleStringProperty("stroke:").concat(strokeSlider.valueProperty().asString("%.2f")));

		translateXLabel.textProperty().bind(
				new SimpleStringProperty("TranslateX:").concat(translateXSlider.valueProperty().asString("%.2f")));
		translateYLabel.textProperty().bind(
				new SimpleStringProperty("TranslateY:").concat(translateYSlider.valueProperty().asString("%.2f")));

		rotateLabel.textProperty().bind(new SimpleStringProperty("Rotate:")
				.concat(rotateSlider.valueProperty().asString("%.2f")).concat(" deg"));

		scaleXLabel.textProperty()
				.bind(new SimpleStringProperty("ScaleX:").concat(scaleXSlider.valueProperty().asString("%.2f")));
		scaleYLabel.textProperty()
				.bind(new SimpleStringProperty("ScaleY:").concat(scaleYSlider.valueProperty().asString("%.2f")));

		shearXLabel.textProperty()
				.bind(new SimpleStringProperty("ShearX:").concat(shearXSlider.valueProperty().asString("%.2f")));
		shearYLabel.textProperty()
				.bind(new SimpleStringProperty("ShearY:").concat(shearYSlider.valueProperty().asString("%.2f")));
	}

	private VBox getTransformationControls() {
		xSlider.setShowTickMarks(true);
		xSlider.setShowTickLabels(true);
		xSlider.snapToTicksProperty().set(true);

		ySlider.setShowTickMarks(true);
		ySlider.setShowTickLabels(true);
		ySlider.snapToTicksProperty().set(true);

		widthSlider.setShowTickMarks(true);
		widthSlider.setShowTickLabels(true);
		widthSlider.snapToTicksProperty().set(true);

		heightSlider.setShowTickMarks(true);
		heightSlider.setShowTickLabels(true);
		heightSlider.snapToTicksProperty().set(true);

		opacitySlider.setShowTickMarks(true);
		opacitySlider.setShowTickLabels(true);
		opacitySlider.snapToTicksProperty().set(true);
		opacitySlider.setMinorTickCount(5);
		opacitySlider.setMajorTickUnit(0.20d);

		strokeSlider.setShowTickMarks(true);
		strokeSlider.setShowTickLabels(true);
		strokeSlider.snapToTicksProperty().set(true);
		strokeSlider.setMinorTickCount(5);
		strokeSlider.setMajorTickUnit(1.0d);

		translateXSlider.setShowTickMarks(true);
		translateXSlider.setShowTickLabels(true);
		translateXSlider.snapToTicksProperty().set(true);

		translateYSlider.setShowTickMarks(true);
		translateYSlider.setShowTickLabels(true);
		translateYSlider.snapToTicksProperty().set(true);

		rotateSlider.setShowTickMarks(true);
		rotateSlider.setShowTickLabels(true);
		rotateSlider.snapToTicksProperty().set(true);
		rotateSlider.setMinorTickCount(5);
		rotateSlider.setMajorTickUnit(30.0);

		scaleXSlider.setShowTickMarks(true);
		scaleXSlider.setShowTickLabels(true);
		scaleXSlider.setMajorTickUnit(0.2d);
		scaleXSlider.setLabelFormatter(new DoubleStringConverter());
		scaleXSlider.snapToTicksProperty().set(true);

		scaleYSlider.setShowTickMarks(true);
		scaleYSlider.setShowTickLabels(true);
		scaleYSlider.setMajorTickUnit(0.2d);
		scaleYSlider.setLabelFormatter(new DoubleStringConverter());
		scaleYSlider.snapToTicksProperty().set(true);

		shearXSlider.setShowTickMarks(true);
		shearXSlider.setShowTickLabels(true);
		shearXSlider.setMajorTickUnit(0.2d);
		shearXSlider.setLabelFormatter(new DoubleStringConverter());
		shearXSlider.snapToTicksProperty().set(true);

		shearYSlider.setShowTickMarks(true);
		shearYSlider.setShowTickLabels(true);
		shearYSlider.setMajorTickUnit(0.2d);
		shearYSlider.setLabelFormatter(new DoubleStringConverter());
		shearYSlider.snapToTicksProperty().set(true);

		HBox xyBox = new HBox();
		xyBox.setSpacing(5);
		xyBox.getChildren().addAll(VBoxUtil.fromChildren(xLabel, xSlider), VBoxUtil.fromChildren(yLabel, ySlider));

		HBox whBox = new HBox();
		whBox.setSpacing(5);
		whBox.getChildren().addAll(VBoxUtil.fromChildren(widthLabel, widthSlider),
				VBoxUtil.fromChildren(heightLabel, heightSlider));

		HBox colorBox = new HBox();
		colorBox.setSpacing(5);
		colorBox.getChildren().addAll(VBoxUtil.fromChildren(strokeLabel, strokeSlider),
				VBoxUtil.fromChildren(new Label("Stroke Color"), rectStrokeColorChoiceBox));

		HBox opacityBox = new HBox();
		opacityBox.setSpacing(5);
		opacityBox.getChildren().addAll(VBoxUtil.fromChildren(opacityLabel, opacitySlider),
				VBoxUtil.fromChildren(new Label("Fill Color"), rectFillColorChoiceBox));

		HBox translateBox = new HBox();
		translateBox.setSpacing(5);
		translateBox.getChildren().addAll(VBoxUtil.fromChildren(translateXLabel, translateXSlider),
				VBoxUtil.fromChildren(translateYLabel, translateYSlider));

		HBox rotateBox = new HBox();
		rotateBox.setSpacing(5);
		rotateBox.getChildren().addAll(VBoxUtil.fromChildren(rotateLabel, rotateSlider));

		HBox scaleBox = new HBox();
		scaleBox.setSpacing(5);
		scaleBox.getChildren().addAll(VBoxUtil.fromChildren(scaleXLabel, scaleXSlider),
				VBoxUtil.fromChildren(scaleYLabel, scaleYSlider));

		HBox shearBox = new HBox();
		shearBox.setSpacing(5);
		shearBox.getChildren().addAll(VBoxUtil.fromChildren(shearXLabel, shearXSlider),
				VBoxUtil.fromChildren(shearYLabel, shearYSlider));

		VBox rectangleBox = new VBox();
		rectangleBox.getChildren().addAll(xyBox, whBox, colorBox, opacityBox);
		TitledPane rectangleProps = new TitledPane("Rectangle", rectangleBox);

		VBox transformBox = new VBox();
		transformBox.getChildren().addAll(translateBox, rotateBox, scaleBox, shearBox);
		TitledPane transformsProps = new TitledPane("Tranformations", transformBox);

		TitledPane showBoundsControls = getShowBoundsControls();
		TitledPane effectPane = getEffectTitledPane();

		Button resetAllButton = new Button("Reset All");
		resetAllButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				resetAll();
			}
		});

		Button saveButton = new Button("Save Layout as Image");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				saveLayoutAsImage();
			}
		});

		Button exitButton = new Button("Exit");
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				Platform.exit();
			}
		});

		/*
		 * Button printButton = new Button("Print"); printButton.setOnAction(new
		 * EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent actionEvent) { String str =
		 * getDesc("layoutBounds", mainRect.getLayoutBounds()) +
		 * getDesc("\nboundsInLocal", mainRect.getBoundsInLocal()) +
		 * getDesc("\nboundsInParent", mainRect.getBoundsInParent());
		 * 
		 * //printDataTextArea.setText(str); }
		 * 
		 * private String getDesc(String type, Bounds b) { String str = type + "[minX="
		 * + b.getMinX() + ", minY=" + b.getMinY() + ", width=" + b.getWidth() +
		 * ", height=" + b.getHeight() + "]"; return str; } });
		 */

		HBox buttonBox = new HBox();
		buttonBox.setSpacing(10);
		buttonBox.getChildren().addAll(resetAllButton, saveButton, exitButton);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(buttonBox, showBoundsControls, rectangleProps, effectPane, transformsProps);
		return vBox;
	}

	private void saveLayoutAsImage() {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Portable Network Graphics(*.png)",
				Collections.singletonList("*.png")));
		File output = fc.showSaveDialog(boundsLayoutNode.getScene().getWindow());
		if (output == null) {
			return;
		} else {
			String fn = output.getName();
			if (!fn.toLowerCase().endsWith(".png")) {
				output = new File(output.getParent(), fn + ".png");
			}
		}

		try {
			WritableImage wImg = boundsLayoutNode.snapshot(null, null);
			// WritableImage wImg = mainRect.snapshot(null, null);
			BufferedImage bImg = SwingFXUtils.fromFXImage(wImg, null);
			ImageIO.write(bImg, "png", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void initializeBoundsTableView() {
		boundsTableView.setPrefSize(200, 100);
		boundsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		boundsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<BoundsRecord, String> boundsTypeCol = new TableColumn<>("Bounds Type");
		boundsTypeCol.setCellValueFactory(new PropertyValueFactory<>("boundsType"));

		TableColumn<BoundsRecord, Double> xCol = new TableColumn<>("MinX");
		xCol.setCellValueFactory(new PropertyValueFactory<>("x"));

		TableColumn<BoundsRecord, Double> yCol = new TableColumn<>("MinY");
		yCol.setCellValueFactory(new PropertyValueFactory<>("y"));

		TableColumn<BoundsRecord, Double> wCol = new TableColumn<>("Width");
		wCol.setCellValueFactory(new PropertyValueFactory<>("w"));

		TableColumn<BoundsRecord, Double> hCol = new TableColumn<>("Height");
		hCol.setCellValueFactory(new PropertyValueFactory<>("h"));

		boundsTableView.getColumns().setAll(boundsTypeCol, xCol, yCol, wCol, hCol);
	}

	private void drawAllAxes() {

		// If local and parent coordinate spaces are the same,
		// we will draw axes only once
		// Transform a non-zero (x, y) point from local to parent.
		// If we get the same point back, it means two coordinate spaces are the same
		double x = 100; // any value will do
		double y = 100; // any value will do

		Point2D p = mainRect.localToParent(x, y);

		if (p.getY() == y) {
			// We do not draw parent x-axis
			parentXArrow.setVisible(false);
			parentXAxisLabel.setVisible(false);

			localXAxisLabel.setText("x-axis (Parent/Untransformed Local)");
			drawLocalAxis();
		} else {
			this.parentXArrow.setVisible(true);
			this.parentXAxisLabel.setVisible(true);

			localXAxisLabel.setText("x-axis (Transformed Local)");
			parentXAxisLabel.setText("x-axis (Parent/Untransformed Local)");
			drawLocalAxis();
			drawParentAxis();
		}

		if (p.getX() == x) {
			// We do not draw parent y-axis
			parentYArrow.setVisible(false);
			parentYAxisLabel.setVisible(false);

			localYAxisLabel.setText("y-axis (Parent/Untransformed Local)");
			drawLocalAxis();
		} else {
			parentYArrow.setVisible(true);
			parentYAxisLabel.setVisible(true);

			localYAxisLabel.setText("y-axis (Transformed Local)");
			parentYAxisLabel.setText("y-axis (Parent/Untransformed Local)");
			drawLocalAxis();
			drawParentAxis();
		}
	}

	private void drawLocalAxis() {
		localYAxisLabel.setLayoutX(-1.0 * localYAxisLabel.layoutBoundsProperty().get().getWidth() / 2.0);

		localXAxisGroup.getTransforms().clear();
		localYAxisGroup.getTransforms().clear();

		List<Transform> t = getTrsnaforms(true, true);
		localXAxisGroup.getTransforms().addAll(t);
		localYAxisGroup.getTransforms().addAll(t);
	}

	private void drawParentAxis() {
		parentYAxisLabel.setLayoutX(-1.0 * parentYAxisLabel.layoutBoundsProperty().get().getWidth() / 2.0);
	}

	private void attachEventHandlers() {
		ChangeListener<Number> cl = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				relayout();
			}
		};

		ChangeListener<String> clStr = new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				relayout();
			}
		};

		xSlider.valueProperty().addListener(cl);
		ySlider.valueProperty().addListener(cl);
		widthSlider.valueProperty().addListener(cl);
		heightSlider.valueProperty().addListener(cl);

		strokeSlider.valueProperty().addListener(cl);
		rectStrokeColorChoiceBox.valueProperty().addListener(clStr);

		opacitySlider.valueProperty().addListener(cl);
		rectFillColorChoiceBox.valueProperty().addListener(clStr);

		translateXSlider.valueProperty().addListener(cl);
		translateYSlider.valueProperty().addListener(cl);
		rotateSlider.valueProperty().addListener(cl);
		scaleXSlider.valueProperty().addListener(cl);
		scaleYSlider.valueProperty().addListener(cl);
		shearXSlider.valueProperty().addListener(cl);
		shearYSlider.valueProperty().addListener(cl);
	}

	private void relayout() {
		mainRect.getTransforms().clear();

		mainRect.setWidth(widthSlider.getValue());
		mainRect.setHeight(heightSlider.getValue());
		mainRect.setX(xSlider.getValue());
		mainRect.setY(ySlider.getValue());

		mainRect.getTransforms().addAll(getTrsnaforms(true, true));

		mainRect.setEffect(this.getEffect());

		mainRect.setStrokeWidth(strokeSlider.getValue());
		mainRect.setStroke(RECTANGLE_STROKE_COLOR);
		mainRect.setStroke(Color.web(rectStrokeColorChoiceBox.getValue()));

		mainRect.setOpacity(opacitySlider.getValue());
		mainRect.setFill(Color.web(rectFillColorChoiceBox.getValue()));

		drawAllAxes();
		displayResults();
		showBounds();
		restartBoundsTransitions();
	}

	private void restartBoundsTransitions() {
		LAYOUT_BOUNDS_PATH_TRANSITION.stop();
		LOCAL_BOUNDS_PATH_TRANSITION.stop();
		PARENT_BOUNDS_PATH_TRANSITION.stop();
		this.animate();
	}

	private List<Transform> getTrsnaforms(boolean includeScale, boolean includeShear) {
		double tx = translateXSlider.getValue();
		double ty = translateYSlider.getValue();
		double scaleX = scaleXSlider.getValue();
		double scaleY = scaleYSlider.getValue();
		double shearX = shearXSlider.getValue();
		double shearY = shearYSlider.getValue();
		double rotation = rotateSlider.getValue();

		List<Transform> list = new ArrayList<Transform>();

		list.add(new Translate(tx, ty));
		list.add(new Rotate(rotation));

		if (includeScale) {
			list.add(new Scale(scaleX, scaleY));
		}

		if (includeShear) {
			list.add(new Shear(shearX, shearY));
		}

		return list;
	}

	private void showBounds() {
		if (layoutCbx.isSelected()) {
			Bounds b = mainRect.getLayoutBounds();
			layoutBoundsRect.setX(b.getMinX());
			layoutBoundsRect.setY(b.getMinY());
			layoutBoundsRect.setWidth(b.getWidth());
			layoutBoundsRect.setHeight(b.getHeight());

			layoutBoundsRect.setVisible(true);
		} else {
			layoutBoundsRect.setVisible(false);
		}

		if (localCbx.isSelected()) {
			Bounds b1 = mainRect.getBoundsInLocal();

			Bounds b = b1; // mainRect.localToParent(b1);
			localBoundsRect.setX(b.getMinX());
			localBoundsRect.setY(b.getMinY());
			localBoundsRect.setWidth(b.getWidth());
			localBoundsRect.setHeight(b.getHeight());
			localBoundsRect.setVisible(true);
		} else {
			localBoundsRect.setVisible(false);
		}

		if (parentCbx.isSelected()) {
			Bounds b = mainRect.getBoundsInParent();
			parentBoundsRect.setX(b.getMinX());
			parentBoundsRect.setY(b.getMinY());
			parentBoundsRect.setWidth(b.getWidth());
			parentBoundsRect.setHeight(b.getHeight());
			parentBoundsRect.setVisible(true);
		} else {
			parentBoundsRect.setVisible(false);
		}
	}

	private TitledPane getShowBoundsControls() {
		ChangeListener<Boolean> cl = new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue,
					Boolean newValue) {
				relayout();
			}
		};

		ChangeListener<Boolean> cl2 = new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue,
					Boolean newValue) {
				animate();
			}
		};

		layoutCbx.selectedProperty().addListener(cl);
		localCbx.selectedProperty().addListener(cl);
		parentCbx.selectedProperty().addListener(cl);
		effectGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				relayout();
			}
		});

		layoutAnimateCbx.selectedProperty().addListener(cl2);
		localAnimateCbx.selectedProperty().addListener(cl2);
		parentAnimateCbx.selectedProperty().addListener(cl2);

		double w = 20.0;
		double h = 10.0;

		Rectangle rLayout = new Rectangle(w, h);
		rLayout.setFill(LAYOUT_BOUNDS_RECT_FILL_COLOR);
		rLayout.setStrokeWidth(BOUNDS_STROKE_WIDTH);
		rLayout.setStroke(LAYOUT_BOUNDS_RECT_STROKE_COLOR);

		Rectangle rLocal = new Rectangle(w, h);
		rLocal.setFill(LOCAL_BOUNDS_RECT_FILL_COLOR);
		rLocal.setStrokeWidth(BOUNDS_STROKE_WIDTH);
		rLocal.setStroke(LOCAL_BOUNDS_RECT_STROKE_COLOR);

		Rectangle rParent = new Rectangle(w, h);
		rParent.setFill(PARENT_BOUNDS_RECT_FILL_COLOR);
		rParent.setStrokeWidth(BOUNDS_STROKE_WIDTH);
		rParent.setStroke(PARENT_BOUNDS_RECT_STROKE_COLOR);

		GridPane gp = new GridPane();
		gp.addRow(1, rLayout, new Text("Layout Bounds:"), layoutCbx, layoutAnimateCbx);
		gp.addRow(2, rLocal, new Text("Local Bounds:"), localCbx, localAnimateCbx);
		gp.addRow(3, rParent, new Text("Parent Bounds:"), parentCbx, parentAnimateCbx);

		TitledPane titledPane = new TitledPane("Show Bounds", gp);

		return titledPane;
	}

	private TitledPane getEffectTitledPane() {
		noneEffect.setToggleGroup(effectGroup);
		noneEffect.setSelected(true);

		dropshadowEffect.setToggleGroup(effectGroup);
		reflectionEffect.setToggleGroup(effectGroup);
		glowEffect.setToggleGroup(effectGroup);

		HBox hBox = new HBox();
		hBox.setPadding(new Insets(5, 5, 5, 5));
		hBox.getChildren().addAll(noneEffect, dropshadowEffect, reflectionEffect, glowEffect);

		TitledPane effectsPane = new TitledPane("Effect", hBox);

		return effectsPane;
	}

	private void animate() {
		if (layoutAnimateCbx.isSelected()) {
			LAYOUT_BOUNDS_PATH_CIRCLE.setVisible(true);
			playLayoutBoundsPathTransition();

		} else {
			LAYOUT_BOUNDS_PATH_CIRCLE.setVisible(false);
			LAYOUT_BOUNDS_PATH_TRANSITION.stop();
		}

		if (localAnimateCbx.isSelected()) {
			LOCAL_BOUNDS_PATH_CIRCLE.setVisible(true);
			playLocalBoundsPathTransition();

		} else {
			LOCAL_BOUNDS_PATH_CIRCLE.setVisible(false);
			LOCAL_BOUNDS_PATH_TRANSITION.stop();
		}

		if (parentAnimateCbx.isSelected()) {
			PARENT_BOUNDS_PATH_CIRCLE.setVisible(true);
			playParentBoundsPathTransition();

		} else {
			PARENT_BOUNDS_PATH_CIRCLE.setVisible(false);
			PARENT_BOUNDS_PATH_TRANSITION.stop();
		}
	}

	private void initializeBoundsPathTransition() {
		LOCAL_BOUNDS_PATH_TRANSITION.setDuration(Duration.seconds(2));
		LOCAL_BOUNDS_PATH_TRANSITION.setNode(LOCAL_BOUNDS_PATH_CIRCLE);
		LOCAL_BOUNDS_PATH_TRANSITION.setOrientation(PathTransition.OrientationType.NONE);
		LOCAL_BOUNDS_PATH_TRANSITION.setCycleCount(Timeline.INDEFINITE);

		PARENT_BOUNDS_PATH_TRANSITION.setDuration(Duration.seconds(2));
		PARENT_BOUNDS_PATH_TRANSITION.setNode(PARENT_BOUNDS_PATH_CIRCLE);
		PARENT_BOUNDS_PATH_TRANSITION.setOrientation(PathTransition.OrientationType.NONE);
		PARENT_BOUNDS_PATH_TRANSITION.setCycleCount(Timeline.INDEFINITE);

		LAYOUT_BOUNDS_PATH_TRANSITION.setDuration(Duration.seconds(2));
		LAYOUT_BOUNDS_PATH_TRANSITION.setNode(LAYOUT_BOUNDS_PATH_CIRCLE);
		LAYOUT_BOUNDS_PATH_TRANSITION.setOrientation(PathTransition.OrientationType.NONE);
		LAYOUT_BOUNDS_PATH_TRANSITION.setCycleCount(Timeline.INDEFINITE);
	}

	private void playLayoutBoundsPathTransition() {
		Bounds b = mainRect.getLayoutBounds();
		Path path = new Path();
		path.getElements().add(new MoveTo(b.getMinX(), b.getMinY()));
		path.getElements().add(new LineTo(b.getMaxX(), b.getMinY()));
		path.getElements().add(new LineTo(b.getMaxX(), b.getMaxY()));
		path.getElements().add(new LineTo(b.getMinX(), b.getMaxY()));
		path.getElements().add(new LineTo(b.getMinX(), b.getMinY()));

		LAYOUT_BOUNDS_PATH_TRANSITION.setPath(path);
		LAYOUT_BOUNDS_PATH_TRANSITION.play();
	}

	private void playLocalBoundsPathTransition() {
		Bounds b = mainRect.getBoundsInLocal();
		Path path = new Path();
		path.getElements().add(new MoveTo(b.getMinX(), b.getMinY()));
		// path.getElements().add(new LineTo(b.getMinX(), b.getMinY()));
		path.getElements().add(new LineTo(b.getMaxX(), b.getMinY()));
		path.getElements().add(new LineTo(b.getMaxX(), b.getMaxY()));
		path.getElements().add(new LineTo(b.getMinX(), b.getMaxY()));
		path.getElements().add(new LineTo(b.getMinX(), b.getMinY()));

		LOCAL_BOUNDS_PATH_TRANSITION.setPath(path);
		LOCAL_BOUNDS_PATH_TRANSITION.play();
	}

	private void playParentBoundsPathTransition() {
		Bounds b = mainRect.getBoundsInParent();

		Path path = new Path();
		path.getElements().add(new MoveTo(b.getMinX(), b.getMinY()));
		path.getElements().add(new LineTo(b.getMaxX(), b.getMinY()));
		path.getElements().add(new LineTo(b.getMaxX(), b.getMaxY()));
		path.getElements().add(new LineTo(b.getMinX(), b.getMaxY()));
		path.getElements().add(new LineTo(b.getMinX(), b.getMinY()));

		PARENT_BOUNDS_PATH_TRANSITION.setPath(path);
		PARENT_BOUNDS_PATH_TRANSITION.play();
	}

	private void resetAll() {
		xSlider.setValue(0.0);
		ySlider.setValue(0.0);
		widthSlider.setValue(RECTANGLE_WIDTH);
		heightSlider.setValue(RECTANGLE_HEIGHT);
		translateXSlider.setValue(0);
		translateYSlider.setValue(0);
		rotateSlider.setValue(0);
		scaleXSlider.setValue(1.0);
		scaleYSlider.setValue(1.0);
		shearXSlider.setValue(0);
		shearYSlider.setValue(0);

		strokeSlider.setValue(RECTANGLE_STROKE_WIDTH);
		rectStrokeColorChoiceBox.setValue(RECTANGLE_STROKE_COLOR_STR);

		opacitySlider.setOpacity(RECTANGLE_OPACITY);
		rectFillColorChoiceBox.setValue(RECTANGLE_FILL_COLOR_STR);

		layoutAnimateCbx.setSelected(false);
		localAnimateCbx.setSelected(false);
		parentAnimateCbx.setSelected(false);

		layoutCbx.setSelected(false);
		localCbx.setSelected(false);
		parentCbx.setSelected(false);

		noneEffect.setSelected(true);
	}

	private Effect getEffect() {
		Effect effect = null;
		if (dropshadowEffect.isSelected()) {
			effect = new DropShadow();
		} else if (reflectionEffect.isSelected()) {
			effect = new Reflection();
		} else if (glowEffect.isSelected()) {
			effect = new Glow();
		}

		return effect;
	}

	private void displayResults() {
		Bounds layoutBounds = mainRect.getLayoutBounds();
		Bounds localBounds = mainRect.getBoundsInLocal();
		Bounds parentBounds = mainRect.getBoundsInParent();

		layoutBoundsData.update(layoutBounds);
		localBoundsData.update(localBounds);
		parentBoundsData.update(parentBounds);
	}
}