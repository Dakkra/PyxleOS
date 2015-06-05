package com.dakkra.pyxleos.ui.components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class ColorButton extends JButton {
	private static final long serialVersionUID = -6634922597932677896L;

	Color color;

	public ColorButton(Color color) {
		this.color = color;
		updateUI();
	}
	
	public void setColor(Color color){
		this.color = color;
		updateUI();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(color);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

}
