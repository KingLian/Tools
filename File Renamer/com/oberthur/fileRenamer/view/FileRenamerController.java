package com.oberthur.fileRenamer.view;

import java.io.File;
import java.io.IOException;

import com.oberthur.fileRenamer.FileRenamer;
import com.oberthur.fsitools.Controller;
import com.oberthur.fsitools.Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;

public class FileRenamerController extends Controller
{
	@FXML
	private TextField folder;
	
	@FXML
	private TextField fileToSearch;
	
	@FXML
	private TextField newFileName;
	
	@FXML
	private CheckBox isIncludeSubFolder;
	
	public static FileRenamerController controller;
	
	public static Tab initialise() throws ClassNotFoundException, IOException 
	{
		FXMLLoader loader = new FXMLLoader();
		
		loader.setLocation(FileRenamer.class.getResource("view/FileRenamerLayout.fxml"));
		
		AnchorPane app = (AnchorPane) loader.load();
		
		controller = loader.getController();
		
		Tab tab = new Tab("File Renamer");
		tab.setContent(app);
		
		return tab;
	}
	
	@FXML
	private void handleChooseFolder()
	{
		DirectoryChooser chooser = new DirectoryChooser();
		
		chooser.setTitle("File Renamer");
		
		File defaultDirectory = Main.getFilePath().getParentFile();
		if(defaultDirectory == null)
			defaultDirectory = new File("C:");
		
		chooser.setInitialDirectory(defaultDirectory);
		
		File selectedDirectory = chooser.showDialog(getApplication().getPrimaryStage());
		
		if(selectedDirectory != null)
		{
			folder.setText(selectedDirectory.getPath());
			Main.setFilePath(selectedDirectory);
		}
	}
	
	@FXML
	private void handleRename()
	{
		if(folder.getText().equalsIgnoreCase("") || fileToSearch.getText().equalsIgnoreCase("") || newFileName.getText().equalsIgnoreCase(""))
		{
			System.err.println("Please fill all data needed!");
			return;
		}
		
		String[] args = new String[4];
		
		args[0] = folder.getText();
		args[1] = fileToSearch.getText();
		args[2] = newFileName.getText();
		args[3] = isIncludeSubFolder.isSelected() ? "Yes" : "No";
		
		FileRenamer.main(args);
	}
}
