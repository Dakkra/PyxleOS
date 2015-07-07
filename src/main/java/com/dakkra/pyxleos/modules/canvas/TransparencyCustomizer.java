package com.dakkra.pyxleos.modules.canvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.dakkra.pyxleos.ui.MainWindow;
import com.dakkra.pyxleos.ui.components.UIColorButton;
import com.dakkra.pyxleos.util.Util;

import net.miginfocom.swing.MigLayout;

public class TransparencyCustomizer {
	private MainWindow mw;

	private JInternalFrame frame;

	private Color primaryColor = CanvasSettings.getTransparencyPrimaryColor();

	private Color secondaryColor = CanvasSettings.getTransparencySecondaryColor();

	private int blockSize = CanvasSettings.getBlockSize();

	private TexturePaint tilePaint;

	private BufferedImage tileImg;

	private TilePane tilePane;

	private JTextField blockSizeField = new JTextField("" + blockSize);

	private UIColorButton primaryColorButton;

	private UIColorButton secondaryColorButton;

	private boolean isReady;

	public TransparencyCustomizer(MainWindow mw) {
		this.mw = mw;
		updatePaint();
		isReady = false;
		cnsUI();
	}

	private void cnsUI() {
		frame = Util.createIFrame("Transparency Customization");
		frame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

		JPanel mPanel = new JPanel();
		mPanel.setBackground(Color.darkGray);
		mPanel.setLayout(new BorderLayout());

		Container controlsContainer = new Container();
		JPanel displayContainer = new JPanel();

		controlsContainer.setLayout(new MigLayout());
		displayContainer.setLayout(new BorderLayout());

		primaryColorButton = new UIColorButton(primaryColor);
		primaryColorButton.setText("      ");
		primaryColorButton.addActionListener(new PrimaryColorEar());

		secondaryColorButton = new UIColorButton(secondaryColor);
		secondaryColorButton.setText("      ");
		secondaryColorButton.addActionListener(new SecondaryColorEar());

		blockSizeField.setColumns(3);

		JButton updateButton = new JButton("Update ->");
		updateButton.addActionListener(new UpdateEar());
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveEar());
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelEar());

		controlsContainer.add(primaryColorButton, "span, grow");
		controlsContainer.add(secondaryColorButton, "span, grow");
		controlsContainer.add(new JLabel("BlockSize: "));
		controlsContainer.add(blockSizeField, "wrap");
		controlsContainer.add(updateButton, "span, grow");
		controlsContainer.add(saveButton, "span, grow");
		controlsContainer.add(cancelButton, "span, grow");

		tilePane = new TilePane();

		JScrollPane scrollPane = new JScrollPane(tilePane);

		displayContainer.add(scrollPane, BorderLayout.CENTER);

		mPanel.add(controlsContainer, BorderLayout.WEST);
		mPanel.add(displayContainer, BorderLayout.CENTER);

		frame.add(mPanel);

		frame.setSize(345, 240);
		frame.setMaximizable(false);
		frame.setResizable(false);
		mw.addIFrame(frame);
	}

	private void updatePaint() {
		blockSize = Integer.parseInt(blockSizeField.getText());
		tileImg = new BufferedImage(blockSize, blockSize, BufferedImage.TYPE_INT_ARGB);

		Graphics2D tileG = tileImg.createGraphics();

		tileG.setColor(primaryColor);
		tileG.fillRect(0, 0, blockSize, blockSize);

		tileG.setColor(secondaryColor);
		tileG.fillRect(0, 0, blockSize / 2, blockSize / 2);
		tileG.fillRect(blockSize / 2, blockSize / 2, blockSize, blockSize);

		tilePaint = new TexturePaint(tileImg, new Rectangle(tileImg.getWidth(), tileImg.getHeight()));
	}

	public boolean isReady() {
		return isReady;
	}

	public TexturePaint getTexturePaint() {
		return tilePaint;
	}

	private class TilePane extends JComponent {

		private static final long serialVersionUID = 8798543884300456743L;

		public TilePane() {

		}

		@Override
		public void paint(Graphics g1) {
			Graphics2D g = (Graphics2D) g1;
			g.setPaint(tilePaint);
			g.fillRect(0, 0, 200, 200);
			g.setColor(mw.getSelectionColor());
			g.drawRect(0, 0, 200, 200);
		}
	}

	private class UpdateEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			updatePaint();
			tilePane.repaint();
		}
	}

	private class SaveEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			CanvasSettings.setBlockSize(blockSize);
			CanvasSettings.setTransparencyPrimaryColor(primaryColor);
			CanvasSettings.setTransparencySecondaryColor(secondaryColor);
			isReady = true;
		}

	}

	private class CancelEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}

	}

	private class PrimaryColorEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Color newColor = JColorChooser.showDialog(null, "Color", primaryColor);
			if (newColor != null) {
				primaryColor = newColor;
				primaryColorButton.setColor(newColor);
			} else {
				return;
			}
		}

	}

	private class SecondaryColorEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Color newColor = JColorChooser.showDialog(null, "Color", secondaryColor);
			if (newColor != null) {
				secondaryColor = newColor;
				secondaryColorButton.setColor(newColor);
			} else {
				return;
			}
		}

	}

}
