package com.dakkra.pyxleos.modules.canvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import com.dakkra.pyxleos.modules.Module;
import com.dakkra.pyxleos.ui.MainWindow;
import com.sun.glass.events.KeyEvent;

public class Canvas extends Module {

	private MainWindow mw;

	private DrawPane drawPane;

	public Canvas(MainWindow mw) {
		this.mw = mw;
		makeUI();
	}

	private void makeUI() {
		frame.setTitle("Canvas");

		JMenuItem fileSave = new JMenuItem("Save");
		fileSave.addActionListener(new SaveEar());
		fileSave.setMnemonic(KeyEvent.VK_S);
		fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));

		JMenuItem fileExport = new JMenuItem("Export");
		fileExport.addActionListener(new ExportEar());
		fileExport.setMnemonic(KeyEvent.VK_E);
		fileExport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				ActionEvent.CTRL_MASK));

		fileMenu.add(fileSave);
		fileMenu.add(fileExport);
		fileMenu.add(fileExit);

		JMenu editMenu = new JMenu(" Edit ");
		JMenuItem editClear = new JMenuItem("Clear");
		editClear.addActionListener(new ClearEar());
		editClear.setMnemonic(KeyEvent.VK_N);
		editClear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		editMenu.add(editClear);

		menuBar.add(editMenu);

		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBackground(Color.DARK_GRAY);

		drawPane = new DrawPane(new Dimension(16, 16));

		JScrollPane pane = new JScrollPane(drawPane);

		container.add(pane, BorderLayout.CENTER);

		frame.add(container);

		mw.addIFrame(frame);
	}

	private class ClearEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			drawPane.clear();
		}

	}

	private class SaveEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser oChooser = new JFileChooser();
			int returnVal = oChooser.showSaveDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File oFile = oChooser.getSelectedFile();
				System.out.println(oFile.getAbsolutePath());
				BufferedImage img = drawPane.getImage();

				try {
					ImageIO.write(img, "png", oFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				return;
			}
		}

	}

	private class ExportEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser oChooser = new JFileChooser();
			int returnVal = oChooser.showSaveDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File oFile = oChooser.getSelectedFile();
				System.out.println(oFile.getAbsolutePath());
				BufferedImage img = drawPane.getScaledImage(10);

				try {
					ImageIO.write(img, "png", oFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				return;
			}
		}
	}

}
