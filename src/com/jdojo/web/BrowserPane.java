// BrowserPane.java
package com.jdojo.web;

import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.Window;

public class BrowserPane extends BorderPane {
	// TODO: book text
	private static String DEFAULT_HOME_PAGE = "https://www.qwant.com";
	private WebView webView;
	
	public BrowserPane(Window ownerWindow) {
		this(null, ownerWindow);
	}
	
	public BrowserPane(String homePageUrl, Window ownerWindow) {
		this(homePageUrl, true, true, true, ownerWindow);
	}
	
	public BrowserPane(String homePageUrl, 
	                   boolean enableNavigationBar, 
	                   boolean enableStatusBar, 
	                   boolean enableJSHandlers, 
	                   Window ownerWindow) {

		// Create the WebView
		webView = new WebView();
		this.setCenter(webView);

		if (homePageUrl == null) {
			homePageUrl = DEFAULT_HOME_PAGE;
		}

		if (enableNavigationBar) {
			this.addNavigationBar(homePageUrl);
		}

		if (enableStatusBar) {
			this.addStatusBar();
		}

		if (enableJSHandlers) {
			this.addJSHandlers(ownerWindow);
		}
	}

	private void addNavigationBar(String homePageUrl) {
		MenuButton options = new WebOptionsMenu(webView);
		BrowserHistory historyComponent = new BrowserHistory(webView);
		NavigationBar navBar = new NavigationBar(webView, homePageUrl, true);
		navBar.getChildren().addAll(options, historyComponent);
		this.setTop(navBar);
	}
	
	private void addStatusBar() {
		Label statusLbl = new Label();

		// Configure the status bar
		statusLbl.setStyle("-fx-background-color: lightgray;");
		statusLbl.prefWidthProperty().bind(webView.widthProperty());

		// If the Worker object reports a message, display it in the status bar
		webView.getEngine().getLoadWorker().messageProperty().addListener(
			(prop, oldMsg,  newMsg) -> statusLbl.setText(newMsg));
		
		// Update the status bar when window.status proeprty changes
		webView.getEngine().setOnStatusChanged(
				e -> statusLbl.setText(e.getData()));
						
		this.setBottom(statusLbl);
	}
	
	
	private void addJSHandlers(Window ownerWindow) {
		webView.getEngine().setPromptHandler(JSHandlers.getPromptHandler());
		webView.getEngine().setCreatePopupHandler(JSHandlers.getPopupHandler());
		webView.getEngine().setOnAlert(JSHandlers::alertHandler);     
		webView.getEngine().setConfirmHandler(JSHandlers.getConfirmHandler());	
		if (ownerWindow instanceof Stage) {
			Stage stage = (Stage) ownerWindow;
	
			// Sync the title of the stage with the title of the loaded web page
			webView.getEngine().titleProperty().addListener(
				(prop, oldTitle, newTitle) -> stage.setTitle(newTitle));
		}
	}
	
	public WebView getWebView() {
		return webView;
	}
}
