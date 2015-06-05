package com.dakkra.pyxleos.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class ColorButton extends JButton {
	private static final long serialVersionUID = -6634922597932677896L;

	Color color;

	public ColorButton(Color color) {
		this.setText("                                                                        ");
		this.color = color;
		updateUI();
	}

	public void setColor(Color color) {
		this.color = color;
		updateUI();
	}

	@Override
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_PURE);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.BLACK);
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 20, 20);
		g.setColor(color);
		g.fillRect(6, 6, this.getWidth() - 12, this.getHeight() - 12);

	}

}
