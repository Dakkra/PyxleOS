package com.dakkra.pyxleos.view;

import javax.swing.JInternalFrame;

import com.dakkra.pyxleos.model.MainModel;
import com.dakkra.pyxleos.model.PaletteModel;

public class PaletteView {
	MainModel m;
	PaletteModel ccpm;
	
	public PaletteView(MainModel m, PaletteModel ccpm){
		this.m = m;
		this.ccpm = ccpm;
		
	}
	
	public void createAndShowGUI(){
		
		ccpm.paletteFrame = new JInternalFrame("Palette",true,true,true,true);
		ccpm.paletteFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		ccpm.paletteFrame.setBounds(10, 420, 400, 300);
		
		ccpm.paletteFrame.setVisible(true);
		m.mainJDPane.add(ccpm.paletteFrame);
	}
}
