package com.dakkra.pyxleos.model;

import java.awt.image.BufferedImage;

import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class CanvasModel {
	public JInternalFrame canvasFrame;
	
	public JMenuBar menuBar;
	//file menu
	public JMenu fileMenu;
	public JMenuItem fileNew;
	public JMenuItem fileOpen;
	public JMenuItem fileSave;
	public JMenuItem fileExit;
	
	//canvas panel
	public JPanel canvasPanel;
	//canvas image
	public BufferedImage canvasImage;
	
}
