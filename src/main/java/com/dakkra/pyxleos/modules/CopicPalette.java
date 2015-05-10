package com.dakkra.pyxleos.modules;

import com.dakkra.pyxleos.model.MainModel;
import com.dakkra.pyxleos.model.PaletteModel;
import com.dakkra.pyxleos.view.PaletteView;

//Copic Palette is a palette with copic colors built into it.
//See: https://www.copicmarker.com/wp-content/uploads/2014/05/color-wheel_2014.pdf
//And: https://imaginationinternationalinc.com/copic/store/color-picker/?name=&family=&type=2

public class CopicPalette {
	MainModel m;
	PaletteView ccpv;
	PaletteModel ccpm;

	public CopicPalette(MainModel m) {
		System.out.println("A new Copic Color Palette was created");
		ccpm = new PaletteModel();
		ccpv = new PaletteView(m, ccpm);
		this.m = m;

		ccpv.createAndShowGUI();
	}

}
