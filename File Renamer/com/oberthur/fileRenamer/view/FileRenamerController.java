package com.oberthur.fileRenamer.view;

import java.io.IOException;

import com.oberthur.certificateGenerator.CertificateGenerator;
import com.oberthur.fsitools.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class FileRenamerController extends Controller
{
	
	public static FileRenamerController controller;
	
	public static Tab initialise() throws ClassNotFoundException, IOException 
	{
		FXMLLoader loader = new FXMLLoader();
		
		loader.setLocation(CertificateGenerator.class.getResource("view/CertificateGeneratorLayout.fxml"));
		
		AnchorPane app = (AnchorPane) loader.load();
		
		controller = loader.getController();
		
		Tab tab = new Tab("File Renamer");
		tab.setContent(app);
		
		return tab;
	}
}
