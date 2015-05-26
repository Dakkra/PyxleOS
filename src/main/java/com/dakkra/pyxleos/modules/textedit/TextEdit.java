package com.dakkra.pyxleos.modules.textedit;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import com.dakkra.pyxleos.ui.MainWindow;
import com.dakkra.pyxleos.util.Util;
import com.sun.glass.events.KeyEvent;

public class TextEdit {
	private MainWindow mw;
	private JTextArea textArea;
	private JInternalFrame frame;
	private File textFile;
	private JMenuItem fileReopen;
	private Font textAreaFont;

	public TextEdit(MainWindow mw) {
		this.mw = mw;
		textFile = null;
		makeUI();
	}
	
	public void setText(String text){
		textArea.setText(text);
	}

	public String getText() {
		String text = textArea.getText();
		return text;
	}

	private void makeUI() {
		frame = Util.createIFrame("TextEdit");

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu(" File ");
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

		JMenu options = new JMenu(" Options ");
		JMenuItem textSize = new JMenuItem("Text Size");
		textSize.addActionListener(new TextSizeEar());
		textSize.setMnemonic(KeyEvent.VK_O);
		textSize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.ALT_MASK));
		options.add(textSize);

		menuBar.add(fileMenu);
		menuBar.add(options);

		textArea = new JTextArea("");

		textAreaFont = new Font("Arial", Font.PLAIN, 17);

		textArea.setFont(textAreaFont);

		frame.setJMenuBar(menuBar);

		JScrollPane textPane = new JScrollPane(textArea);

		frame.add(textPane);

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
			fileReopen.setEnabled(true);
			System.out.println("Name: " + textFile.getName() + "||URI: "
					+ textFile.getAbsolutePath());
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

	private class TextSizeEar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int size = Integer.parseInt(JOptionPane.showInputDialog(
					"Input a new font size:", textAreaFont.getSize()));
			if (size <= 0 || size >100){
				JOptionPane.showMessageDialog(null, "Invalid size! (1-100 only)");
			}else{
				textAreaFont = new Font("Arial", Font.PLAIN, size);
				textArea.setFont(textAreaFont);
			}
		}
	}
}
