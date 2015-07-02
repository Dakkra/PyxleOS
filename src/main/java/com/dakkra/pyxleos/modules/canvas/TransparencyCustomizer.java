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

	private TexturePaint tilePaint;

	private BufferedImage tileImg;

	private TilePane tilePane;

	private JTextField blockSizeField = new JTextField("4");

	public TransparencyCustomizer(MainWindow mw) {
		this.mw = mw;
		updatePaint();
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

		UIColorButton primaryColorButton = new UIColorButton(primaryColor);
		primaryColorButton.setText("      ");
		UIColorButton secondaryColorButton = new UIColorButton(secondaryColor);
		secondaryColorButton.setText("      ");

		blockSizeField.setColumns(3);

		JButton updateButton = new JButton("Update ->");
		updateButton.addActionListener(new UpdateEar());
		JButton saveButton = new JButton("Save");
		JButton cancelButton = new JButton("Cancel");

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
		int size = Integer.parseInt(blockSizeField.getText()) * 2;
		tileImg = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

		Graphics2D tileG = tileImg.createGraphics();

		tileG.setColor(primaryColor);
		tileG.fillRect(0, 0, size, size);

		tileG.setColor(secondaryColor);
		tileG.fillRect(0, 0, size / 2, size / 2);
		tileG.fillRect(size / 2, size / 2, size, size);

		tilePaint = new TexturePaint(tileImg, new Rectangle(tileImg.getWidth(), tileImg.getHeight()));
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

}
