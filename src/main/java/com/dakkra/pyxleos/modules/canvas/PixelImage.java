/*
 * Represents the image pixels to be drawn on the graphics context,
 */

package com.dakkra.pyxleos.modules.canvas;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class PixelImage {
	private int width;
	private int height;
	private PixelPoint mapArray[][];
	private BufferedImage img;

	public PixelImage(int width, int height) {
		this.width = width;
		this.height = height;
		mapArray = new PixelPoint[width][height];
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

	}

	public PixelImage(int width, int height, BufferedImage oimage) {
		this.width = width;
		this.height = height;
		mapArray = new PixelPoint[width][height];
		img = oimage;

	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				g.setColor(mapArray[i][j].getColor());
				g.drawLine(i, j, i, j);
			}
		}
	}

	public void setPixel(PixelPoint pixel) {
		// TODO make this an undoable action
		mapArray[pixel.getX()][pixel.getY()] = pixel;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Graphics2D createGraphics() {
		return img.createGraphics();
	}

	public void clearImage() {
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	public BufferedImage getImage() {
		return img;
	}
}
