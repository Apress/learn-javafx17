// DatePickerTest.java
package com.jdojo.control;

import java.time.DayOfWeek;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class DatePickerTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		DatePicker birthDate = new DatePicker();		
		birthDate.setEditable(false);
		
		// Print the new date on standard output
		birthDate.setOnAction(e -> 
			System.out.println("New Date:" + birthDate.getValue()));
		
		String pattern = "MM/dd/yyyy";
		birthDate.setConverter(new LocalDateStringConverter(pattern));
		birthDate.setPromptText(pattern.toLowerCase());
		
		// Create a day cell factory
		Callback<DatePicker, DateCell> dayCellFactory = 
		new Callback<DatePicker, DateCell>() {
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override 
					public void updateItem(LocalDate item, boolean empty) {
						// Must call super
						super.updateItem(item, empty);

						// Disable all future date cells
						if (item.isAfter(LocalDate.now())) {
							this.setDisable(true);
						}

						// Show Weekends in blue color
						DayOfWeek day = DayOfWeek.from(item);
						if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
							this.setTextFill(Color.BLUE);
						}
					}
				};
			}};
		
		// Set the day cell factory
		birthDate.setDayCellFactory(dayCellFactory);
	
		HBox root = new HBox(new Label("Birth Date:"), birthDate); 
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		             "-fx-border-radius: 5;" + 
		             "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using DatePicker Control");
		stage.show();
		stage.sizeToScene();
	}
}
