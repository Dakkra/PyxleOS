package com.dakkra.pyxleos;

import com.dakkra.pyxleos.controller.MainController;
import com.dakkra.pyxleos.model.MainModel;
import com.dakkra.pyxleos.view.MainView;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Getting started...");
		
		MainView v = new MainView();
		MainModel m = new MainModel();
		MainController c = new MainController();
		
		//Assign the sub-main classes to point to the mains objects
		v.c = c;
		v.m = m;
		c.m = m;
		c.v = v;
		System.out.println("Welcome to PyxleOS! Version: "+m.version);
		//Start GUI
		System.out.println("Displaying GUI");
		v.createAndShowGUI();
	}

}
