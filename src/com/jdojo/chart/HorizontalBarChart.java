// HorizontalBarChart.java
package com.jdojo.chart;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HorizontalBarChart extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Population (in millions)");

		CategoryAxis yAxis = new CategoryAxis();
		yAxis.setLabel("Country");

		// Use a category axis as the y-axis for a horizontal bar chart
		BarChart<Number, String> chart = new BarChart<>(xAxis, yAxis);		
		chart.setTitle("Population by Country and Year");

		// Set the data for the chart
		ObservableList<XYChart.Series<Number,String>> chartData = 
				this.getChartData(XYChartDataUtil.getYearSeries());
		chart.setData(chartData);

		StackPane root = new StackPane(chart);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A Horizontal Bar Chart");
		stage.show();
	}

	private ObservableList<XYChart.Series<Number,String>> getChartData(
			ObservableList<XYChart.Series<String,Number>> oldData) {
		ObservableList<XYChart.Series<Number, String>> newData = 
			FXCollections.observableArrayList();

		// Read (String, Number) from old data and convert it into 
		// (Nubmer, String) in new data
		for(XYChart.Series<String, Number> oldSeries: oldData) {
			XYChart.Series<Number, String> newSeries = new XYChart.Series<>();
			newSeries.setName(oldSeries.getName());

			for(XYChart.Data<String, Number> oldItem: oldSeries.getData()) {
				XYChart.Data<Number, String> newItem = 
					new XYChart.Data<>(oldItem.getYValue(), 
					                                 oldItem.getXValue());
				newSeries.getData().add(newItem);
			}
			newData.add(newSeries);			
		}
		return newData;
	}
}
