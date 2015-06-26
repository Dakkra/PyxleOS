package com.dakkra.pyxleos.modules.canvas;

import java.awt.Color;

public class CanvasSettings {

	private static int offsetMoveAmt = 3;

	private static Color transparencyColor = new Color(102, 102, 102);

	public static int getOffsetMoveAmt() {
		return offsetMoveAmt;
	}

	public static void setOffsetMoveAmt(int amt) {
		offsetMoveAmt = amt;
	}

	public static Color getTransparencyColor() {
		return transparencyColor;
	}

	public static void setTransparencyColor(Color transparencyColor) {
		CanvasSettings.transparencyColor = transparencyColor;
	}

}
