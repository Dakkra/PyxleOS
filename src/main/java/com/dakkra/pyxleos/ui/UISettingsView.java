package com.dakkra.pyxleos.ui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import com.dakkra.pyxleos.util.Util;

public class UISettingsView {
	public UISettings uis;
	private MainWindow mw;
	public UISettingsView(MainWindow mw){
		this.mw = mw;
	}
	
	public void makeUI(){
		
		JInternalFrame frame = Util.createIFrame("Appearance");
		
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);

		JPanel controlColorPanel = new JPanel();
		
		controlColorPanel.setLayout(layout);
		
		controlColorPanel.add(new JButton("LOL1"));
		controlColorPanel.add(new JButton("LOL2"));
		controlColorPanel.add(new JButton("LOL3"));
		
		frame.setLayout(layout);
		
		frame.add(controlColorPanel);
		
		mw.addIFrame(frame);
	}
}
