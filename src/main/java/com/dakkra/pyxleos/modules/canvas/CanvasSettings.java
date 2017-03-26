package com.dakkra.pyxleos.modules.canvas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

public class CanvasSettings {

	private static int offsetMoveAmt = 3;

	private static int blockSize = 15;

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
		BufferedImage tileImg = new BufferedImage(blockSize, blockSize, BufferedImage.TYPE_INT_ARGB);

		Graphics2D tileG = tileImg.createGraphics();

		tileG.setColor(transparencyPrimaryColor);
		tileG.fillRect(0, 0, blockSize, blockSize);

		tileG.setColor(transparencySecondaryColor);
		tileG.fillRect(0, 0, blockSize / 2, blockSize / 2);
		tileG.fillRect(blockSize / 2, blockSize / 2, blockSize, blockSize);

		transPaint = new TexturePaint(tileImg, new Rectangle(blockSize, blockSize));

		return transPaint;
	}

	public static int getBlockSize() {
		return blockSize;
	}

	public static void setBlockSize(int blockSize) {
		CanvasSettings.blockSize = blockSize;
	}

}
