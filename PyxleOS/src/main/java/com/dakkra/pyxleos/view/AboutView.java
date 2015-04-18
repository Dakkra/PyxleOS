package com.dakkra.pyxleos.view;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class AboutView {
	public void showAboutView(){
		//use ninbus
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    if ("Nimbus".equals(info.getName())) {
		        try {
		    		//Customize Nimbus
		    		customizeNimbus();
					UIManager.setLookAndFeel(info.getClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
		        break;
		    }
		}
		
		JFrame aboutViewFrame = new JFrame("About PyxleOS");
		aboutViewFrame.setSize(350, 170);
		aboutViewFrame.setLocationRelativeTo(null);
		aboutViewFrame.setResizable(false);
		aboutViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Icon code
		Image icon;
		InputStream input = getClass().getResourceAsStream(
				"/Icon.png");
		try {
			icon = ImageIO.read(input);
			aboutViewFrame.setIconImage(icon);
		} catch (IOException e) {
			// Intentionally ignore exception (because it should never happen)
		}
		
		JTextArea about = new JTextArea("(c) By Chris Soderquist\n http://pyxleos.sourceforge.net \n \nPyxleOS is under a GNU GENERAL PUBLIC LICENSE V2.0\n https://www.gnu.org/licenses/gpl-2.0.html \n\n Check out http://dakkra.com \n Name: Pyxle(pixel) OS(open source)");
		about.setEditable(false);
		about.setLineWrap(true);
		
		aboutViewFrame.add(about);
		
		aboutViewFrame.setVisible(true);
		
	}
	
	private void customizeNimbus(){
		//General Changes
//		UIManager.put("control", PANELCOLOR);
		
	}
}
