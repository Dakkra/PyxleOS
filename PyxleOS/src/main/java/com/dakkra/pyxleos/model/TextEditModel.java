package com.dakkra.pyxleos.model;

import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextEditModel {
	public JInternalFrame textEditFrame;
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
