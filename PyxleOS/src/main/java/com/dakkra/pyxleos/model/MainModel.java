package com.dakkra.pyxleos.model;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MainModel {
	public final String version = "0.1.1";
	public String applicationNameVersion = "PyxleOS "+version;
	public String applicationName = "PyxleOS";
	//main frame
	public JFrame mainFrame;
	//main menu bar
	public JMenuBar menuBar;
	//file menu
	public JMenu fileMenu;
	//file menu items
	public JMenuItem fileQuit;
	public JMenuItem fileNew;
	public JMenuItem fileOpen;
	public JMenuItem fileSave;
	//about menu
	public JMenu aboutMenu;
	//about menu item
	public JMenuItem aboutAbout;
	public JMenuItem aboutGithub;
}
