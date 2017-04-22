package com.oberthur.certificateGenerator.view;

import java.io.IOException;

import com.oberthur.certificateGenerator.CertificateGenerator;
import com.oberthur.fsitools.ApplicationController;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class CertificateGeneratorController extends ApplicationController
{
	
	public static CertificateGeneratorController controller;
	
	public static Tab initialise() throws ClassNotFoundException, IOException 
	{
		FXMLLoader loader = new FXMLLoader();
		
		loader.setLocation(CertificateGenerator.class.getResource("view/CertificateGeneratorLayout.fxml"));
		
		AnchorPane app = (AnchorPane) loader.load();
		
		controller = loader.getController();
		
		Tab tab = new Tab("Certificate Generator");
		tab.setContent(app);
		
		return tab;
	}
}
