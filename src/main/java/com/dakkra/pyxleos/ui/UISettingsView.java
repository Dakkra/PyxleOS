package com.dakkra.pyxleos.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.dakkra.pyxleos.ui.components.ColorButton;
import com.dakkra.pyxleos.util.Util;

public class UISettingsView {
	public UISettings uis;
	private MainWindow mw;

	public UISettingsView(MainWindow mw, UISettings uis) {
		this.mw = mw;
		this.uis = uis;
	}

	public void makeUI() {

		JInternalFrame frame = Util.createIFrame("Appearance");

		MigLayout layout = new MigLayout();
		
		JPanel mPanel = new JPanel(layout);
		mPanel.setBackground(Color.DARK_GRAY);

		mPanel.add(new JLabel("Background: "));
		ColorButton bgColorButton = new ColorButton(uis.getbgColor());
		bgColorButton.addActionListener(new BGColorButtonEar(bgColorButton));
		mPanel.add(bgColorButton,"wrap");

		mPanel.add(new JLabel("Base: "));
		ColorButton baseColorButton = new ColorButton(uis.getbaseColor());
		baseColorButton.addActionListener(new BaseColorButtonEar(
				baseColorButton));
		mPanel.add(baseColorButton,"wrap");

		mPanel.add(new JLabel("Selection: "));
		ColorButton redColorButton = new ColorButton(uis.getbaseRedColor());
		redColorButton.addActionListener(new RedColorButtonEar(redColorButton));
		mPanel.add(redColorButton,"wrap");

		mPanel.add(new JLabel("Text: "));
		ColorButton textColorButton = new ColorButton(uis.gettextColor());
		textColorButton.addActionListener(new TextColorButtonEar(
				textColorButton));
		mPanel.add(textColorButton,"wrap");

		frame.add(mPanel);
		frame.setSize(425, 205);
		frame.setMaximizable(false);
		frame.setResizable(false);

		mw.addIFrame(frame);
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
					uis.getbaseRedColor());
			if (newColor != null) {
				uis.setbaseRedColor(newColor);
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
}
