// BuilderTest.java
package com.jdojo.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import com.jdojo.util.ResourceUtil;

import javafx.fxml.FXMLLoader; 
import javafx.util.BuilderFactory;

public class BuilderTest {	
	public static void main(String[] args) throws IOException {	   
	    // Use the Builder with property getter and setter
	    loadItems(new ItemBuilderFactory());
		
	    // Use the Builder with Map
		loadItems(new ItemBuilderFactoryMap());	    
	}
	
	public static void loadItems(BuilderFactory builderFactory) throws IOException {
		URL fxmlUrl = ResourceUtil.getResourceURL("fxml/items.fxml");
		
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(fxmlUrl);
	    loader.setBuilderFactory(builderFactory);
	    ArrayList items = loader.<ArrayList>load();
	    System.out.println("List:" + items);  
	}
}
