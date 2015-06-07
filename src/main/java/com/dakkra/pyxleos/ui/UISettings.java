package com.dakkra.pyxleos.ui;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.dakkra.pyxleos.PyxleOS;

public class UISettings {
	private Color bgColor = new Color(43, 57, 71);
	private Color baseColor = new Color(43, 57, 71);
	private Color selectionColor = new Color(7, 75, 125);
	private Color textColor = new Color(255, 255, 255);
	private Image bg;

	public UISettings() {
		try {
			bg = ImageIO
					.read(PyxleOS.class.getResource("/PyxleOS-Default.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Color getbgColor() {
		return bgColor;
	}

	public void setbgColor(Color color) {
		bgColor = color;
	}

	public Color getbaseColor() {
		return baseColor;
	}

	public void setbaseColor(Color color) {
		baseColor = color;
	}

	public Color getselectionRedColor() {
		return selectionColor;
	}

	public void setselectionRedColor(Color color) {
		selectionColor = color;
	}

	public Color gettextColor() {
		return textColor;
	}

	public void settextColor(Color color) {
		textColor = color;
	}

	public Image getbgImage() {
		return bg;
	}

	public void setbgImage(Image image) {
		bg = image;
	}

	// Default Options
	public void setThemeDefault() {
		bgColor = new Color(43, 57, 71);
		baseColor = bgColor;
		selectionColor = new Color(7, 75, 125);
		textColor = new Color(255, 255, 255);
	}

	public void setThemeCrimson() {
		bgColor = new Color(71, 43, 43);
		baseColor = bgColor;
		selectionColor = new Color(142, 61, 61);
		textColor = new Color(255, 255, 255);
	}

	public void setThemeSleek() {
		bgColor = new Color(57, 57, 57);
		baseColor = bgColor;
		selectionColor = new Color(81, 81, 81);
		textColor = new Color(255, 255, 255);
	}

	public void setThemeSciFi() {
		bgColor = new Color(51, 43, 71);
		baseColor = new Color(53, 88, 57);
		selectionColor = new Color(14, 133, 34);
		textColor = new Color(255, 255, 255);
	}

	public void setThemeFrozen() {
		bgColor = new Color(56, 96, 126);
		baseColor = new Color(52, 95, 105);
		selectionColor = new Color(4, 57, 77);
		textColor = new Color(255, 255, 255);
	}

	public void setThemeNight() {
		bgColor = new Color(0, 0, 0);
		baseColor = new Color(36, 36, 51);
		selectionColor = new Color(74, 74, 98);
		textColor = new Color(255, 255, 255);
	}

	public void setThemeDaylight() {
		bgColor = new Color(88, 141, 181);
		baseColor = new Color(82, 151, 167);
		selectionColor = new Color(40, 103, 128);
		textColor = new Color(0, 0, 0);
	}

	public void setThemeForest() {
		bgColor = new Color(43, 71, 43);
		baseColor = new Color(47, 71, 43);
		selectionColor = new Color(6, 125, 48);
		textColor = new Color(255, 255, 255);
	}
}
