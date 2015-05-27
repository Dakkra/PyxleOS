package com.dakkra.pyxleos.ui;

import javax.swing.JInternalFrame;

import com.dakkra.pyxleos.util.Util;

public class UISettingsView {
	public UISettingsView(){
		
	}
	
	public void makeUI(MainWindow mw){
		
		JInternalFrame frame = Util.createIFrame("Appearance");
		
		mw.addIFrame(frame);
	}
}
