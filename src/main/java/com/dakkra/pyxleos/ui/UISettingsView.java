package com.dakkra.pyxleos.ui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import com.dakkra.pyxleos.util.Util;

public class UISettingsView {
	public UISettings uis;
	private MainWindow mw;
	public UISettingsView(MainWindow mw){
		this.mw = mw;
	}
	
	public void makeUI(){
		
		JInternalFrame frame = Util.createIFrame("Appearance");
		
		JLabel controlColorLabel = new JLabel("Control Color: ");
		
		frame.add(controlColorLabel);
		
		mw.addIFrame(frame);
	}
}
