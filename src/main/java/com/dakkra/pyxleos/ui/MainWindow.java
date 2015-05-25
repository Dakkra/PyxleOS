package com.dakkra.pyxleos.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainWindow {
	// Create class fields
	private JDesktopPane jdp;

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
		menuBar.add(fileMenu);
		// Initialize Main Frame
		JFrame mFrame = new JFrame("PyxleOS::E");
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setMinimumSize(new Dimension(950, 600));
		mFrame.setLocationRelativeTo(null);
		mFrame.setJMenuBar(menuBar);

		jdp = new JDesktopPane();

		mFrame.add(jdp, BorderLayout.CENTER);

		mFrame.setVisible(true);

	}

	public void addIFrame(JInternalFrame iFrame) {
		jdp.add(iFrame);
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
}
