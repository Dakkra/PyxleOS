package com.dakkra.pyxleos.ui;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.dakkra.pyxleos.PyxleOS;

public class UISettings {
	private Color bgColor = new Color(43, 57, 71);
	private Color baseColor = new Color(43, 57, 71);
	private Color baseRedColor = new Color(7, 75, 125);
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

	public Color getbaseRedColor() {
		return baseRedColor;
	}

	public void setbaseRedColor(Color color) {
		baseRedColor = color;
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
	public void setDefault() {
		bgColor = new Color(43, 57, 71);
		baseColor = bgColor;
		baseRedColor = new Color(7, 75, 125);
		textColor = new Color(255, 255, 255);
	}

	public void setCrimson() {
		bgColor = new Color(71, 43, 43);
		baseColor = bgColor;
		baseRedColor = new Color(125, 6, 6);
		textColor = new Color(255, 255, 255);
	}

	public void setSleek() {
		bgColor = new Color(57, 57, 57);
		baseColor = bgColor;
		baseRedColor = new Color(81, 81, 81);
		textColor = new Color(255, 255, 255);
	}
	
	public void setSciFi() {
		bgColor = new Color(51, 43, 71);
		baseColor = new Color(53,88,57);
		baseRedColor = new Color(14, 133, 34);
		textColor = new Color(255, 255, 255);
	}
}
