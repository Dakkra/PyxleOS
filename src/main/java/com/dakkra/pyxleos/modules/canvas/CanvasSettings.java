package com.dakkra.pyxleos.modules.canvas;

import java.awt.Color;
import java.awt.TexturePaint;

public class CanvasSettings {

	private static int offsetMoveAmt = 3;

	private static int blockSize = 4;

	private static Color transparencyPrimaryColor = new Color(102, 102, 102);

	private static Color transparencySecondaryColor = new Color(51, 51, 51);

	private static TexturePaint transPaint;

	public static int getOffsetMoveAmt() {
		return offsetMoveAmt;
	}

	public static void setOffsetMoveAmt(int amt) {
		offsetMoveAmt = amt;
	}

	public static Color getTransparencyPrimaryColor() {
		return transparencyPrimaryColor;
	}

	public static void setTransparencyPrimaryColor(Color transparencyPrimaryColor) {
		CanvasSettings.transparencyPrimaryColor = transparencyPrimaryColor;
	}

	public static Color getTransparencySecondaryColor() {
		return transparencySecondaryColor;
	}

	public static void setTransparencySecondaryColor(Color transparencySecondaryColor) {
		CanvasSettings.transparencySecondaryColor = transparencySecondaryColor;
	}

	public static void setDefaults() {

	}

	public static TexturePaint getTransPaint() {
		return transPaint;
	}

	public static void setTransPaint(TexturePaint transPaint) {
		CanvasSettings.transPaint = transPaint;
	}

	public static int getBlockSize() {
		return blockSize;
	}

	public static void setBlockSize(int blockSize) {
		CanvasSettings.blockSize = blockSize;
	}

}
