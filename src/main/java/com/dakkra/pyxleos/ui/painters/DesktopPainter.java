package com.dakkra.pyxleos.ui.painters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JDesktopPane;
import javax.swing.Painter;

import com.dakkra.pyxleos.ui.UISettings;

public class DesktopPainter implements Painter<JDesktopPane> {
	private Image image;
	private UISettings uis;

	public DesktopPainter(UISettings uis) {
		this.uis = uis;
		image = uis.getbgImage();
		//System.out.println("DesktopPainter.java bg painter init");
	}

	@Override
	public void paint(Graphics2D g, JDesktopPane object, int width, int height) {
		// TODO find out why nimbus doesn't render this painter
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(image, 0, 0, width, height, null);
		Color backColor = new Color(uis.getbgColor().getRed(), uis.getbgColor().getGreen(), uis.getbgColor().getBlue(),
				100);
		g.setColor(backColor);
		g.fillRect(0, 0, width, height);

	}
}
