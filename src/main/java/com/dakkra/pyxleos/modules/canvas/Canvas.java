package com.dakkra.pyxleos.modules.canvas;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

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

		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBackground(Color.DARK_GRAY);

		DrawPane drawPane = new DrawPane();

		JScrollPane pane = new JScrollPane(drawPane);

		container.add(pane, BorderLayout.CENTER);

		frame.add(container);

		mw.addIFrame(frame);
	}

}
