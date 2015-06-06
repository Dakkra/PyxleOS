package com.dakkra.pyxleos;

import java.awt.Color;

public class ColorReference {
	private Color fgColor = new Color(0, 0, 0);
	private Color bgColor = new Color(255, 255, 255);

	public Color getfgColor() {
		return fgColor;
	}

	public void setfgColor(Color color) {
		fgColor = color;
	}

	public Color getbgColor() {
		return bgColor;
	}

	public void setbgColor(Color color) {
		bgColor = color;
	}
}
