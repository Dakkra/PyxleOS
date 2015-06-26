package com.dakkra.pyxleos;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.dakkra.pyxleos.modules.canvas.Canvas;
import com.dakkra.pyxleos.modules.textedit.TextEdit;
import com.dakkra.pyxleos.ui.MainWindow;
import com.dakkra.pyxleos.ui.UISettings;
import com.dakkra.pyxleos.util.Util;

public class PyxleOS {
	public static void main(String[] args) {
		System.out.println("Initializing PyxleOS");

		UISettings uis = new UISettings();

		MainWindow mw = new MainWindow(uis);

		mw.initUIS();
		mw.initCS();
		mw.cnsUI();

		BufferedImage img = null;

		try {
			img = ImageIO.read(PyxleOS.class.getResourceAsStream("/sword.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		@SuppressWarnings("unused")
		Canvas canvas = new Canvas(mw, img);

		TextEdit te = new TextEdit(mw);
		InputStream input = PyxleOS.class.getResourceAsStream("/greeting.txt");
		String greetingMessage = Util.read(input);
		try {
			input.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		te.setText(greetingMessage);
		te.setSize(new Dimension(620, 512));

		System.out.println("Ready!");
	}
}
