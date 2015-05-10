package com.dakkra.pyxleos.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.dakkra.pyxleos.controller.MainController;
import com.dakkra.pyxleos.model.MainModel;
import com.dakkra.pyxleos.modules.Canvas;
import com.dakkra.pyxleos.modules.CopicPalette;
import com.dakkra.pyxleos.modules.TextEdit;
import com.dakkra.pyxleos.specialcomponents.DesktopPainter;
import com.dakkra.pyxleos.specialcomponents.PxDesktopPane;
import com.dakkra.pyxleos.specialcomponents.ToolbarColorBox;

public class MainView {
	public MainController c;
	public MainModel m;

	public void createAndShowGUI() {
		// Use nimbus
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try {
					// Customize Nimbus
					customizeNimbus();
					UIManager.setLookAndFeel(info.getClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}

		applicationMainMenu();

		m.mainFrame = new JFrame(m.applicationNameVersion);
		m.mainFrame.setResizable(true);
		m.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.mainFrame.setMinimumSize(new Dimension(800, 600));
		m.mainFrame.setLocationRelativeTo(null);
		m.mainFrame.setJMenuBar(m.menuBar);
		// Icon code
		Image icon;
		InputStream input = getClass().getResourceAsStream("/Icon.png");
		try {
			icon = ImageIO.read(input);
			m.mainFrame.setIconImage(icon);
		} catch (IOException e) {
			// Intentionally ignore exception (because it should never happen)
		}

		m.toolBar = new JToolBar();
		m.fgColor = Color.BLACK;
		m.fgColorBox = new ToolbarColorBox(m.fgColor, m);
		m.toolBar.add(m.fgColorBox);
		m.toolBar.setFloatable(true);
		m.toolBar.setOrientation(JToolBar.NORTH);

		m.mainFrame.add(m.toolBar, BorderLayout.WEST);

		m.mainJDPane = new PxDesktopPane();
		m.mainScrollPane = new JScrollPane(m.mainJDPane);
		m.mainScrollPane.getViewport().setBackground(Color.RED.darker());

		m.mainFrame.add(m.mainScrollPane);
		m.mainFrame.setVisible(true);

	}

	private void applicationMainMenu() {
		m.menuBar = new JMenuBar();

		// file menu
		m.fileMenu = new JMenu(" File ");
		m.fileNew = new JMenuItem("New Project");
		m.fileNew.addActionListener(new NewProjectListener());
		m.fileNew.setMnemonic(KeyEvent.VK_N);
		m.fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		m.fileOpen = new JMenuItem("Open Project");
		m.fileOpen.addActionListener(new OpenListener());
		m.fileOpen.setMnemonic(KeyEvent.VK_O);
		m.fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		m.fileSave = new JMenuItem("Save Project");
		m.fileSave.addActionListener(new SaveListener());
		m.fileSave.setMnemonic(KeyEvent.VK_S);
		m.fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		m.fileQuit = new JMenuItem("Quit " + m.applicationName);
		m.fileQuit.addActionListener(new CloseListener());
		m.fileQuit.setMnemonic(KeyEvent.VK_Q);
		m.fileQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));
		m.fileMenu.add(m.fileNew);
		m.fileMenu.add(m.fileOpen);
		m.fileMenu.add(m.fileSave);
		m.fileMenu.add(m.fileQuit);

		// tools menu
		m.toolsMenu = new JMenu(" Tools ");
		m.toolTextEditor = new JMenuItem("New Text Editor");
		m.toolTextEditor.addActionListener(new ToolTextEditorListener(m));
		m.toolCanvas = new JMenuItem("New Canvas");
		m.toolCanvas.addActionListener(new ToolCanvasListener(m));
		m.toolCopicPalette = new JMenuItem("New Copic Palette");
		m.toolCopicPalette.addActionListener(new CopicPaletteListener(m));
		m.toolsMenu.add(m.toolCanvas);
		m.toolsMenu.add(m.toolCopicPalette);
		m.toolsMenu.add(m.toolTextEditor);

		// about menu
		m.aboutMenu = new JMenu(" About ");
		m.aboutTrello = new JMenuItem("Trello");
		m.aboutTrello.addActionListener(new AboutTrelloListener());
		m.aboutGithub = new JMenuItem("Github");
		m.aboutGithub.addActionListener(new AboutGithubListener());
		m.aboutSourceForge = new JMenuItem("SourceForge");
		m.aboutSourceForge.addActionListener(new AboutSourceForgeListener());
		m.aboutWebsite = new JMenuItem("Website");
		m.aboutWebsite.addActionListener(new AboutWebsiteListener());
		m.aboutAbout = new JMenuItem("About " + m.applicationNameVersion);
		m.aboutAbout.addActionListener(new AboutListener(m));
		m.aboutMenu.add(m.aboutWebsite);
		m.aboutMenu.add(m.aboutTrello);
		m.aboutMenu.add(m.aboutGithub);
		m.aboutMenu.add(m.aboutSourceForge);
		m.aboutMenu.add(m.aboutAbout);

		// add menus to the main menu bar
		m.menuBar.add(m.fileMenu);
		m.menuBar.add(m.toolsMenu);
		m.menuBar.add(m.aboutMenu);
	}

	private void customizeNimbus() {
		// Colors
		// Color bgColor = new Color(64,64,80);
		// Color baseColor = new Color(64,64,80);
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

		UIManager.put("FileChooser.background", Color.WHITE);
		UIManager.put("DesktopPane[Enabled].backgroundPainter",
				new DesktopPainter());
	}

	private static void openURI(URI uri) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) {
			}
		} else {
		}

	}

	// ***!From here down are listeners!***
	private class CloseListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int returnval = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to quit?");

			if (returnval == JOptionPane.OK_OPTION) {
				System.out
						.println("File > Quit: Exitting Application (This is ok)");
				System.exit(0);
			} else {
				return;
			}
		}
	}

	private class AboutListener implements ActionListener {
		MainModel m;

		public AboutListener(MainModel m) {
			this.m = m;
		}

		public void actionPerformed(ActionEvent e) {
			AboutView aboutView = new AboutView();
			aboutView.showAboutView(m);
		}
	}

	private class AboutTrelloListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			URI trelloURI = null;
			try {
				trelloURI = new URI("https://trello.com/b/uhTWzdfO/pyxleos");
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}

			openURI(trelloURI);
		}
	}

	private class AboutGithubListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			URI githubURI = null;
			try {
				githubURI = new URI("https://github.com/Dakkra/PyxleOS");
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}

			openURI(githubURI);
		}
	}

	private class AboutSourceForgeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			URI sourceforgeURI = null;
			try {
				sourceforgeURI = new URI(
						"https://sourceforge.net/projects/pyxleos/");
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}

			openURI(sourceforgeURI);
		}
	}

	private class AboutWebsiteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			URI websiteURI = null;
			try {
				websiteURI = new URI("http://pyxleos.sourceforge.net/");
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}

			openURI(websiteURI);
		}
	}

	private class NewProjectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Will create a project");
			// TODO create an image here
		}
	}

	private class OpenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Will open a project");
			// TODO open an image here
		}
	}

	private class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Will save a project");
			// TODO save an image here
		}
	}

	private class ToolTextEditorListener implements ActionListener {
		MainModel m;

		public ToolTextEditorListener(MainModel m) {
			this.m = m;
		}

		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			TextEdit textEdit = new TextEdit(m);
		}
	}

	private class ToolCanvasListener implements ActionListener {
		MainModel m;

		public ToolCanvasListener(MainModel m) {
			this.m = m;
		}

		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			Canvas canvas = new Canvas(m);
		}
	}

	private class CopicPaletteListener implements ActionListener {
		MainModel m;

		public CopicPaletteListener(MainModel m) {
			this.m = m;
		}

		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			CopicPalette copicPalette = new CopicPalette(m);
		}
	}
}
