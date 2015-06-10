package com.dakkra.pyxleos;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;

import com.dakkra.pyxleos.modules.textedit.TextEdit;
import com.dakkra.pyxleos.ui.MainWindow;
import com.dakkra.pyxleos.ui.UISettings;
import com.dakkra.pyxleos.util.Util;

public class PyxleOS {
	public static void main(String[] args) {
		System.out.println("Initializing PyxleOS");

		UISettings uis = new UISettings();

		MainWindow mw = new MainWindow(uis);

		mw.setUIS();
		mw.cnsUI();

		TextEdit te = new TextEdit(mw);
		InputStream input = PyxleOS.class.getResourceAsStream("/greeting.txt");
		String content = Util.read(input);
		try {
			input.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		te.setText(content);
		te.setSize(new Dimension(620, 512));

		System.out.println("" + te.getSize().getWidth() + "x"
				+ te.getSize().getHeight());

		System.out.println("Ready!");
	}
}
