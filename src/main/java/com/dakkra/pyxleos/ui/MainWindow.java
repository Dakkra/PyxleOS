package com.dakkra.pyxleos.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.dakkra.pyxleos.PyxleOS;
import com.dakkra.pyxleos.modules.textedit.TextEdit;
import com.dakkra.pyxleos.ui.painters.DesktopPainter;
import com.dakkra.pyxleos.util.Util;

public class MainWindow {
	// Create class fields
	private JDesktopPane jdp;

	private JFrame mFrame;
	
	private UISettings uis;

	// Constructor
	public MainWindow() {

	}
	
	public void setUIS(UISettings uis){
		this.uis = uis;
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
		Image icon;
		InputStream input = getClass().getResourceAsStream("/Icon.png");
		try {
			icon = ImageIO.read(input);
			mFrame.setIconImage(icon);
		} catch (IOException e) {
			// Intentionally ignore exception (because it should never happen)
		}

		initializeMainMenu();

		// Initialize jdp
		jdp = new JDesktopPane();

		JToolBar toolBar = new JToolBar();
		toolBar.add(new JButton("Color"));

		mFrame.add(jdp, BorderLayout.CENTER);
		mFrame.add(toolBar, BorderLayout.NORTH);

		mFrame.setVisible(true);

	}

	public void addIFrame(JInternalFrame iFrame) {
		Rectangle bounds = jdp.getBounds();
		Point center = new Point((bounds.width / 2) - (iFrame.getWidth() / 2),
				(bounds.height / 2) - (iFrame.getHeight() / 2));
		iFrame.setLocation(center);
		jdp.add(iFrame);
		jdp.setComponentZOrder(iFrame, 0);
		jdp.setSelectedFrame(iFrame);
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
		JMenuItem toolsMTE = new JMenuItem("TextEdit");
		toolsMTE.addActionListener(new TextEditListener(this));
		toolsMTE.setMnemonic(KeyEvent.VK_T);
		toolsMTE.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
				ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
		toolsMenu.add(toolsMTE);
		// Options menu
		JMenu optionsMenu = new JMenu(" Options ");
		JMenuItem optionColor = new JMenuItem(" UI Customization ");
		optionColor.setMnemonic(KeyEvent.VK_V);
		optionColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.ALT_MASK));
		optionsMenu.add(optionColor);
		// About menu
		JMenu aboutMenu = new JMenu(" About ");
		JMenuItem aboutAbout = new JMenuItem("About");
		aboutAbout.addActionListener(new AboutListener(this));
		aboutMenu.add(aboutAbout);

		// Add menus to bar
		menuBar.add(fileMenu);
		menuBar.add(toolsMenu);
		menuBar.add(optionsMenu);
		menuBar.add(aboutMenu);

		mFrame.setJMenuBar(menuBar);
	}

	private void customizeNimbus() {
		Color bgColor = uis.getbgColor();
		Color baseColor = uis.getbaseColor();
		Color baseRedColor = uis.getbaseRedColor();
		Color textColor = uis.gettextColor();

		// General Changes
		UIManager.put("control", bgColor);
		UIManager.put("nimbusBase", baseColor);
		UIManager.put("nimbusOrange", baseColor);
		UIManager.put("nimbusGreen", baseColor);
		UIManager.put("nimbusRed", baseRedColor);
		UIManager.put("nimbusLightBackground", baseColor);
		UIManager.put("text", textColor);
		UIManager.put("nimbusDisabledText", textColor);

		UIManager.put("DesktopPane[Enabled].backgroundPainter",
				new DesktopPainter(uis));
	}

	// Action Listeners
	private class QuitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Util.exitApp();
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

			JTextArea textArea = new JTextArea("");
			textArea.setEditable(false);

			InputStream input = PyxleOS.class.getResourceAsStream("/about.txt");
			String content = Util.read(input);
			try {
				input.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			textArea.setText(content);

			JScrollPane textPane = new JScrollPane(textArea);

			aboutFrame.add(textPane);

			mw.addIFrame(aboutFrame);
		}
	}

	private class TextEditListener implements ActionListener {
		MainWindow mw;

		public TextEditListener(MainWindow mw) {
			this.mw = mw;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			TextEdit te = new TextEdit(mw);
		}
	}
}
