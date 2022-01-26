// BubbleChartTest.java
package com.jdojo.chart;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BubbleChartTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Year");
		
		// Customize the x-axis, so points are scattred uniformly
		xAxis.setAutoRanging(false);
		xAxis.setLowerBound(1900);
		xAxis.setUpperBound(2300);
		xAxis.setTickUnit(50);
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Population (in millions)");
 
		BubbleChart<Number,Number> chart = new BubbleChart<>(xAxis, yAxis);
		chart.setTitle("Population by Year and Country");

		// Get the data for the chart
		ObservableList<XYChart.Series<Number,Number>> chartData = 
			XYChartDataUtil.getCountrySeries();

		// Set the bubble radius
		setBubbleRadius(chartData);

		// Set the data for the chart
		chart.setData(chartData);

		StackPane root = new StackPane(chart);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A Bubble Chart");
		stage.show();
	}
	
	private void setBubbleRadius(ObservableList<XYChart.Series<Number,Number>> chartData) {
		for(XYChart.Series<Number,Number> series: chartData) {
			for(XYChart.Data<Number,Number> data : series.getData()) {
				data.setExtraValue(20); // Bubble radius
			}
		}
	}
}
