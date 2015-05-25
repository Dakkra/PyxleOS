package com.dakkra.pyxleos.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainWindow {
	public MainWindow(){
//		Constructor
	}
	
	public void cnsUI(){
//		Create and show UI
//		Use Nimbus
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		        	customizeNimbus();
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    System.out.println("Nimbus look and feel not found (MainView.java)");
		    e.printStackTrace();
		}
//		Initialize Main Frame
		JFrame mFrame = new JFrame("PyxleOS::E");
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setMinimumSize(new Dimension(950,600));
		mFrame.setLocationRelativeTo(null);
		
		mFrame.setVisible(true);
		
	}
	private void customizeNimbus(){
		Color bgColor = new Color(43, 57, 71);
		Color baseColor = new Color(43, 57, 71);
		Color baseRedColor = new Color(60, 57, 71);

		// General Changes
		UIManager.put("control", bgColor);
		UIManager.put("nimbusBase", baseColor);
		UIManager.put("nimbusOrange", baseColor);
		UIManager.put("nimbusGreen", baseColor);
		UIManager.put("nimbusRed", baseRedColor);
		UIManager.put("nimbusLightBackground", baseColor);
		UIManager.put("text", Color.WHITE);
		UIManager.put("nimbusDisabledText", Color.WHITE);
	}
}
