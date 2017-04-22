package com.oberthur.fsitools.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MainLayoutController 
{
	@FXML
	private void handleAbout()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("FSI Tools");
		alert.setHeaderText("About");
		alert.setContentText("Author: Raja Parsaulian Batubara (r.batubara@oberthur.com)");
		alert.showAndWait();
	}
	
	@FXML
	private void handleExit()
	{
		System.exit(0);
	}
}
