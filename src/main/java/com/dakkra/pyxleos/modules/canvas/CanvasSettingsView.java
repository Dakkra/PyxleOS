package com.dakkra.pyxleos.modules.canvas;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.dakkra.pyxleos.ui.MainWindow;
import com.dakkra.pyxleos.util.Util;
import com.sun.glass.events.KeyEvent;

import net.miginfocom.swing.MigLayout;

public class CanvasSettingsView {

	private MainWindow mw;

	private JInternalFrame frame;

	private JTextField moveAmtField;

	public CanvasSettingsView(MainWindow mw) {
		this.mw = mw;
	}

	public void cnsGUI() {
		frame = Util.createIFrame("Canvas Settings");

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu(" File ");
		JMenuItem fileSave = new JMenuItem("Save");
		fileSave.setMnemonic(KeyEvent.VK_S);
		fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		fileSave.addActionListener(new SaveEar());
		JMenuItem fileExit = new JMenuItem("Exit");
		fileExit.setMnemonic(KeyEvent.VK_Q);
		fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		fileExit.addActionListener(new CancelEar());
		fileMenu.add(fileSave);
		fileMenu.addSeparator();
		fileMenu.add(fileExit);
		menuBar.add(fileMenu);

		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());

		JLabel moveAmtLabel = new JLabel("Arrow Key Movement Amount: ");
		panel.add(moveAmtLabel);

		moveAmtField = new JTextField("" + CanvasSettings.getOffsetMoveAmt());
		moveAmtField.setColumns(2);
		panel.add(moveAmtField, "wrap");

		JButton transparencyButton = new JButton("Transparency Colors");
		transparencyButton.addActionListener(new TransparencyEar());
		panel.add(transparencyButton, "span, grow");

		Container buttonContainer = new Container();
		buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER));

		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveEar());

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelEar());

		buttonContainer.add(saveButton);
		buttonContainer.add(cancelButton);

		panel.add(buttonContainer, "span, grow");

		frame.add(panel);

		frame.setJMenuBar(menuBar);

		frame.pack();
		frame.setMaximizable(false);
		frame.setResizable(false);

		mw.addIFrame(frame);
	}

	// Event listeners (I call them ears.. heh)
	private class CancelEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}

	}

	private class SaveEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			CanvasSettings.setOffsetMoveAmt(Integer.parseInt(moveAmtField.getText()));
			mw.saveCanvasSettings();
		}

	}

	private class TransparencyEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			TransparencyCustomizer transparencyCustomizer = new TransparencyCustomizer(mw);
		}

	}

}
