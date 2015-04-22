package com.dakkra.pyxleos.specialcomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class CanvasPad extends JComponent {
	private static final long serialVersionUID = 6748629663390647156L;
	
	// this is gonna be your image that you draw on
	private BufferedImage image;

	// this is what we'll be using to draw on
	private Graphics2D graphics2D;

	// these are gonna hold our mouse coordinates
	private int currentX, currentY, oldX, oldY;

	public CanvasPad() {
		image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		graphics2D = image.createGraphics();

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				oldX = e.getX();
				oldY = e.getY();
			}
		});
		
		// if the mouse is pressed it sets the oldX & oldY
		// coordinates as the mouses x & y coordinates
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				currentX = e.getX();
				currentY = e.getY();
				if (graphics2D != null) {
					graphics2D.setPaint(Color.black);
					graphics2D.drawLine(oldX, oldY, currentX, currentY);
				}
				repaint();
				oldX = currentX;
				oldY = currentY;
			}

		});
		// while the mouse is dragged it sets currentX & currentY as the mouses
		// x and y
		// then it draws a line at the coordinates
		// it repaints it and sets oldX and oldY as currentX and currentY
		
		clear();
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, 100, 100, this);
	}

	// this is the painting bit
	// if it has nothing on it then
	// it creates an image the size of the window
	// sets the value of Graphics as the image
	// sets the rendering
	// runs the clear() method
	// then it draws the image

	public void clear() {
		Graphics2D graphics2D = image.createGraphics();

		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setPaint(Color.WHITE);
		graphics2D.fillRect(0, 0, image.getWidth(), image.getHeight());

		graphics2D.dispose();
	}
	
}
