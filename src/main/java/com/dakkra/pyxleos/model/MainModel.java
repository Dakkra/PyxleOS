package com.dakkra.pyxleos.model;

import java.awt.Color;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import com.dakkra.pyxleos.specialcomponents.ToolbarColorBox;

public class MainModel {
	public final String version = "0.2.2";
	public String applicationName = "PyxleOS";
	public String applicationNameVersion = applicationName + " " + version;
	// main frame
	public JFrame mainFrame;
	// main menu bar
	public JMenuBar menuBar;
	// main scroll pane
	public JScrollPane mainScrollPane;
	// main jdpane
	public JDesktopPane mainJDPane;

	// main toolbar
	public JToolBar toolBar;
	// tool bar button
	public ToolbarColorBox fgColorBox;
	public Color fgColor;

	// internal frames
	public JInternalFrame textEditor;

	// file menu
	public JMenu fileMenu;
	// file menu items
	public JMenuItem fileQuit;
	public JMenuItem fileNew;
	public JMenuItem fileOpen;
	public JMenuItem fileSave;

	// tools menu
	public JMenu toolsMenu;
	// tools menu items
	public JMenuItem toolTextEditor;
	public JMenuItem toolCanvas;
	public JMenuItem toolCopicPalette;

	// about menu
	public JMenu aboutMenu;
	// about menu item
	public JMenuItem aboutAbout;
	public JMenuItem aboutTrello;
	public JMenuItem aboutGithub;
	public JMenuItem aboutSourceForge;
	public JMenuItem aboutWebsite;

}
