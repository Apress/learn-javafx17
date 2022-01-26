// TitledPaneTest.java
package com.jdojo.control;

import com.jdojo.util.ResourceUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TitledPaneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TextField firstNameFld = new TextField();
		firstNameFld.setPrefColumnCount(8);

		TextField lastNameFld = new TextField();
		lastNameFld.setPrefColumnCount(8);

		DatePicker dob = new DatePicker();
		dob.setPrefWidth(150);

		GridPane grid = new GridPane();
		grid.addRow(0, new Label("First Name:"), firstNameFld);
		grid.addRow(1, new Label("Last Name:"), lastNameFld);
		grid.addRow(2, new Label("DOB:"), dob);

		TitledPane infoPane = new TitledPane();
		infoPane.setText("Personal Info");
		infoPane.setContent(grid);

		String imageStr = ResourceUtil.getResourceURLStr("picture/privacy_icon.png");		
		Image img = new Image(imageStr);
		ImageView imgView = new ImageView(img);
		infoPane.setGraphic(imgView);
		
		HBox root = new HBox(infoPane);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using TitledPane Controls");
		stage.show();		
	}
}
