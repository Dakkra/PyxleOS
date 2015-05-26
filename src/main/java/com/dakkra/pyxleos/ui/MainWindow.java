package com.dakkra.pyxleos.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.dakkra.pyxleos.util.Util;

public class MainWindow {
	// Create class fields
	private JDesktopPane jdp;

	private JFrame mFrame;

	// Constructor
	public MainWindow() {

	}

	public void cnsUI() {
		// Create and show UI

		// Use Nimbus
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					customizeNimbus();
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			System.out
					.println("Nimbus look and feel not found (MainView.java)");
			e.printStackTrace();
		}
		// Initialize Main Frame
		mFrame = new JFrame("PyxleOS::E");
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setMinimumSize(new Dimension(950, 600));
		mFrame.setLocationRelativeTo(null);
		initializeMainMenu();

		jdp = new JDesktopPane();

		JToolBar toolBar = new JToolBar();
		toolBar.add(new JButton("Button"));
		toolBar.add(new JButton("Button"));
		toolBar.add(new JButton("Button"));
		toolBar.setOrientation(JToolBar.NORTH);

		mFrame.add(jdp, BorderLayout.CENTER);
		mFrame.add(toolBar, BorderLayout.WEST);

		JInternalFrame ifrrame = new JInternalFrame("test", true, true, true,
				true);
		ifrrame.setBounds(0, 0, 400, 400);
		ifrrame.requestFocusInWindow();
		ifrrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		ifrrame.setVisible(true);
		addIFrame(ifrrame);

		mFrame.setVisible(true);

	}

	public void addIFrame(JInternalFrame iFrame) {
		jdp.add(iFrame);
		jdp.setComponentZOrder(iFrame, 0);
	}

	private void initializeMainMenu() {

		// Initialize Main Menu
		JMenuBar menuBar = new JMenuBar();
		// File menu
		JMenu fileMenu = new JMenu(" File ");
		JMenuItem fileMQuit = new JMenuItem("Quit");
		fileMQuit.addActionListener(new QuitListener());
		fileMQuit.setMnemonic(KeyEvent.VK_Q);
		fileMQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));
		fileMenu.add(fileMQuit);
		// Tools menu
		JMenu toolsMenu = new JMenu(" Tools ");
		toolsMenu.setEnabled(false);
		// About menu
		JMenu aboutMenu = new JMenu(" About ");
		JMenuItem aboutAbout = new JMenuItem("About");
		aboutAbout.addActionListener(new AboutListener(this));
		aboutMenu.add(aboutAbout);

		// Add menus to bar
		menuBar.add(fileMenu);
		menuBar.add(toolsMenu);
		menuBar.add(aboutMenu);

		mFrame.setJMenuBar(menuBar);
	}

	private void customizeNimbus() {
		Color bgColor = new Color(43, 57, 71);
		Color baseColor = new Color(43, 57, 71);
		Color baseRedColor = new Color(60, 57, 71);

		// General Changes
		UIManager.put("control", bgColor);
		UIManager.put("nimbusBase", baseColor);
		UIManager.put("nimbusOrange", baseColor);
		UIManager.put("nimbusGreen", baseColor);
		UIManager.put("nimbusRed", baseRedColor);
		UIManager.put("nimbusLightBackground", baseColor);
		UIManager.put("text", Color.WHITE);
		UIManager.put("nimbusDisabledText", Color.WHITE);
	}

	// Action Listeners
	private class QuitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int confirm = JOptionPane.showConfirmDialog(null,
					"Are you sure you wish to exit?");
			if (confirm == JOptionPane.OK_OPTION) {
				System.exit(0);
			} else {
				return;
			}
		}
	}

	private class AboutListener implements ActionListener {
		MainWindow mw;

		public AboutListener(MainWindow mw) {
			this.mw = mw;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JInternalFrame aboutFrame = Util.createIFrame("About");
			aboutFrame.setBounds(0, 0, 400, 200);
			aboutFrame
					.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
			aboutFrame.toFront();
			try {
				aboutFrame.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}

			JTextArea textArea = new JTextArea(
					"PyxleOS::E\n(c) By Chris Soderquist\n http://pyxleos.sourceforge.net \n \nPyxleOS is under a GNU GENERAL PUBLIC LICENSE V2.0\n https://www.gnu.org/licenses/gpl-2.0.html \n\nPyxle(pixel) OS(open source)\n\nCheck out http://dakkra.com");
			textArea.setEditable(false);

			aboutFrame.add(textArea);

			aboutFrame.setVisible(true);
			mw.addIFrame(aboutFrame);
		}
	}
}
