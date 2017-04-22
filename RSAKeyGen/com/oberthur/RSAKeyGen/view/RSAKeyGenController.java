package com.oberthur.RSAKeyGen.view;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.oberthur.RSAKeyGen.RSAKeyGen;
import com.oberthur.RSAKeyGen.model.RSAKeyWrapper;
import com.oberthur.fsitools.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RSAKeyGenController extends Controller
{
	@FXML
	private TextField keySize;
	@FXML
	private TextArea modulus;
	@FXML
	private TextField publicExponent;
	@FXML
	private TextArea privateExponent;
	@FXML
	private TextArea primeP;
	@FXML
	private TextArea primeQ;
	@FXML
	private TextArea primeExponentP;
	@FXML
	private TextArea primeExponentQ;
	@FXML
	private TextArea crtCoefficient;
	
	public static RSAKeyGenController controller;
	
	public static final Tab initialise() throws IOException 
	{
		FXMLLoader loader = new FXMLLoader();
		
		loader.setLocation(RSAKeyGen.class.getResource("view/RSAKeyGenLayout.fxml"));
		
		AnchorPane app = (AnchorPane) loader.load();
		
		controller = loader.getController();
		
		Tab tab = new Tab("RSA Key Generator");
		tab.setContent(app);
		
		return tab;
	}
	
	@FXML
	private void generateKey()
	{
		String[] args = new String[1];
		if(keySize.getLength() > 0)
			args[0] = keySize.getText();
		
		try 
		{
			RSAKeyGen.main(args);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(getApplication().primaryStage);
			alert.setTitle("Error");
			alert.setHeaderText(e.getClass().getSimpleName());
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} 
	}
	
	public void setKeyValues(BigInteger modulus, BigInteger publicExponent, BigInteger privateExponent, 
			BigInteger primeP, BigInteger primeQ, BigInteger primeExponentP, BigInteger primeExponentQ, BigInteger crtCoefficient)
	{
		if(modulus != null && publicExponent != null && privateExponent != null && primeP != null && primeQ != null
				&& primeExponentP != null && primeExponentQ != null && crtCoefficient != null)
		{
			this.modulus.setText(bigIntegerToString(modulus));
			this.publicExponent.setText(bigIntegerToString(publicExponent));
			this.privateExponent.setText(bigIntegerToString(privateExponent));
			this.primeP.setText(bigIntegerToString(primeP));
			this.primeQ.setText(bigIntegerToString(primeQ));
			this.primeExponentP.setText(bigIntegerToString(primeExponentP));
			this.primeExponentQ.setText(bigIntegerToString(primeExponentQ));
			this.crtCoefficient.setText(bigIntegerToString(crtCoefficient));
		}
	}
	
	private static String bigIntegerToString(BigInteger number)
	{
		String sb = "";
		byte[] num = number.toByteArray();
		for(int i = 0; i < num.length; i++)
			sb += (String.format("%02X ", num[i]));
		return sb;
	}
	
	@FXML
	private void handleSaveAs()
	{
		if(modulus.getText().equalsIgnoreCase(""))
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(getApplication().primaryStage);
			alert.setTitle("Error");
			alert.setHeaderText("RSA Key is empty");
			alert.setContentText("Please generate an RSA key first before save the key!");
			alert.showAndWait();
			return;
		}
		
		FileChooser fileChooser = new FileChooser();
		
		// Set extension filter
		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML Files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extensionFilter);
		
		// Show save file dialog
		if(getApplication().getFilePath() != null)
		{
			fileChooser.setInitialDirectory(getApplication().getFilePath().getParentFile());
		}
		fileChooser.setInitialFileName("RSA");
		File file = fileChooser.showSaveDialog(getApplication().primaryStage);
		
		if(file != null)
		{
			if(!file.getPath().endsWith(".xml"))
			{
				file = new File(file.getPath() + ".xml");
			}
			saveRSAKeyToXML(file);
		}
	}
	
	private void saveRSAKeyToXML(File file)
	{
		try {
			JAXBContext context = JAXBContext.newInstance(RSAKeyWrapper.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			// Wrapping RSA Key data
			RSAKeyWrapper wrapper = new RSAKeyWrapper();
			wrapper.setKey(this.modulus.getText(), this.privateExponent.getText(), this.publicExponent.getText(), 
					this.primeP.getText(), this.primeQ.getText(), this.primeExponentP.getText(), this.primeExponentQ.getText(), 
					this.crtCoefficient.getText());
			
			// Marshalling and saving XML to the file
			marshaller.marshal(wrapper, file);
			
			// Save last file path
			getApplication().setFilePath(file);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(getApplication().primaryStage);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save key");
			alert.setContentText(e.getClass().getSimpleName() + ": " +e.getMessage());
			alert.showAndWait();
		}
	}
	
}
