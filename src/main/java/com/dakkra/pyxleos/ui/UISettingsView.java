package com.dakkra.pyxleos.ui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);

		JPanel bgColorPanel = new JPanel();
		bgColorPanel.setLayout(layout);
		bgColorPanel.add(new JLabel("BG Color: "));
		ColorButton bgColorButton = new ColorButton(uis.getbgColor());
		bgColorPanel.add(bgColorButton);

		JPanel baseColorPanel = new JPanel();
		baseColorPanel.setLayout(layout);
		baseColorPanel.add(new JLabel("Base Color: "));
		baseColorPanel.add(new JButton("Color"));

		JPanel baseRedColorPanel = new JPanel();
		baseRedColorPanel.setLayout(layout);
		baseRedColorPanel.add(new JLabel("Red Color: "));
		baseRedColorPanel.add(new JButton("Color"));

		JPanel textColorPanel = new JPanel();
		textColorPanel.setLayout(layout);
		textColorPanel.add(new JLabel("TextColor: "));
		textColorPanel.add(new JButton("Color"));

		frame.setLayout(layout);
		frame.add(bgColorPanel);
		frame.add(baseColorPanel);
		frame.add(baseRedColorPanel);
		frame.add(textColorPanel);

		mw.addIFrame(frame);
	}
}
