package com.dakkra.pyxleos;

import java.awt.Color;

public class ColorReference {
	private static Color fgColor = new Color(0, 0, 0);
	private static Color bgColor = new Color(255, 255, 255);

	public static Color getFgColor() {
		return fgColor;
	}

	public static void setFgColor(Color fgColor) {
		ColorReference.fgColor = fgColor;
	}

	public static Color getBgColor() {
		return bgColor;
	}

	public static void setBgColor(Color bgColor) {
		ColorReference.bgColor = bgColor;
	}
}
