package com.dakkra.pyxleos.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.dakkra.pyxleos.controller.CanvasController;
import com.dakkra.pyxleos.model.CanvasModel;
import com.dakkra.pyxleos.model.MainModel;
import com.dakkra.pyxleos.specialcomponents.CanvasPad;

public class CanvasView {
	MainModel m;
	CanvasModel cvm;
	CanvasController cvc;

	public static final Color canvasBG = new Color(80, 80, 80);

	public CanvasView(MainModel m, CanvasModel cvm, CanvasController cvc) {
		this.m = m;
		this.cvm = cvm;
		this.cvc = cvc;
	}

	public void createAndShowGUI() {

		cvm.menuBar = new JMenuBar();
		// file menu
		cvm.fileMenu = new JMenu(" File ");
		cvm.fileMenu.setEnabled(false);
		cvm.fileNew = new JMenuItem("New");
		cvm.fileOpen = new JMenuItem("Open");
		cvm.fileSave = new JMenuItem("Save");
		cvm.fileSave.addActionListener(new SaveListener());
		cvm.fileExit = new JMenuItem("Exit");
		cvm.fileExit.addActionListener(new ExitListener());
		cvm.fileMenu.add(cvm.fileNew);
		cvm.fileMenu.add(cvm.fileOpen);
		cvm.fileMenu.add(cvm.fileSave);
		cvm.fileMenu.add(cvm.fileExit);
		// all together
		cvm.menuBar.add(cvm.fileMenu);

		cvm.canvasFrame = new JInternalFrame("Canvas", true, true, true, true);
		cvm.canvasFrame.setBounds(520, 10, 400, 400);
		cvm.canvasFrame.setJMenuBar(cvm.menuBar);
		cvm.canvasFrame
				.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

		// TODO wrap image in container in a scrollable
		cvm.containerPanel = new JPanel();
		cvm.containerPanel.setPreferredSize(new Dimension(cvm.canvasFrame
				.getWidth(), cvm.canvasFrame.getHeight()));
		cvm.containerPanel.setLayout(new BorderLayout());

		cvm.canvasPad = new CanvasPad(cvm, m);

		cvm.containerPanel.add(cvm.canvasPad, BorderLayout.CENTER);

		cvm.canvasFrame.add(cvm.containerPanel);

		cvm.canvasFrame.setVisible(true);
		m.mainJDPane.add(cvm.canvasFrame);
	}

	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cvm.canvasFrame.dispose();

		}
	}

	private class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				cvc.saveImage();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}
}
