package com.oberthur.fsitools;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import com.oberthur.RSAKeyGen.view.RSAKeyGenController;
import com.oberthur.certificateGenerator.view.CertificateGeneratorController;
import com.oberthur.fileRenamer.view.FileRenamerController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application 
{
	public Stage primaryStage;
	private BorderPane mainLayout;
	
	@Override
	public void start(Stage primaryStage) 
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("FSI Tools");
		
		initMainLayout();
		
		showApplication();
	}
	
	public void initMainLayout()
	{
		try {
			// Load Main Layout from fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainLayout.fxml"));
			mainLayout = (BorderPane) loader.load();
			
			Controller.setController(loader.getController());
			
			// Show the scene containing the main layout
			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void showApplication()
	{
		try {
			Controller.setApplication(this);
			
			// Create application tab
			Tab[] tabs = new Tab[3];
			
			// Initialise all applications
			tabs[0] = RSAKeyGenController.initialise();
			tabs[1] = CertificateGeneratorController.initialise();
			tabs[2] = FileRenamerController.initialise();
			
			// Set the layout into centre on the main layout
			TabPane tabPane = new TabPane(tabs);
			tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
			mainLayout.setCenter(tabPane);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(primaryStage);
			alert.setTitle("Error");
			alert.setHeaderText(e.getClass().getSimpleName());
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}
	
	public Stage getPrimaryStage()
	{
		return primaryStage;
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	
	private static final String lastFolder = "lastFolder";
	
	public File getFilePath()
	{
		Preferences preferences = Preferences.userNodeForPackage(Main.class);
		String filePath = preferences.get(lastFolder, null);
		if(filePath == null)
		{
			return null;
		}
		return new File(filePath);
	}
	
	public void setFilePath(File file)
	{
		Preferences preferences = Preferences.userNodeForPackage(Main.class);
		if(file == null)
		{
			preferences.remove(lastFolder);
		}
		preferences.put(lastFolder, file.getPath());
	}
	
}
