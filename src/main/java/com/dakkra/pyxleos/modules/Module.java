package com.dakkra.pyxleos.modules;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.dakkra.pyxleos.ui.MainWindow;
import com.dakkra.pyxleos.util.Util;

public class Module {
	protected MainWindow mw;
	protected JInternalFrame frame;
	protected JMenuBar menuBar;
	protected JMenu fileMenu;
	protected JMenuItem fileExit;

	public Module(MainWindow mw) {
		this.mw = mw;

		frame = Util.createIFrame("Module");

		frame.addFocusListener(new FrameFocusEar());
		frame.addMouseListener(new FrameMouseListener());

		menuBar = new JMenuBar();

		fileMenu = new JMenu(" File ");

		fileExit = new JMenuItem("Quit");
		fileExit.addActionListener(new ExitEar());
		fileExit.setMnemonic(KeyEvent.VK_Q);
		fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));

		menuBar.add(fileMenu);

		frame.setJMenuBar(menuBar);
	}

	protected class ExitEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Util.exitIFrame(frame);
		}

	}

	protected class FrameFocusEar implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			frame.setEnabled(true);
			mw.bringToFront(frame);
		}

		@Override
		public void focusLost(FocusEvent e) {
			frame.setEnabled(false);
		}

	}

	protected class FrameMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			frame.setEnabled(true);
			mw.bringToFront(frame);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			frame.setEnabled(true);
			mw.bringToFront(frame);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}
}
