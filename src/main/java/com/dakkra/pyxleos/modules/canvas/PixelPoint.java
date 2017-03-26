/*
 * Represents a colored pixel to be added to a PixelMap
 */

package com.dakkra.pyxleos.modules.canvas;

import java.awt.Color;
import java.awt.Point;

public class PixelPoint {
	private int x;
	private int y;
	private Color color;

	public PixelPoint(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public Point getPoint() {
		return new Point(x, y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getColor() {
		return color;
	}
}
