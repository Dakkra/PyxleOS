package com.dakkra.pyxleos.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.dakkra.pyxleos.ColorReference;
import com.dakkra.pyxleos.PyxleOS;
import com.dakkra.pyxleos.modules.canvas.Canvas;
import com.dakkra.pyxleos.modules.canvas.CanvasSettings;
import com.dakkra.pyxleos.modules.canvas.CanvasSettingsView;
import com.dakkra.pyxleos.modules.textedit.TextEdit;
import com.dakkra.pyxleos.ui.components.BGColorButton;
import com.dakkra.pyxleos.ui.components.FGColorButton;
import com.dakkra.pyxleos.ui.painters.DesktopPainter;
import com.dakkra.pyxleos.util.Util;

public class MainWindow {
	// Create class fields

	private JDesktopPane jdp;

	private JFrame mFrame;

	private UISettings uis;

	private UISettingsView uisv;

	private CanvasSettingsView csv;

	private Properties appearanceProperties;

	private Properties canvasProperties;

	private File appearancePropertiesFile;

	private File canvasPropertiesFile;

	private File appDir;

	private boolean firstRun;

	private FGColorButton fgColorButton;

	private BGColorButton bgColorButton;

	// Constructor
	public MainWindow(UISettings uis) {
		this.uis = uis;
		// If settings don't exist yet, make them
		appDir = new File(System.getProperty("user.home"), "PyxleOS");
		appearancePropertiesFile = new File(appDir, "colors.pyxos");
		canvasPropertiesFile = new File(appDir, "canvas.pyxos");
		if (fileCheck()) {
			loadColorSettings();
			loadCanvasSettings();
			firstRun = false;
		} else {
			firstRun = true;
			if (!appDir.exists()) {
				if (appDir.mkdirs()) {
					System.out.println("application directory created");
				} else {
					System.out.println("failed to create application directory");
				}
			}
			if (!appearancePropertiesFile.exists()) {
				saveColorSettings();
			}
			if (!canvasPropertiesFile.exists()) {
				saveCanvasSettings();
			}
			loadColorSettings();
			loadCanvasSettings();
		}
	}

	private boolean fileCheck() {
		if (appDir.exists() && appearancePropertiesFile.exists() && canvasPropertiesFile.exists()) {
			return true;
		} else {
			return false;
		}
	}

	public void initUIS() {
		uisv = new UISettingsView(this, uis);
	}

	public void initCS() {
		csv = new CanvasSettingsView(this);
	}

	public void cnsUI() {
		// Create and show UI

		// Use Nimbus
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					updateNimbus();
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Nimbus look and feel not found (MainView.java)");
			e.printStackTrace();
		}
		// Initialize Main Frame
		mFrame = new JFrame("PyxleOS");
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

		mFrame.add(jdp, BorderLayout.CENTER);

		JToolBar toolBar = new JToolBar("ToolBar");

		fgColorButton = new FGColorButton();
		bgColorButton = new BGColorButton();

		toolBar.add(fgColorButton);
		toolBar.add(bgColorButton);
		JButton switchColorsButton = new JButton("Switch");
		switchColorsButton.setFocusable(false);
		switchColorsButton.addActionListener(new SwitchColorsEar());
		toolBar.add(switchColorsButton);

		mFrame.add(toolBar, BorderLayout.PAGE_START);

		updateGUI();

		mFrame.setVisible(true);

	}

	public Rectangle getDesktopPaneBounds() {
		Rectangle bounds = jdp.getBounds();
		return bounds;
	}

	public void addIFrame(JInternalFrame iFrame) {
		Rectangle bounds = getDesktopPaneBounds();
		Point center = new Point((bounds.width / 2) - (iFrame.getWidth() / 2),
				(bounds.height / 2) - (iFrame.getHeight() / 2));
		iFrame.setLocation(center);
		iFrame.setVisible(true);
		jdp.add(iFrame);
		jdp.setComponentZOrder(iFrame, 0);
		jdp.setSelectedFrame(iFrame);
	}

	public void bringToFront(JInternalFrame iFrame) {
		jdp.setComponentZOrder(iFrame, 0);
		jdp.setSelectedFrame(iFrame);
	}

	public void updateGUI() {
		updateNimbus();

		for (Component component : mFrame.getComponents()) {
			component.repaint();
		}

		for (Window window : Window.getWindows()) {
			SwingUtilities.updateComponentTreeUI(window);
		}
		updateColorButtons();
	}

	private void initializeMainMenu() {

		// Initialize Main Menu
		JMenuBar menuBar = new JMenuBar();
		// File menu
		JMenu fileMenu = new JMenu(" File ");
		JMenuItem fileMQuit = new JMenuItem("Quit");
		fileMQuit.addActionListener(new QuitListener());
		fileMQuit.setMnemonic(KeyEvent.VK_Q);
		fileMQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		JMenuItem fileNew = new JMenuItem("New Canvas");
		fileNew.addActionListener(new NewCanvasEar(this));
		fileNew.setMnemonic(KeyEvent.VK_N);
		fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		fileMenu.add(fileNew);
		JMenuItem fileOpen = new JMenuItem("Load Image");
		fileOpen.addActionListener(new OpenCanvasEar(this));
		fileOpen.setMnemonic(KeyEvent.VK_O);
		fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

		fileMenu.add(fileNew);
		fileMenu.add(fileOpen);
		fileMenu.addSeparator();
		fileMenu.add(fileMQuit);
		// Tools menu
		JMenu toolsMenu = new JMenu(" Tools ");
		JMenuItem toolsMTE = new JMenuItem("New TextEdit");
		toolsMTE.addActionListener(new TextEditListener(this));
		toolsMTE.setMnemonic(KeyEvent.VK_T);
		toolsMTE.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
		toolsMenu.add(toolsMTE);
		// Options menu
		JMenu optionsMenu = new JMenu(" Options ");
		JMenuItem optionColor = new JMenuItem("Appearance");
		optionColor.addActionListener(new CustomizeUIEar());
		optionColor.setMnemonic(KeyEvent.VK_V);
		optionColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
		optionsMenu.add(optionColor);
		JMenuItem optionCanvas = new JMenuItem("Canvas Settings");
		optionCanvas.addActionListener(new CanvasEar());
		optionCanvas.setMnemonic(KeyEvent.VK_C);
		optionCanvas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		optionsMenu.add(optionCanvas);

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

	public void updateNimbus() {
		Color bgColor = uis.getbgColor();
		Color baseColor = uis.getbaseColor();
		Color baseRedColor = uis.getselectionRedColor();
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
		UIManager.put("nimbusFocus", baseColor.brighter());
		UIManager.put("nimbusSelectionBackground", baseRedColor);

		DesktopPainter dp = new DesktopPainter(uis);

		UIManager.put("DesktopPane[Enabled].backgroundPainter", dp);
	}

	private void loadColorSettings() {
		System.out.println("Loading color settings");

		try {

			InputStream iStream = new FileInputStream(appearancePropertiesFile);

			appearanceProperties = new Properties();
			appearanceProperties.load(iStream);

			uis.setbgColor(new Color(Integer.parseInt(appearanceProperties.getProperty("bgColor"))));
			uis.setbaseColor(new Color(Integer.parseInt(appearanceProperties.getProperty("baseColor"))));
			uis.setselectionRedColor(new Color(Integer.parseInt(appearanceProperties.getProperty("baseRedColor"))));
			uis.settextColor(new Color(Integer.parseInt(appearanceProperties.getProperty("textColor"))));

			iStream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("Error while loading color settings");
		}
	}

	public void saveColorSettings() {
		System.out.println("Saving color settings");

		appearanceProperties = new Properties();

		try {
			OutputStream oStream = new FileOutputStream(appearancePropertiesFile);

			appearanceProperties.setProperty("bgColor", "" + uis.getbgColor().getRGB());
			appearanceProperties.setProperty("baseColor", "" + uis.getbaseColor().getRGB());
			appearanceProperties.setProperty("baseRedColor", "" + uis.getselectionRedColor().getRGB());
			appearanceProperties.setProperty("textColor", "" + uis.gettextColor().getRGB());

			appearanceProperties.store(oStream, "Colors");

			oStream.close();

			if (!firstRun) {
				JOptionPane.showMessageDialog(mFrame, "Theme saved! Please restart for full effect");
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(mFrame, "Error while saving theme");
		}
	}

	public void loadCanvasSettings() {
		System.out.println("Loading Canvas Settings");

		try {

			InputStream iStream = new FileInputStream(canvasPropertiesFile);

			canvasProperties = new Properties();
			canvasProperties.load(iStream);

			CanvasSettings.setTransparencyPrimaryColor(
					new Color(Integer.parseInt(canvasProperties.getProperty("PrimaryColor"))));
			CanvasSettings.setTransparencySecondaryColor(
					new Color(Integer.parseInt(canvasProperties.getProperty("SecondaryColor"))));
			CanvasSettings.setBlockSize(Integer.parseInt(canvasProperties.getProperty("BlockSize")));
			System.out.println("(Load) Block Size: " + CanvasSettings.getBlockSize());

			iStream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("Error while loading canvas settings");
		}
	}

	public void saveCanvasSettings() {
		System.out.println("Saving Canvas Settings");

		canvasProperties = new Properties();

		try {
			OutputStream oStream = new FileOutputStream(canvasPropertiesFile);

			canvasProperties.setProperty("PrimaryColor", "" + CanvasSettings.getTransparencyPrimaryColor().getRGB());
			canvasProperties.setProperty("SecondaryColor",
					"" + CanvasSettings.getTransparencySecondaryColor().getRGB());
			canvasProperties.setProperty("BlockSize", "" + CanvasSettings.getBlockSize());

			System.out.println("(Save) Block Size: " + CanvasSettings.getBlockSize());

			canvasProperties.store(oStream, "Canvas");
			oStream.close();

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(mFrame, "Error while saving canvas settings");
		}
	}

	public void updateColorButtons() {
		fgColorButton.updateButton();
		bgColorButton.updateButton();
	}

	public Color getSelectionColor() {
		return uis.getselectionRedColor();
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

			aboutFrame.setSize(500, 400);

			aboutFrame.setSize(aboutFrame.getWidth() + 10, aboutFrame.getHeight() + 10);

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

	private class NewCanvasEar implements ActionListener {
		MainWindow mw;

		public NewCanvasEar(MainWindow mw) {
			this.mw = mw;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			Canvas canv = new Canvas(mw);
		}
	}

	private class OpenCanvasEar implements ActionListener {
		MainWindow mw;

		public OpenCanvasEar(MainWindow mw) {
			this.mw = mw;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser ofc = new JFileChooser();
			int returnVal = ofc.showOpenDialog(mFrame);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				BufferedImage img = null;
				try {
					img = ImageIO.read(ofc.getSelectedFile());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if (img != null) {
					@SuppressWarnings("unused")
					Canvas canvas = new Canvas(mw, img, ofc.getSelectedFile());
				} else {
					JOptionPane.showMessageDialog(mFrame, "Please select a png or jpg");
				}
			}
		}

	}

	private class CustomizeUIEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			uisv.makeUI();
		}
	}

	private class CanvasEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			csv.cnsGUI();
		}

	}

	private class SwitchColorsEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Color oldFG = ColorReference.getFgColor();
			Color oldBG = ColorReference.getBgColor();

			ColorReference.setFgColor(oldBG);
			ColorReference.setBgColor(oldFG);

			updateColorButtons();
		}

	}
}
