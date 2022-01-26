// FlowPaneRowColAlignment.java
package com.jdojo.container;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import static javafx.geometry.Orientation.HORIZONTAL;
import static javafx.geometry.Orientation.VERTICAL;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FlowPaneRowColAlignment extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		FlowPane fp1 = createFlowPane(HORIZONTAL, VPos.TOP, HPos.LEFT);
		FlowPane fp2 = createFlowPane(HORIZONTAL, VPos.CENTER, HPos.LEFT);
		FlowPane fp3 = createFlowPane(VERTICAL, VPos.CENTER, HPos.RIGHT);

		HBox root = new HBox(fp1, fp2, fp3);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("FlowPane Row and Column Alignment");
		stage.show();
	}

	private FlowPane createFlowPane(Orientation orientation, 
	                                VPos rowAlign,
	                                HPos colAlign) {
		// Show the row or column alignment value in a Text
		Text t = new Text();
		if (orientation == Orientation.HORIZONTAL) {
			t.setText(rowAlign.toString());
		} else {
			t.setText(colAlign.toString());
		}
	
		// Show the orientation of the FlowPane in a TextArea
		TextArea ta = new TextArea(orientation.toString());
		ta.setPrefColumnCount(5);
		ta.setPrefRowCount(3);	    
	
		FlowPane fp = new FlowPane(orientation, 5, 5);
		fp.setRowValignment(rowAlign);
		fp.setColumnHalignment(colAlign);
		fp.setPrefSize(175, 130);	    
		fp.getChildren().addAll(t, ta);
		fp.setStyle("-fx-padding: 10;" + 
	                    "-fx-border-style: solid inside;" + 
	                    "-fx-border-width: 2;" +
	                    "-fx-border-insets: 5;" + 
	                    "-fx-border-radius: 5;" + 
	                    "-fx-border-color: blue;");

		return fp;
	}
}
