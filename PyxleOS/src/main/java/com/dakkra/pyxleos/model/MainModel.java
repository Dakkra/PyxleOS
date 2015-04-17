package com.dakkra.pyxleos.model;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.dakkra.pyxleos.Main;

public class MainModel {
	public String applicationNameVersion = "PyxleOS "+Main.version;
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
}
