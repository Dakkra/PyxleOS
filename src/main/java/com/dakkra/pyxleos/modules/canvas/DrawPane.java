package com.dakkra.pyxleos.modules.canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class DrawPane extends JComponent {
	private static final long serialVersionUID = 6748629663390647156L;

	private BufferedImage image;

	private Graphics2D g2;

	private Point currentPoint;

	private int scale;

	public DrawPane() {
		image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		g2 = image.createGraphics();

		scale = 10;

		MouseDragListener mouseDragListener = new MouseDragListener();

		addMouseListener(mouseDragListener);

		addMouseMotionListener(mouseDragListener);

		addMouseWheelListener(mouseDragListener);

		clear();
	}

	private void scaleUp() {
		scale += 1;
		repaint();
	}

	private void scaleDown() {
		if (scale - 1 > 0) {
			scale -= 1;
		}
		repaint();
	}

	public Point convertToCanvasCoord(Point point) {
		point = centerImageCoord(point);

		int x = point.x / scale;
		int y = point.y / scale;

		return new Point(x, y);
	}

	public Point centerImageCoord(Point point) {
		Rectangle bounds = getBounds();
		int x = (point.x - ((bounds.width - image.getWidth() * scale) / 2));
		int y = (point.y - ((bounds.height - image.getHeight() * scale) / 2));

		return new Point(x, y);
	}

	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		Point point = centerImageCoord(new Point(0, 0));
		g.setColor(Color.GRAY);
		g.fillRect(-point.x, -point.y, image.getWidth() * scale,
				image.getHeight() * scale);
		g.drawImage(image, -point.x, -point.y, image.getWidth() * scale,
				image.getHeight() * scale, this);
	}

	public void clear() {
		image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		g2 = image.createGraphics();

		repaint();

	}

	private class MouseDragListener extends MouseMotionAdapter implements
			MouseListener, MouseWheelListener {

		@Override
		public void mousePressed(MouseEvent e) {
			currentPoint = convertToCanvasCoord(e.getPoint());
			if (g2 != null) {
				g2.setPaint(Color.BLACK);
				g2.drawLine(currentPoint.x, currentPoint.y, currentPoint.x,
						currentPoint.y);
			}
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			currentPoint = convertToCanvasCoord(e.getPoint());
			if (g2 != null) {
				g2.setPaint(Color.BLACK);
				g2.drawLine(currentPoint.x, currentPoint.y, currentPoint.x,
						currentPoint.y);
			}
			repaint();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getWheelRotation() < 0) {
				scaleUp();
			} else if (e.getWheelRotation() > 0) {
				scaleDown();
			}

		}

	}

}
