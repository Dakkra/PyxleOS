package com.dakkra.pyxleos;

import com.dakkra.pyxleos.ui.MainWindow;

public class PyxleOS {
	public static void main(String[]args){
		System.out.println("Initializing PyxleOS::E");
		
		MainWindow mw = new MainWindow();
		mw.cnsUI();
		
		System.out.println("Ready!");
	}
}
