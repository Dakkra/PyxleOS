package com.dakkra.pyxleos.modules.textedit;

import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.undo.UndoManager;

import com.dakkra.pyxleos.modules.Module;
import com.dakkra.pyxleos.ui.MainWindow;
import com.dakkra.pyxleos.util.Util;
import com.sun.glass.events.KeyEvent;

public class TextEdit extends Module {
	private MainWindow mw;
	private JTextArea textArea;
	// private JInternalFrame frame;
	private File textFile;
	private JMenuItem fileReopen;
	private Font textAreaFont;

	private UndoManager undoManager = new UndoManager();

	public TextEdit(MainWindow mw) {
		this.mw = mw;
		textFile = null;
		makeUI();
	}

	public void setText(String text) {
		textArea.setText(text);
	}

	public String getText() {
		String text = textArea.getText();
		return text;
	}

	public void packFrame() {
		frame.pack();
		Rectangle bounds = mw.getDesktopPaneBounds();
		Point center = new Point((bounds.width / 2) - (frame.getWidth() / 2),
				(bounds.height / 2) - (frame.getHeight() / 2));
		frame.setLocation(center);
	}

	private void makeUI() {

		frame.setTitle("TextEdit");

		JMenuItem fileNew = new JMenuItem("New");
		fileNew.addActionListener(new NewEar());
		fileNew.setMnemonic(KeyEvent.VK_N);
		fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		JMenuItem fileOpen = new JMenuItem("Open");
		fileOpen.addActionListener(new OpenEar());
		fileOpen.setMnemonic(KeyEvent.VK_O);
		fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		fileReopen = new JMenuItem("Reopen");
		fileReopen.setEnabled(false);
		fileReopen.addActionListener(new ReopenEar());
		fileReopen.setMnemonic(KeyEvent.VK_R);
		fileReopen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				ActionEvent.CTRL_MASK));
		JMenuItem fileSave = new JMenuItem("Save");
		fileSave.addActionListener(new SaveEar());
		fileSave.setMnemonic(KeyEvent.VK_S);
		fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		JMenuItem fileSaveAs = new JMenuItem("Save As");
		fileSaveAs.addActionListener(new SaveAsEar());
		fileSaveAs.setMnemonic(KeyEvent.VK_S);
		fileSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
		JMenuItem fileExit = new JMenuItem("Exit");
		fileExit.addActionListener(new ExitEar());
		fileExit.setMnemonic(KeyEvent.VK_Q);
		fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));

		fileMenu.add(fileNew);
		fileMenu.add(fileOpen);
		fileMenu.add(fileReopen);
		fileMenu.add(fileSave);
		fileMenu.add(fileSaveAs);
		fileMenu.add(fileExit);

		JMenu edit = new JMenu(" Edit ");
		JMenuItem undo = new JMenuItem("Undo");
		undo.addActionListener(new UndoEar());
		undo.setMnemonic(KeyEvent.VK_Z);
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				ActionEvent.CTRL_MASK));
		JMenuItem redo = new JMenuItem("Redo");
		redo.addActionListener(new RedoEar());
		redo.setMnemonic(KeyEvent.VK_Z);
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));

		edit.add(undo);
		edit.add(redo);

		JMenu options = new JMenu(" Options ");
		JMenuItem textSize = new JMenuItem("Text Size");
		textSize.addActionListener(new TextSizeEar());
		textSize.setMnemonic(KeyEvent.VK_O);
		textSize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.ALT_MASK));
		options.add(textSize);

		menuBar.add(fileMenu);
		menuBar.add(edit);
		menuBar.add(options);

		textArea = new JTextArea("");

		textArea.getDocument().addUndoableEditListener(undoManager);

		textAreaFont = Util.makeFont(17, Font.PLAIN);

		textArea.setFont(textAreaFont);

		frame.setJMenuBar(menuBar);

		JScrollPane textPane = new JScrollPane(textArea);

		frame.add(textPane);

		frame.setSize(550, 350);

		mw.addIFrame(frame);
	}

	private class ExitEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Util.exitIFrame(frame);
		}

	}

	private class NewEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			TextEditControl.reset(frame, textArea);
			textFile = null;
			fileReopen.setEnabled(false);
		}

	}

	private class OpenEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfc = new JFileChooser();
			int returnval = jfc.showOpenDialog(null);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				textFile = jfc.getSelectedFile();
				textArea.setText(TextEditControl.readText(textFile, frame));
				fileReopen.setEnabled(true);
			} else {
				return;
			}
		}
	}

	private class ReopenEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			textArea.setText(TextEditControl.readText(textFile, frame));
		}

	}

	private class SaveEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			textFile = TextEditControl.saveText(textFile, textArea, frame);

			if (textFile != null) {
				fileReopen.setEnabled(true);
				System.out.println("Name: " + textFile.getName() + "||URI: "
						+ textFile.getAbsolutePath());
			} else {
				return;
			}
		}
	}

	private class SaveAsEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfc = new JFileChooser();
			int returnval = jfc.showSaveDialog(null);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				textFile = jfc.getSelectedFile();
				textFile = TextEditControl.saveText(textFile, textArea, frame);
				fileReopen.setEnabled(true);
				System.out.println("Name: " + textFile.getName() + "||URI: "
						+ textFile.getAbsolutePath());
			} else {
				return;
			}
		}
	}

	private class RedoEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (undoManager.canRedo()) {
				undoManager.redo();
			}
		}

	}

	private class UndoEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (undoManager.canUndo()) {
				undoManager.undo();
			}
		}

	}

	private class TextSizeEar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int size = textAreaFont.getSize();
			try {
				size = Integer.parseInt(JOptionPane.showInputDialog(
						"Input a new font size:", textAreaFont.getSize()));
			} catch (NumberFormatException e1) {
				System.out.println("Invalid Size or user cancelled");
			}
			if (size <= 0 || size > 70) {
				JOptionPane
						.showMessageDialog(null, "Invalid size! (1-70 only)");
			} else {
				textAreaFont = Util.makeFont(size, Font.PLAIN);
				textArea.setFont(textAreaFont);
			}
		}
	}
}
