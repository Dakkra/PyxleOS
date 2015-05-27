package com.dakkra.pyxleos.ui.painters;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.Painter;

import com.dakkra.pyxleos.PyxleOS;

public class DesktopPainter implements Painter<JDesktopPane> {
    private Image image;

    public DesktopPainter() {
        try {
            image = ImageIO.read(PyxleOS.class.getResource("/PyxleOS-Default.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
