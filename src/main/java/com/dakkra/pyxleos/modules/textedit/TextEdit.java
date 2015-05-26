package com.dakkra.pyxleos.modules.textedit;

import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import com.dakkra.pyxleos.ui.MainWindow;
import com.dakkra.pyxleos.util.Util;

public class TextEdit {
	private MainWindow mw;
	private JTextArea textArea;

	public TextEdit(MainWindow mw) {
		this.mw = mw;
		makeUI();
	}

	public void setText(String text) {
		textArea.setText(text);
	}

	public String getText() {
		String text = textArea.getText();
		return text;
	}

	private void makeUI() {
		JInternalFrame frame = Util.createIFrame("TextEdit");

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu(" File ");
		JMenuItem fileNew = new JMenuItem("New");
		JMenuItem fileOpen = new JMenuItem("Open");
		JMenuItem fileReopen = new JMenuItem("Reopen");
		fileReopen.setEnabled(false);
		JMenuItem fileSave = new JMenuItem("Save");
		JMenuItem fileSaveAs = new JMenuItem("Save As");
		JMenuItem fileExit = new JMenuItem("Exit");

		fileMenu.add(fileNew);
		fileMenu.add(fileOpen);
		fileMenu.add(fileReopen);
		fileMenu.add(fileSave);
		fileMenu.add(fileSaveAs);
		fileMenu.add(fileExit);

		JMenu options = new JMenu(" Options ");
		JMenuItem textSize = new JMenuItem("Text Size");
		options.add(textSize);

		menuBar.add(fileMenu);
		menuBar.add(options);

		textArea = new JTextArea("");

		frame.setJMenuBar(menuBar);

		frame.add(textArea);

		mw.addIFrame(frame);
	}
}
