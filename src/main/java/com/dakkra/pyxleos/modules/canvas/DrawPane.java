package com.dakkra.pyxleos.modules.canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import com.dakkra.pyxleos.ColorReference;
import com.dakkra.pyxleos.PyxleOS;
import com.dakkra.pyxleos.ui.MainWindow;

public class DrawPane extends JComponent {
	private static final long serialVersionUID = 6748629663390647156L;

	private MainWindow mw;

	private Canvas canvas;

	private Color paintColor;

	private BufferedImage image;

	private BufferedImage prevLayer;

	private BufferedImage tileImg;

	private Image tileLoad;

	private Graphics2D g2;

	private Graphics2D gPrev;

	private Point currentPoint, primaryPoint;

	private int scale;

	private Color transparentColor;

	private Color fgColor = ColorReference.getFgColor();

	private Color bgColor = ColorReference.getBgColor();

	private int width;

	private int height;

	public DrawPane(MainWindow mw, Canvas canvas, Dimension d) {
		this.mw = mw;

		this.canvas = canvas;

		width = d.width;

		height = d.height;

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		prevLayer = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);

		try {
			tileLoad = ImageIO.read(PyxleOS.class
					.getResourceAsStream("/tile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		tileImg = new BufferedImage(tileLoad.getWidth(this),
				tileLoad.getHeight(this), BufferedImage.TYPE_INT_ARGB);
		Graphics2D tileG = tileImg.createGraphics();
		tileG.drawImage(tileLoad, 0, 0, this);
		tileG.dispose();

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

		image = oimage;

		prevLayer = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);

		try {
			tileLoad = ImageIO.read(PyxleOS.class
					.getResourceAsStream("/tile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		tileImg = new BufferedImage(tileLoad.getWidth(this),
				tileLoad.getHeight(this), BufferedImage.TYPE_INT_ARGB);
		Graphics2D tileG = tileImg.createGraphics();
		tileG.drawImage(tileLoad, 0, 0, this);
		tileG.dispose();

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

	private void scaleUp() {
		scale += Math.round((scale / 5) + 1);
		repaint();
		canvas.updateTitle();
	}

	private void scaleDown() {
		if (scale - (Math.round((scale / 5) + 1)) > 0) {
			scale -= Math.round((scale / 5) + 1);
		}
		repaint();
		canvas.updateTitle();
	}

	private void updateColors() {
		fgColor = ColorReference.getFgColor();
		bgColor = ColorReference.getBgColor();
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
		TexturePaint tilePaint = new TexturePaint(tileImg, new Rectangle(
				tileImg.getWidth(), tileImg.getHeight()));
		g.setColor(transparentColor);
		g.fillRect(-point.x, -point.y, image.getWidth() * scale,
				image.getHeight() * scale);
		g.setPaint(tilePaint);
		g.fillRect(-point.x, -point.y, image.getWidth() * scale,
				image.getHeight() * scale);
		g.drawImage(image, -point.x, -point.y, image.getWidth() * scale,
				image.getHeight() * scale, this);
		g.drawImage(prevLayer, -point.x, -point.y, image.getWidth() * scale,
				image.getHeight() * scale, this);
		updateColors();
	}

	public void clear() {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		g2 = image.createGraphics();

		updateColors();

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
		prevLayer = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);

		gPrev = prevLayer.createGraphics();

		updateColors();

		repaint();

	}

	public BufferedImage getImage() {
		return image;
	}

	public int getZoom() {
		return scale;
	}

	public void setZoom(int scale) {
		this.scale = scale;
		repaint();
		canvas.updateTitle();
	}

	private class DefaultToolListener extends MouseMotionAdapter implements
			MouseListener, MouseWheelListener, KeyListener, FocusListener {

		private boolean shift;

		@Override
		public void mousePressed(MouseEvent e) {
			currentPoint = convertToCanvasCoord(e.getPoint());
			primaryPoint = currentPoint;
			canvas.updateMousePos(currentPoint.x, currentPoint.y);

			if (e.getButton() == MouseEvent.BUTTON3) {
				int newColorInt = image.getRGB(currentPoint.x, currentPoint.y);
				Color newColor = new Color(newColorInt);
				ColorReference.setFgColor(newColor);
				if (newColor != null) {
					updateColors();
					paintColor = fgColor;
					gPrev.setPaint(paintColor);
					g2.setPaint(paintColor);
					gPrev.drawLine(currentPoint.x, currentPoint.y,
							currentPoint.x, currentPoint.y);
					mw.updateColorButtons();
					repaint();
				}
				return;
			}

			updateColors();
			if (!shift) {
				if (g2 != null) {
					g2.setPaint(paintColor);
					g2.drawLine(currentPoint.x, currentPoint.y, currentPoint.x,
							currentPoint.y);
				}
			}
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			currentPoint = convertToCanvasCoord(e.getPoint());
			canvas.updateMousePos(currentPoint.x, currentPoint.y);
			updateColors();
			if (shift) {
				resetPrevLayer();
				gPrev.setPaint(paintColor);
				gPrev.drawLine(primaryPoint.x, primaryPoint.y, currentPoint.x,
						currentPoint.y);
				repaint();
			} else {
				g2.setPaint(paintColor);
				g2.drawLine(primaryPoint.x, primaryPoint.y, currentPoint.x,
						currentPoint.y);
				repaint();
				primaryPoint = currentPoint;
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			currentPoint = convertToCanvasCoord(e.getPoint());
			canvas.updateMousePos(currentPoint.x, currentPoint.y);
			resetPrevLayer();
			updateColors();
			gPrev.setPaint(paintColor);
			gPrev.drawLine(currentPoint.x, currentPoint.y, currentPoint.x,
					currentPoint.y);
			repaint();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			updateColors();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			updateColors();
			currentPoint = convertToCanvasCoord(e.getPoint());
			g2.setPaint(paintColor);
			if (shift) {
				g2.drawLine(primaryPoint.x, primaryPoint.y, currentPoint.x,
						currentPoint.y);
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			currentPoint = convertToCanvasCoord(e.getPoint());
			canvas.updateMousePos(currentPoint.x, currentPoint.y);
			updateColors();
			paintColor = fgColor;
		}

		@Override
		public void mouseExited(MouseEvent e) {
			updateColors();
			resetPrevLayer();
			gPrev.setPaint(paintColor);
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			updateColors();
			if (e.getWheelRotation() < 0) {
				scaleUp();
				resetPrevLayer();
			} else if (e.getWheelRotation() > 0) {
				scaleDown();
				resetPrevLayer();
			}

			currentPoint = convertToCanvasCoord(e.getPoint());
			canvas.updateMousePos(currentPoint.x, currentPoint.y);
			resetPrevLayer();
			if (gPrev != null) {
				gPrev.setPaint(paintColor);
				gPrev.drawLine(currentPoint.x, currentPoint.y, currentPoint.x,
						currentPoint.y);
			}
			repaint();
		}

		@Override
		public void keyTyped(KeyEvent e) {
			updateColors();
			paintColor = fgColor;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			resetPrevLayer();
			updateColors();
			switch (e.getKeyCode()) {
			case KeyEvent.VK_SHIFT: {
				updateColors();
				shift = true;
				gPrev.setPaint(paintColor);
				break;
			}
			case KeyEvent.VK_CONTROL: {
				updateColors();
				paintColor = bgColor;
				gPrev.setPaint(paintColor);
				break;
			}
			default: {
				updateColors();
				paintColor = fgColor;
				shift = false;
				gPrev.setPaint(paintColor);
				break;
			}
			}
			;
			gPrev.drawLine(currentPoint.x, currentPoint.y, currentPoint.x,
					currentPoint.y);
			repaint();

		}

		@Override
		public void keyReleased(KeyEvent e) {
			updateColors();
			paintColor = fgColor;
			gPrev.setPaint(paintColor);
			gPrev.drawLine(currentPoint.x, currentPoint.y, currentPoint.x,
					currentPoint.y);
			repaint();
			if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				shift = false;
			}
		}

		@Override
		public void focusGained(FocusEvent e) {
			updateColors();
			paintColor = fgColor;
		}

		@Override
		public void focusLost(FocusEvent e) {
			updateColors();
			paintColor = fgColor;
		}

	}

}
