package com.dakkra.pyxleos.modules;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.dakkra.pyxleos.util.Util;

public class Module {
	protected JInternalFrame frame;

	public Module(){
		frame = Util.createIFrame("Module");
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu(" File ");
		
		JMenuItem fileQuit = new JMenuItem("Quit");
		
		fileMenu.add(fileQuit);
		menuBar.add(fileMenu);
		
		frame.setJMenuBar(menuBar);
	}
	
	protected class QuitEar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Util.exitIFrame(frame);
		}
	}
}
