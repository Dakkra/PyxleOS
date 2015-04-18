package com.dakkra.pyxleos.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

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
		m.mainFrame.setJMenuBar(m.menuBar);
		
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
	
		//tools menu
		m.toolsMenu = new JMenu(" Tools ");
		
		//about menu
		m.aboutMenu = new JMenu(" About ");
		m.aboutGithub = new JMenuItem("Github");
		m.aboutGithub.addActionListener(new AboutGithubListener());
		m.aboutSourceForge = new JMenuItem("SourceForge");
		m.aboutSourceForge.addActionListener(new AboutSourceForgeListener());
		m.aboutWebsite = new JMenuItem("Website");
		m.aboutWebsite.addActionListener(new AboutWebsiteListener());
		m.aboutAbout = new JMenuItem("About "+m.applicationNameVersion);
		m.aboutAbout.addActionListener(new AboutListener());
		m.aboutMenu.add(m.aboutWebsite);
		m.aboutMenu.add(m.aboutGithub);
		m.aboutMenu.add(m.aboutSourceForge);
		m.aboutMenu.add(m.aboutAbout);
		
		//add menus to the main menu bar
		m.menuBar.add(m.fileMenu);
		m.menuBar.add(m.toolsMenu);
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
	private class AboutGithubListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	
	    	URI githubURI = null;
			try {githubURI = new URI("https://github.com/Dakkra/PyxleOS");
			} catch (URISyntaxException e1){e1.printStackTrace();}
	    	
			openURI(githubURI);
	    }
	}
	private class AboutSourceForgeListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	
	    	URI sourceforgeURI = null;
			try {sourceforgeURI = new URI("https://sourceforge.net/projects/pyxleos/");
			} catch (URISyntaxException e1){e1.printStackTrace();}
	    	
			openURI(sourceforgeURI);
	    }
	}
	private class AboutWebsiteListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	
	    	URI websiteURI = null;
			try {websiteURI = new URI("http://pyxleos.sourceforge.net/");
			} catch (URISyntaxException e1){e1.printStackTrace();}
	    	
			openURI(websiteURI);
	    }
	}
    private static void openURI(URI uri) {
        if (Desktop.isDesktopSupported()) {
          try {Desktop.getDesktop().browse(uri);
          } catch (IOException e) {}} else {}

    }
}
