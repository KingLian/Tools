package com.oberthur.fsitools;

import com.oberthur.fsitools.view.MainLayoutController;

public abstract class Controller
{
	// Reference to the main application
	private static Main application;
	
	// Reference to the main controller
	private static MainLayoutController controller;
	
	public static final void setApplication(Main application) 
	{
		Controller.application = application;
	}
	
	public static final Main getApplication() 
	{
		return application;
	}

	public static MainLayoutController getController() 
	{
		return controller;
	}

	public static void setController(MainLayoutController controller) 
	{
		Controller.controller = controller;
	}
	
}
