package com.dakkra.pyxleos.model;

import java.awt.image.BufferedImage;

import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.dakkra.pyxleos.specialcomponents.CanvasPad;

public class CanvasModel {
	public JInternalFrame canvasFrame;

	public JMenuBar menuBar;
	// file menu
	public JMenu fileMenu;
	public JMenuItem fileNew;
	public JMenuItem fileOpen;
	public JMenuItem fileSave;
	public JMenuItem fileExit;

	// canvas panel
	public JPanel containerPanel;
	//canvas scrollpane
	public JScrollPane canvasPane;
	// canvas canvas
	public CanvasPad canvasPad;
	// canvas image
	public BufferedImage canvasImage = new BufferedImage(200, 200,
			BufferedImage.TYPE_INT_ARGB);

}
