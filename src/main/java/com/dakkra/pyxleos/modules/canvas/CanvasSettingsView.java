package com.dakkra.pyxleos.modules.canvas;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dakkra.pyxleos.ui.MainWindow;
import com.dakkra.pyxleos.util.Util;

import net.miginfocom.swing.MigLayout;

public class CanvasSettingsView {

	private MainWindow mw;

	public CanvasSettingsView(MainWindow mw) {
		this.mw = mw;
	}

	public void cnsGUI() {
		JInternalFrame frame = Util.createIFrame("Canvas Settings");

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu(" File ");
		JMenuItem fileSave = new JMenuItem(" Save ");
		JMenuItem fileExit = new JMenuItem("Exit");
		fileMenu.add(fileSave);
		fileMenu.addSeparator();
		fileMenu.add(fileExit);
		menuBar.add(fileMenu);

		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());

		JLabel moveAmtLabel = new JLabel("Arrow Key Movement Amount: ");
		panel.add(moveAmtLabel);

		JTextField moveAmtField = new JTextField("" + CanvasSettings.getOffsetMoveAmt());
		moveAmtField.setColumns(2);
		panel.add(moveAmtField, "wrap");

		JButton tranparencyButton = new JButton("Transparency Color");

		panel.add(tranparencyButton, "span, grow");

		Container buttonContainer = new Container();
		buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER));

		JButton saveButton = new JButton("Save");

		JButton cancelButton = new JButton("Cancel");

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

}
