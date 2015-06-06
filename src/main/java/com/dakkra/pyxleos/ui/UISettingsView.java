package com.dakkra.pyxleos.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import net.miginfocom.swing.MigLayout;

import com.dakkra.pyxleos.ui.components.ColorButton;
import com.dakkra.pyxleos.util.Util;
import com.sun.glass.events.KeyEvent;

public class UISettingsView {
	public UISettings uis;
	private MainWindow mw;
	private JComboBox<String> comboBox;

	public UISettingsView(MainWindow mw, UISettings uis) {
		this.mw = mw;
		this.uis = uis;
	}

	public void makeUI() {

		JInternalFrame frame = Util.createIFrame("Appearance");

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu(" File ");
		JMenuItem fileSave = new JMenuItem("Save");
		fileSave.addActionListener(new SaveButtonEar());
		fileSave.setMnemonic(KeyEvent.VK_S);
		fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		JMenuItem fileQuit = new JMenuItem("Quit");
		fileQuit.addActionListener(new QuitEar(frame));
		fileQuit.setMnemonic(KeyEvent.VK_Q);
		fileQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));
		fileMenu.add(fileSave);
		fileMenu.add(fileQuit);
		menuBar.add(fileMenu);

		frame.setJMenuBar(menuBar);

		MigLayout layout = new MigLayout();

		JPanel mPanel = new JPanel(layout);
		mPanel.setBackground(Color.DARK_GRAY);

		mPanel.add(new JLabel("Background:"));
		ColorButton bgColorButton = new ColorButton(uis.getbgColor());
		bgColorButton.addActionListener(new BGColorButtonEar(bgColorButton));
		mPanel.add(bgColorButton, "wrap");

		mPanel.add(new JLabel("Base:"));
		ColorButton baseColorButton = new ColorButton(uis.getbaseColor());
		baseColorButton.addActionListener(new BaseColorButtonEar(
				baseColorButton));
		mPanel.add(baseColorButton, "wrap");

		mPanel.add(new JLabel("Selection:"));
		ColorButton redColorButton = new ColorButton(uis.getselectionRedColor());
		redColorButton.addActionListener(new RedColorButtonEar(redColorButton));
		mPanel.add(redColorButton, "wrap");

		mPanel.add(new JLabel("Text:"));
		ColorButton textColorButton = new ColorButton(uis.gettextColor());
		textColorButton.addActionListener(new TextColorButtonEar(
				textColorButton));
		mPanel.add(textColorButton, "wrap");

		mPanel.add(new JLabel("Built in:"));
		String[] defaults = { "None Selected", "Default", "Crimson", "Sleek",
				"Frozen", "SciFi" };
		comboBox = new JComboBox<String>(defaults);
		comboBox.addItemListener(new SelectionHandler(frame));
		mPanel.add(comboBox, "wrap");

		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveButtonEar());
		mPanel.add(saveButton);

		frame.add(mPanel);
		frame.pack();
		frame.setMaximizable(false);
		frame.setResizable(false);

		mw.addIFrame(frame);
	}

	public void changeSetting() {
		String theme = (String) comboBox.getSelectedItem();

		switch (theme) {
		case "Default": {
			uis.setThemeDefault();
			mw.updateGUI();
			break;
		}
		case "Crimson": {
			uis.setThemeCrimson();
			mw.updateGUI();
			break;
		}
		case "Sleek": {
			uis.setThemeSleek();
			mw.updateGUI();
			break;
		}
		case "SciFi": {
			uis.setThemeSciFi();
			mw.updateGUI();
			break;
		}
		case "Frozen": {
			uis.setThemeFrozen();
			mw.updateGUI();
			break;
		}
		default:
			return;
		}
	}

	private class BGColorButtonEar implements ActionListener {
		ColorButton button;

		public BGColorButtonEar(ColorButton button) {
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Color newColor = JColorChooser.showDialog(null, "Color",
					uis.getbgColor());
			if (newColor != null) {
				uis.setbgColor(newColor);
				mw.updateGUI();
				button.setColor(newColor);
			} else {
				return;
			}
		}

	}

	private class BaseColorButtonEar implements ActionListener {
		ColorButton button;

		public BaseColorButtonEar(ColorButton button) {
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Color newColor = JColorChooser.showDialog(null, "Color",
					uis.getbaseColor());
			if (newColor != null) {
				uis.setbaseColor(newColor);
				mw.updateGUI();
				button.setColor(newColor);
			} else {
				return;
			}
		}

	}

	private class RedColorButtonEar implements ActionListener {
		ColorButton button;

		public RedColorButtonEar(ColorButton button) {
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Color newColor = JColorChooser.showDialog(null, "Color",
					uis.getselectionRedColor());
			if (newColor != null) {
				uis.setselectionRedColor(newColor);
				mw.updateGUI();
				button.setColor(newColor);
			} else {
				return;
			}
		}

	}

	private class TextColorButtonEar implements ActionListener {
		ColorButton button;

		public TextColorButtonEar(ColorButton button) {
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Color newColor = JColorChooser.showDialog(null, "Color",
					uis.gettextColor());
			if (newColor != null) {
				uis.settextColor(newColor);
				mw.updateGUI();
				button.setColor(newColor);
			} else {
				return;
			}
		}

	}

	private class SaveButtonEar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mw.saveSettings();
		}

	}

	private class QuitEar implements ActionListener {
		JInternalFrame frame;

		public QuitEar(JInternalFrame frame) {
			this.frame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}

	}

	private class SelectionHandler implements ItemListener {
		JInternalFrame frame;

		public SelectionHandler(JInternalFrame frame) {
			this.frame = frame;
		}

		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				changeSetting();
				frame.dispose();
				makeUI();
			}
		}

	}
}
