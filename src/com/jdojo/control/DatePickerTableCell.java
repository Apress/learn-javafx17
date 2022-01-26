// DatePickerTableCell.java
package com.jdojo.control;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.util.StringConverter;

@SuppressWarnings("unchecked")
public class DatePickerTableCell<S, T> extends TableCell<S, java.time.LocalDate> {
	private DatePicker datePicker;
	private StringConverter converter = null;
	private boolean datePickerEditable = true;
	
	public DatePickerTableCell() {
		this.converter = new LocalDateStringConverter();
	}
	
	public DatePickerTableCell(boolean datePickerEditable) {
	    this.converter = new LocalDateStringConverter();
		this.datePickerEditable = datePickerEditable;
	}

	public DatePickerTableCell(StringConverter<java.time.LocalDate> converter) {
		this.converter = converter;
	}

	public DatePickerTableCell(StringConverter<java.time.LocalDate> converter, 
	                           boolean datePickerEditable) {
		this.converter = converter;
		this.datePickerEditable = datePickerEditable;
	}
	
	@Override
	public void startEdit() {
		// Make sure the cell is editable
		if (!isEditable() || 
		    !getTableView().isEditable() || 
		    !getTableColumn().isEditable()) {
			return;
		}
		
		// Let the ancestor do the plumbing job
		super.startEdit();
		
		// Create a DatePicker, if needed, and set it as the graphic for the cell
		if (datePicker == null) {
			this.createDatePicker();
		}
		
		this.setGraphic(datePicker);
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();
		this.setText(converter.toString(this.getItem()));
		this.setGraphic(null);
	}

	@Override
	public void updateItem(java.time.LocalDate item, boolean empty) {
		super.updateItem(item, empty);
		
		// Take actions based on whether the cell is being edited or not
		if (empty) {
			this.setText(null);
			this.setGraphic(null);
		}
		else {
			if (this.isEditing()) {
				if (datePicker != null) {
					datePicker.setValue((java.time.LocalDate)item);
				}
				this.setText(null);
				this.setGraphic(datePicker);
			}
			else {
				this.setText(converter.toString(item));
				this.setGraphic(null);
			}
		}
	}

	private void createDatePicker() {
		datePicker = new DatePicker();
		datePicker.setConverter(converter);
		
		// Set the current value in the cell to the DatePicker
		datePicker.setValue((java.time.LocalDate)this.getItem());
		
		// Configure the DatePicker properties
		datePicker.setPrefWidth(this.getWidth() - this.getGraphicTextGap() * 2);
		datePicker.setEditable(this.datePickerEditable);
		
		// Commit the new value when the user selects or enters a date
		datePicker.valueProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue prop, 
								Object oldValue, 
								Object newValue) {
				if (DatePickerTableCell.this.isEditing()) {
					DatePickerTableCell.this.commitEdit((java.time.LocalDate)newValue);
				}
			}
		});
	}
	
	public static <S> Callback<TableColumn<S, java.time.LocalDate>, 
	                           TableCell<S, java.time.LocalDate>> forTableColumn() {
		return forTableColumn(true);
	}
	
	public static <S> Callback<TableColumn<S, java.time.LocalDate>, 
	                           TableCell<S, java.time.LocalDate>> 
				forTableColumn(boolean datePickerEditable) {
		return (col -> new DatePickerTableCell<>(datePickerEditable));
	}
	
	public static <S> Callback<TableColumn<S, java.time.LocalDate>, TableCell<S, java.time.LocalDate>> forTableColumn(StringConverter<java.time.LocalDate> converter) {
		return forTableColumn(converter, true);
	}
	
	public static <S> Callback<TableColumn<S, java.time.LocalDate>, TableCell<S, java.time.LocalDate>> forTableColumn(StringConverter<java.time.LocalDate> converter, boolean datePickerEditable) {
		return (col -> new DatePickerTableCell<>(converter, datePickerEditable));
	}
}
