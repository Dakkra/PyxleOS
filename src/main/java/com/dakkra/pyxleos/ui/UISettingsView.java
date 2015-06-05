package com.dakkra.pyxleos.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
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
		layout.setAlignment(FlowLayout.RIGHT);

		JPanel bgColorPanel = new JPanel();
		bgColorPanel.setLayout(layout);
		bgColorPanel.setBackground(Color.DARK_GRAY.darker());
		bgColorPanel.add(new JLabel("BG Color: "));
		ColorButton bgColorButton = new ColorButton(uis.getbgColor());
		bgColorButton.addActionListener(new BGColorButtonEar(bgColorButton));
		bgColorPanel.add(bgColorButton);

		JPanel baseColorPanel = new JPanel();
		baseColorPanel.setLayout(layout);
		baseColorPanel.setBackground(Color.DARK_GRAY.darker());
		baseColorPanel.add(new JLabel("Base Color: "));
		ColorButton baseColorButton = new ColorButton(uis.getbaseColor());
		baseColorButton.addActionListener(new BaseColorButtonEar(baseColorButton));
		baseColorPanel.add(baseColorButton);

		JPanel baseRedColorPanel = new JPanel();
		baseRedColorPanel.setLayout(layout);
		baseRedColorPanel.setBackground(Color.DARK_GRAY.darker());
		baseRedColorPanel.add(new JLabel("Red Color: "));
		ColorButton redColorButton = new ColorButton(uis.getbaseRedColor());
		redColorButton.addActionListener(new RedColorButtonEar(redColorButton));
		baseRedColorPanel.add(redColorButton);

		JPanel textColorPanel = new JPanel();
		textColorPanel.setLayout(layout);
		textColorPanel.setBackground(Color.DARK_GRAY.darker());
		textColorPanel.add(new JLabel("TextColor: "));
		ColorButton textColorButton = new ColorButton(uis.gettextColor());
		textColorButton.addActionListener(new TextColorButtonEar(textColorButton));
		textColorPanel.add(textColorButton);

		frame.setLayout(layout);
		frame.add(bgColorPanel);
		frame.add(baseColorPanel);
		frame.add(baseRedColorPanel);
		frame.add(textColorPanel);

		frame.setSize(425, 205);
		frame.setResizable(false);

		mw.addIFrame(frame);
	}

	private class BGColorButtonEar implements ActionListener {
		ColorButton button;
		
		public BGColorButtonEar(ColorButton button){
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Color newColor = JColorChooser.showDialog(null, "Color", uis.getbgColor());
			uis.setbgColor(newColor);
			mw.updateNimbus();
			mw.updateGUI();
			button.setColor(newColor);
		}

	}
	
	private class BaseColorButtonEar implements ActionListener {
		ColorButton button;
		
		public BaseColorButtonEar(ColorButton button){
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Color newColor = JColorChooser.showDialog(null, "Color", uis.getbaseColor());
			uis.setbaseColor(newColor);
			mw.updateNimbus();
			mw.updateGUI();
			button.setColor(newColor);
		}

	}
	
	private class RedColorButtonEar implements ActionListener {
		ColorButton button;
		
		public RedColorButtonEar(ColorButton button){
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Color newColor = JColorChooser.showDialog(null, "Color", uis.getbaseRedColor());
			uis.setbaseRedColor(newColor);
			mw.updateNimbus();
			mw.updateGUI();
			button.setColor(newColor);
		}

	}
	
	private class TextColorButtonEar implements ActionListener {
		ColorButton button;
		
		public TextColorButtonEar(ColorButton button){
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Color newColor = JColorChooser.showDialog(null, "Color", uis.gettextColor());
			uis.settextColor(newColor);
			mw.updateNimbus();
			mw.updateGUI();
			button.setColor(newColor);
		}

	}
}
