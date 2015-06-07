package com.dakkra.pyxleos.modules;

import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.dakkra.pyxleos.util.Util;

public class Module {
	protected JInternalFrame frame;
	protected JMenuBar menuBar;
	protected JMenu fileMenu;

	public Module() {
		frame = Util.createIFrame("Module");

		menuBar = new JMenuBar();

		fileMenu = new JMenu(" File ");

		menuBar.add(fileMenu);

		frame.setJMenuBar(menuBar);
	}
}
