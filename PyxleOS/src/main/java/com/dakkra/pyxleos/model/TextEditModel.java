package com.dakkra.pyxleos.model;

import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextEditModel {
	public JInternalFrame textEditFrame;
	public String fileURI = null;
	public Font textAreaFont = new Font("Arial",Font.PLAIN,16);
	public JTextArea textArea;
	public JScrollPane textAreaPane;
	public JMenuBar menuBar;
	public JMenu fileMenu;
	public JMenuItem fileOpen;
	public JMenuItem fileSave;
	public JMenuItem fileExit;
	public JMenu optionsMenu;
	public JMenuItem optionsTextSize;
}
