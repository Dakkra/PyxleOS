package com.dakkra.pyxleos.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.dakkra.pyxleos.controller.MainController;
import com.dakkra.pyxleos.model.MainModel;

public class MainView {
	public MainController c;
	public MainModel m;
	
	public static final Color BGCOLOR = new Color(17, 17, 17);
	public static final Color PANELCOLOR = new Color(75, 75, 75);
	public static final Color SCBLUE = new Color(95, 115, 133);
	
	public void createAndShowGUI(){
		//Use nimbus
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
		
		applicationMainMenu();
		
		m.mainFrame = new JFrame(m.applicationNameVersion);
		m.mainFrame.setResizable(true);
		m.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.mainFrame.setMinimumSize(new Dimension(800,600));
		m.mainFrame.setLocationRelativeTo(null);
		m.mainFrame.setJMenuBar(m.menuBar);//TODO make menu bar
		
		// Icon code
		Image icon;
		InputStream input = getClass().getResourceAsStream(
				"/Icon.png");
		try {
			icon = ImageIO.read(input);
			m.mainFrame.setIconImage(icon);
		} catch (IOException e) {
			// Intentionally ignore exception (because it should never happen)
		}
		
		JLabel contentDemo = new JLabel("Early Alpha. Nothing has been added yet");
		
		m.mainFrame.add(contentDemo);
		
		m.mainFrame.setVisible(true);
		
	}
	private void applicationMainMenu(){
		m.menuBar = new JMenuBar();

		//file menu
		m.fileMenu = new JMenu(" File ");
		m.fileNew = new JMenuItem("New Project");
		m.fileOpen = new JMenuItem("Open");
		m.fileSave = new JMenuItem("Save");
		m.fileQuit = new JMenuItem("Quit "+m.applicationName);
		m.fileQuit.addActionListener(new CloseListener());
		m.fileMenu.add(m.fileNew);
		m.fileMenu.add(m.fileOpen);
		m.fileMenu.add(m.fileSave);
		m.fileMenu.add(m.fileQuit);
	
		//add items to about menu
		m.aboutMenu = new JMenu(" About ");
		m.aboutGithub = new JMenuItem("Github");
		m.aboutAbout = new JMenuItem("About "+m.applicationNameVersion);
		m.aboutAbout.addActionListener(new AboutListener());
		m.aboutMenu.add(m.aboutGithub);
		m.aboutMenu.add(m.aboutAbout);
		
		//add menus to the main menu bar
		m.menuBar.add(m.fileMenu);
		m.menuBar.add(m.aboutMenu);
	}
	
	private void customizeNimbus(){
		//General Changes
//		UIManager.put("control", PANELCOLOR);
		
	}
	private class CloseListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	System.out.println("File > Quit: Exitting Application");
	        System.exit(0);
	    }
	}
	private class AboutListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	AboutView aboutView = new AboutView();
	    	aboutView.showAboutView();
	    }
	}

}
