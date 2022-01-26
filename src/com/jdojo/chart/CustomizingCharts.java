// CustomizingCharts.java
package com.jdojo.chart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CustomizingCharts extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Set caspian CSS to get alternate column fills 
		// until modena CSS is fixed
		Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);

		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Year");

		// CUstomize the x-axis, so points are scattred uniformly
		xAxis.setAutoRanging(false);
		xAxis.setLowerBound(1900);
		xAxis.setUpperBound(2300);
		xAxis.setTickUnit(50);

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Population");

		// Use a formatter for tick labels on y-axis to append
		// M (for millioms) to the population value
		yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, null, "M"));

		LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
		chart.setTitle("Population by Year and Country");

		// Set the data for the chart
		chart.setData(XYChartDataUtil.getCountrySeries());

		// Show alternate column fills
		chart.setAlternativeColumnFillVisible(true);
		chart.setAlternativeRowFillVisible(false);

		// Hide grid lines
		chart.setHorizontalGridLinesVisible(false);
		chart.setVerticalGridLinesVisible(false);

		StackPane root = new StackPane(chart);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Customizing Tick Labels and Chart Plot");
		stage.show();
	}
}
