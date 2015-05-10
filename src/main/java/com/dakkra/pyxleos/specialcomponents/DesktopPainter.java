package com.dakkra.pyxleos.specialcomponents;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JDesktopPane;
import javax.swing.Painter;
import javax.swing.UIManager;

public class DesktopPainter implements Painter<JDesktopPane> {

	@Override
	public void paint(Graphics2D g, JDesktopPane desktop, int width, int height) {
		Rectangle bounds = desktop.getBounds();
		g.setColor(UIManager.getDefaults().getColor("desktop"));
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}

}