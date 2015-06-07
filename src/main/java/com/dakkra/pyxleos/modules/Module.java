package com.dakkra.pyxleos.modules;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.dakkra.pyxleos.util.Util;
import com.sun.glass.events.KeyEvent;

public class Module {
	protected JInternalFrame frame;
	protected JMenuBar menuBar;
	protected JMenu fileMenu;
	protected JMenuItem fileExit;

	public Module() {
		frame = Util.createIFrame("Module");

		menuBar = new JMenuBar();

		fileMenu = new JMenu(" File ");

		fileExit = new JMenuItem("Quit");
		fileExit.addActionListener(new ExitEar());
		fileExit.setMnemonic(KeyEvent.VK_Q);
		fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));

		menuBar.add(fileMenu);

		frame.setJMenuBar(menuBar);
	}

	protected class ExitEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Util.exitIFrame(frame);
		}

	}
}
