package com.dakkra.pyxleos.modules.canvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import net.miginfocom.swing.MigLayout;

import com.dakkra.pyxleos.modules.Module;
import com.dakkra.pyxleos.ui.MainWindow;
import com.sun.glass.events.KeyEvent;

public class Canvas extends Module {

	private MainWindow mw;

	private DrawPane drawPane;

	private JTextField wField;

	private JTextField hField;

	private JTextField scaleField;

	public Canvas(MainWindow mw) {
		this.mw = mw;

		int returnval = JOptionPane.showConfirmDialog(null,
				dimensionDialogPanel(), "Image Dimensions : ",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (returnval == JOptionPane.OK_OPTION) {
			makeUI();
		} else {
			return;
		}
	}

	public void updateTitle() {
		int zoom = drawPane.getZoom();
		frame.setTitle("Canvas: (" + zoom + "x)");
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

		JButton transButton = new JButton("Transparency Color");
		transButton.setFocusable(false);
		transButton.addActionListener(new TransButtonEar());
		menuBar.add(transButton);

		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBackground(Color.DARK_GRAY);

		drawPane = new DrawPane(mw, this, new Dimension(Integer.parseInt(wField
				.getText()), Integer.parseInt(hField.getText())));

		JScrollPane pane = new JScrollPane(drawPane);

		container.add(pane, BorderLayout.CENTER);

		frame.add(container);

		updateTitle();

		mw.addIFrame(frame);
	}

	private JPanel dimensionDialogPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new MigLayout());

		panel.add(new JLabel("Enter dimensions"), "span");

		panel.add(new JLabel("Width: "));
		wField = new JTextField("16");
		wField.setColumns(4);
		panel.add(wField, "wrap");

		panel.add(new JLabel("Height: "));
		hField = new JTextField("16");
		hField.setColumns(4);
		panel.add(hField, "wrap");

		return panel;
	}

	private JPanel exportDialogPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new MigLayout());

		panel.add(new JLabel("Pixel Scale"), "span");

		panel.add(new JLabel("PX*"));
		scaleField = new JTextField("10");
		scaleField.setColumns(4);
		panel.add(scaleField, "wrap");

		return panel;
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
				File oFile = new File(oChooser.getSelectedFile()
						.getAbsoluteFile() + ".png");
				System.out.println(oFile.getAbsolutePath());

				try {
					ImageIO.write(drawPane.getImage(), "png", oFile);
					JOptionPane.showMessageDialog(frame, "Saved!");
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
			int returnval = JOptionPane.showConfirmDialog(null,
					exportDialogPanel(), "Export scale : ",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			int scaleAmt;
			try {
				scaleAmt = Integer.parseInt(scaleField.getText());
			} catch (NumberFormatException e2) {
				e2.printStackTrace();
				return;
			}
			if (returnval == JOptionPane.OK_OPTION & scaleAmt > 0) {
				JFileChooser oChooser = new JFileChooser();
				int returnVal = oChooser.showSaveDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File oFile = new File(oChooser.getSelectedFile()
							.getAbsoluteFile() + ".png");
					System.out.println(oFile.getAbsolutePath());
					BufferedImage orig = drawPane.getImage();
					int width = orig.getWidth() * scaleAmt;
					int height = orig.getHeight() * scaleAmt;
					BufferedImage img = null;
					try {
						img = new BufferedImage(width, height,
								BufferedImage.TYPE_INT_ARGB);
						Graphics2D g = img.createGraphics();
						g.drawImage(orig, 0, 0, width, height, frame);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(frame,
								"Image is too large");
						return;
					}

					try {
						ImageIO.write(img, "png", oFile);
						JOptionPane.showMessageDialog(frame, "Saved!");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					return;
				}
			}
		}
	}

	private class TransButtonEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Color newColor = JColorChooser.showDialog(frame,
					"Transparancy Color", drawPane.getTransparencyColor());

			if (newColor != null) {
				drawPane.setTransparencyColor(newColor);
			} else {
				return;
			}
		}

	}

}