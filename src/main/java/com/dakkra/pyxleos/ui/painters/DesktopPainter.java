package com.dakkra.pyxleos.ui.painters;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.Painter;

import com.dakkra.pyxleos.PyxleOS;
import com.dakkra.pyxleos.ui.UISettings;

public class DesktopPainter implements Painter<JDesktopPane> {
    private Image image;

    public DesktopPainter(UISettings uis) {
    	image = uis.getbgImage();
    }

	@Override
	public void paint(Graphics2D g, JDesktopPane object, int width, int height) {
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_PURE);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(image, 0, 0, width, height, null);
		
	}
}
