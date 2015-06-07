package com.dakkra.pyxleos.modules.canvas;

import com.dakkra.pyxleos.modules.Module;
import com.dakkra.pyxleos.ui.MainWindow;

public class Canvas extends Module {

	private MainWindow mw;

	public Canvas(MainWindow mw) {
		this.mw = mw;
		makeUI();
	}

	private void makeUI() {
		frame.setTitle("Canvas");

		fileMenu.add(fileExit);

		mw.addIFrame(frame);
	}

}
