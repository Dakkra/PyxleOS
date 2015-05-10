package com.dakkra.pyxleos.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.dakkra.pyxleos.model.MainModel;

public class AboutView {
	public MainModel m;
	public void showAboutView(MainModel m){
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
		aboutViewFrame.setMinimumSize(new Dimension(390,170));
		aboutViewFrame.setSize(390, 170);
		aboutViewFrame.setLocationRelativeTo(m.mainFrame);
		aboutViewFrame.setResizable(true);
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
		
		JTextArea aboutText = new JTextArea(m.applicationNameVersion+"\n(c) By Chris Soderquist\n http://pyxleos.sourceforge.net \n \nPyxleOS is under a GNU GENERAL PUBLIC LICENSE V2.0\n https://www.gnu.org/licenses/gpl-2.0.html \n\nPyxle(pixel) OS(open source)\n\nCheck out http://dakkra.com");
		aboutText.setEditable(false);
		aboutText.setLineWrap(true);
		JScrollPane aboutTextPane = new JScrollPane(aboutText);
		
		aboutViewFrame.add(aboutTextPane);
		
		aboutViewFrame.setVisible(true);
		
	}
	
	private void customizeNimbus(){
		//Colors
		Color bgColor = new Color(43,57,71);
		Color baseColor = new Color(43,57,71);
		//General Changes
		UIManager.put("control", bgColor);
		UIManager.put("nimbusBase", baseColor);
	}
}
