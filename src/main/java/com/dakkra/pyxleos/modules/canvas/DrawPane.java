package com.dakkra.pyxleos.modules.canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import com.dakkra.pyxleos.ColorReference;
import com.dakkra.pyxleos.ui.MainWindow;

public class DrawPane extends JComponent {
	private static final long serialVersionUID = 6748629663390647156L;

	private MainWindow mw;
	private Canvas canvas;
	private Color paintColor;
	private PixelImage image;
	private BufferedImage prevLayer;
	private Graphics2D g2;
	private Graphics2D gPrev;
	private Point currentPoint, primaryPoint, offsetPoint;
	private int scale;
	private Color transparentColor;
	private Color fgColor = ColorReference.getFgColor();
	private Color bgColor = ColorReference.getBgColor();
	private TexturePaint transPaint = CanvasSettings.getTransPaint();
	private int width;
	private int height;
	private int offsetX;
	private int offsetY;
	private int offsetMoveAmt = CanvasSettings.getOffsetMoveAmt();

	public DrawPane(MainWindow mw, Canvas canvas, Dimension d) {
		this.mw = mw;
		this.canvas = canvas;
		width = d.width;
		height = d.height;
		image = new PixelImage(width, height);
		prevLayer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2 = image.createGraphics();
		gPrev = prevLayer.createGraphics();
		transparentColor = new Color(102, 102, 102);
		scale = 10;
		DefaultToolListener mouseDragListener = new DefaultToolListener();
		addMouseListener(mouseDragListener);
		addMouseMotionListener(mouseDragListener);
		addMouseWheelListener(mouseDragListener);
		addKeyListener(mouseDragListener);
		setFocusable(true);
		requestFocus();
		requestFocusInWindow();
		clear();
	}

	public DrawPane(MainWindow mw, Canvas canvas, BufferedImage oimage) {
		this.mw = mw;
		this.canvas = canvas;
		width = oimage.getWidth();
		height = oimage.getHeight();
		image = new PixelImage(width, height, oimage);
		prevLayer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2 = image.createGraphics();
		gPrev = prevLayer.createGraphics();
		transparentColor = new Color(102, 102, 102);
		scale = 10;
		DefaultToolListener mouseDragListener = new DefaultToolListener();
		addMouseListener(mouseDragListener);
		addMouseMotionListener(mouseDragListener);
		addMouseWheelListener(mouseDragListener);
		addKeyListener(mouseDragListener);
		setFocusable(true);
		requestFocus();
		requestFocusInWindow();
	}

	private void scaleUp(Point p) {
		Point p2 = convertToCanvasCoord(p);
		scale += Math.round((scale / 5) + 1);
		offsetX += p2.x;
		offsetY -= p2.y;
		repaint();
		canvas.updateTitle();
	}

	private void scaleDown(Point p) {
		Point p2 = convertToCanvasCoord(p);
		if (scale - (Math.round((scale / 5) + 1)) > 0) {
			scale -= Math.round((scale / 5) + 1);
		}
		offsetX -= p2.x;
		offsetY -= p2.y;
		repaint();
		canvas.updateTitle();
	}

	private void updateSettings() {
		fgColor = ColorReference.getFgColor();
		bgColor = ColorReference.getBgColor();
		transPaint = CanvasSettings.getTransPaint();
		offsetMoveAmt = CanvasSettings.getOffsetMoveAmt();
	}

	public Point convertToCanvasCoord(Point point) {
		point = centerImageCoord(point);

		int x = (point.x + offsetX) / scale;
		int y = (point.y + offsetY) / scale;

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
		Point point = centerImageCoord(new Point(offsetX, offsetY));
		g.setColor(transparentColor);
		g.fillRect(-point.x, -point.y, image.getWidth() * scale, image.getHeight() * scale);
		g.setPaint(transPaint);
		g.fillRect(-point.x, -point.y, image.getWidth() * scale, image.getHeight() * scale);
		g.drawImage(image.getImage(), -point.x, -point.y, image.getWidth() * scale, image.getHeight() * scale, this);
		g.drawImage(prevLayer, -point.x, -point.y, image.getWidth() * scale, image.getHeight() * scale, this);
		updateSettings();
	}

	public void clear() {
		image.clearImage();
		g2 = image.createGraphics();
		updateSettings();
		repaint();

	}

	public void setTransparencyColor(Color newColor) {
		transparentColor = newColor;
		repaint();
	}

	public Color getTransparencyColor() {
		return transparentColor;
	}

	public void resetPrevLayer() {
		prevLayer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		gPrev = prevLayer.createGraphics();
		updateSettings();
		repaint();

	}

	public BufferedImage getImage() {
		return image.getImage();
	}

	public int getZoom() {
		return scale;
	}

	public void setZoom(int scale) {
		this.scale = scale;
		repaint();
		canvas.updateTitle();
	}

	public void centerCanvas() {
		offsetX = 0;
		offsetY = 0;
		repaint();
	}

	private class DefaultToolListener extends MouseMotionAdapter
			implements MouseListener, MouseWheelListener, KeyListener, FocusListener {

		private boolean shift;

		@Override
		public void mousePressed(MouseEvent e) {
			offsetPoint = e.getPoint();
			currentPoint = convertToCanvasCoord(e.getPoint());
			primaryPoint = currentPoint;
			canvas.updateMousePos(currentPoint.x, currentPoint.y);

			if (e.getButton() == MouseEvent.BUTTON3) {
				int newColorInt = image.getImage().getRGB(currentPoint.x, currentPoint.y);
				Color newColor = new Color(newColorInt);
				ColorReference.setFgColor(newColor);
				if (newColor != null) {
					updateSettings();
					paintColor = fgColor;
					gPrev.setPaint(paintColor);
					g2.setPaint(paintColor);
					gPrev.drawLine(currentPoint.x, currentPoint.y, currentPoint.x, currentPoint.y);
					mw.updateColorButtons();
					repaint();
				}
				return;
			}

			if (e.getButton() == MouseEvent.BUTTON2) {
				return;
			}

			updateSettings();
			if (!shift) {
				if (g2 != null) {
					g2.setPaint(paintColor);
					g2.drawLine(currentPoint.x, currentPoint.y, currentPoint.x, currentPoint.y);
				}
			}
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			currentPoint = convertToCanvasCoord(e.getPoint());
			canvas.updateMousePos(currentPoint.x, currentPoint.y);
			updateSettings();
			if (SwingUtilities.isMiddleMouseButton(e)) {
				offsetX += (offsetPoint.x - e.getX());
				offsetY += (offsetPoint.y - e.getY());
				offsetPoint = e.getPoint();
				repaint();
				return;
			}
			if (shift) {
				resetPrevLayer();
				gPrev.setPaint(paintColor);
				gPrev.drawLine(primaryPoint.x, primaryPoint.y, currentPoint.x, currentPoint.y);
				repaint();
			} else {
				g2.setPaint(paintColor);
				g2.drawLine(primaryPoint.x, primaryPoint.y, currentPoint.x, currentPoint.y);
				repaint();
				primaryPoint = currentPoint;
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			currentPoint = convertToCanvasCoord(e.getPoint());
			canvas.updateMousePos(currentPoint.x, currentPoint.y);
			resetPrevLayer();
			updateSettings();
			gPrev.setPaint(paintColor);
			gPrev.drawLine(currentPoint.x, currentPoint.y, currentPoint.x, currentPoint.y);
			repaint();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			updateSettings();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			updateSettings();
			offsetPoint = e.getPoint();
			currentPoint = convertToCanvasCoord(e.getPoint());
			g2.setPaint(paintColor);

			if (shift) {
				g2.drawLine(primaryPoint.x, primaryPoint.y, currentPoint.x, currentPoint.y);
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			currentPoint = convertToCanvasCoord(e.getPoint());
			canvas.updateMousePos(currentPoint.x, currentPoint.y);
			updateSettings();
			paintColor = fgColor;
		}

		@Override
		public void mouseExited(MouseEvent e) {
			updateSettings();
			resetPrevLayer();
			gPrev.setPaint(paintColor);
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			updateSettings();
			if (e.getWheelRotation() < 0) {
				scaleUp(e.getPoint());
				resetPrevLayer();
			} else if (e.getWheelRotation() > 0) {
				scaleDown(e.getPoint());
				resetPrevLayer();
			}

			currentPoint = convertToCanvasCoord(e.getPoint());
			canvas.updateMousePos(currentPoint.x, currentPoint.y);
			resetPrevLayer();
			if (gPrev != null) {
				gPrev.setPaint(paintColor);
				gPrev.drawLine(currentPoint.x, currentPoint.y, currentPoint.x, currentPoint.y);
			}
			repaint();
		}

		@Override
		public void keyTyped(KeyEvent e) {
			updateSettings();
			paintColor = fgColor;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			resetPrevLayer();
			updateSettings();
			switch (e.getKeyCode()) {
			case KeyEvent.VK_SHIFT: {
				updateSettings();
				shift = true;
				gPrev.setPaint(paintColor);
				break;
			}
			case KeyEvent.VK_CONTROL: {
				updateSettings();
				paintColor = bgColor;
				gPrev.setPaint(paintColor);
				break;
			}
			case KeyEvent.VK_LEFT: {
				offsetX -= scale * offsetMoveAmt;
				break;
			}
			case KeyEvent.VK_RIGHT: {
				offsetX += scale * offsetMoveAmt;
				break;
			}
			case KeyEvent.VK_UP: {
				offsetY -= scale * offsetMoveAmt;
				break;
			}
			case KeyEvent.VK_DOWN: {
				offsetY += scale * offsetMoveAmt;
				break;
			}
			default: {
				updateSettings();
				paintColor = fgColor;
				shift = false;
				gPrev.setPaint(paintColor);
				break;
			}
			}
			;
			gPrev.drawLine(currentPoint.x, currentPoint.y, currentPoint.x, currentPoint.y);
			repaint();

		}

		@Override
		public void keyReleased(KeyEvent e) {
			updateSettings();
			paintColor = fgColor;
			gPrev.setPaint(paintColor);
			gPrev.drawLine(currentPoint.x, currentPoint.y, currentPoint.x, currentPoint.y);
			repaint();
			if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				shift = false;
			}
		}

		@Override
		public void focusGained(FocusEvent e) {
			updateSettings();
			paintColor = fgColor;
		}

		@Override
		public void focusLost(FocusEvent e) {
			updateSettings();
			paintColor = fgColor;
		}

	}

}
