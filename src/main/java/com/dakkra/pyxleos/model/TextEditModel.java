package com.dakkra.pyxleos.model;

import java.awt.Font;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextEditModel {
	public JInternalFrame textEditFrame;
	public JFileChooser openJFC;
	public JFileChooser saveJFC;
	public String fileURI = null;
	public String fileName;
	public Font textAreaFont = new Font("Arial",Font.PLAIN,16);
	public JTextArea textArea;
	public JScrollPane textAreaPane;
	public JMenuBar menuBar;
	public JMenu fileMenu;
	public JMenuItem fileNew;
	public JMenuItem fileOpen;
	public JMenuItem fileReopen;
	public JMenuItem fileSave;
	public JMenuItem fileSaveAs;
	public JMenuItem fileExit;
	public JMenu optionsMenu;
	public JMenuItem optionsTextSize;
}
