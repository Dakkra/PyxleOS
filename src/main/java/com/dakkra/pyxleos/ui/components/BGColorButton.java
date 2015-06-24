package com.dakkra.pyxleos.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;

import com.dakkra.pyxleos.ColorReference;
import com.dakkra.pyxleos.util.Util;

public class BGColorButton extends JButton {
	private static final long serialVersionUID = -6634922597932677896L;

	Color color;

	public BGColorButton() {
		this.setText("        ");
		setFocusable(false);
		addActionListener(new ButtonEar());
		color = ColorReference.getBgColor();
		updateUI();
	}

	public void updateButton() {
		color = ColorReference.getBgColor();
		updateUI();
	}

	@Override
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.BLACK);
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 20, 20);
		g.setColor(color);
		g.fillRoundRect(3, 3, this.getWidth() - 6, this.getHeight() - 6, 10, 10);
		if (color.getAlpha() < 255) {
			g.setFont(Util.makeFont(20, Font.BOLD));
			g.setColor(Color.WHITE);
			char[] t = "S".toCharArray();
			g.drawChars(t, 0, 1, (this.getWidth() / 2) - (getFont().getSize() / 2),
					(this.getHeight() / 2) + (getFont().getSize() / 2) + 1);
		}

	}

	private class ButtonEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Color newColor = JColorChooser.showDialog(null, "Choose BG Color", color);

			if (newColor != null) {
				ColorReference.setBgColor(newColor);
			}
			color = ColorReference.getBgColor();
			updateUI();
		}

	}

}
