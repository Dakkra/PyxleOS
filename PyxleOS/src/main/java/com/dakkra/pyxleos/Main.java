package com.dakkra.pyxleos;

import com.dakkra.pyxleos.controller.MainController;
import com.dakkra.pyxleos.model.MainModel;
import com.dakkra.pyxleos.view.MainView;

public class Main {
	public static String version = "0.1.0";

	public static void main(String[] args) {
		
		System.out.println("Welcome to PyxleOS! Version: "+version);
		System.out.println("Setting up...");
		
		MainView v = new MainView();
		MainModel m = new MainModel();
		MainController c = new MainController();
		
		//Assign the sub-main classes to point to the mains objects
		v.c = c;
		v.m = m;
		c.m = m;
		c.v = v;
		
		//Start GUI
		System.out.println("Displaying GUI");
		v.createAndShowGUI();
	}

}
