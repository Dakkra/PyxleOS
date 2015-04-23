package com.dakkra.pyxleos.specialcomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import com.dakkra.pyxleos.model.CanvasModel;

public class CanvasPad extends JComponent {
	private static final long serialVersionUID = 6748629663390647156L;
	
	public CanvasModel cvm;
	// this is gonna be your image that you draw on
	private BufferedImage image;

	// this is what we'll be using to draw on
	private Graphics2D graphics2D;

	// these are gonna hold our mouse coordinates
//	private int currentX, currentY, oldX, oldY;
	private Point currentPoint , oldPoint; 

	public CanvasPad(CanvasModel cvm) {
		this.cvm = cvm;
		cvm.canvasImage = new BufferedImage(300,300,BufferedImage.TYPE_INT_ARGB);
		this.image = cvm.canvasImage;
		graphics2D = image.createGraphics();

		MouseDragListener mouseDragListener = new MouseDragListener();
		
		addMouseListener(mouseDragListener);
		
		// if the mouse is pressed it sets the oldX & oldY
		// coordinates as the mouses x & y coordinates
		addMouseMotionListener(mouseDragListener);
		// while the mouse is dragged it sets currentX & currentY as the mouses
		// x and y
		// then it draws a line at the coordinates
		// it repaints it and sets oldX and oldY as currentX and currentY
		clear();
	}
	
	public Point convertToImageCoord(Point point){
		Rectangle bounds = getBounds();
		int x = point.x - ((bounds.width-image.getWidth())/2);
		int y = point.y - ((bounds.height-image.getHeight())/2);

		return new Point(x,y);
	}
	
	public void paintComponent(Graphics g) {
		Point point = convertToImageCoord(new Point (0,0));
		g.drawImage(image, -point.x, -point.y, image.getWidth(), image.getHeight(), this);
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
		graphics2D.fillRect(0,0, image.getWidth(), image.getHeight());
		graphics2D.dispose();
	}
	private class MouseDragListener extends MouseMotionAdapter implements MouseListener{

		@Override
		public void mousePressed(MouseEvent e) {
			oldPoint = convertToImageCoord(e.getPoint());
		}
		
		public void mouseDragged(MouseEvent e) {
			currentPoint = convertToImageCoord(e.getPoint());
			if (graphics2D != null) {
				graphics2D.setPaint(Color.black);
				graphics2D.drawLine(oldPoint.x, oldPoint.y, currentPoint.x, currentPoint.y);
			}
			repaint();
			oldPoint = currentPoint;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	
}
