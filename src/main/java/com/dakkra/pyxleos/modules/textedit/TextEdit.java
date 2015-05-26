package com.dakkra.pyxleos.modules.textedit;

import javax.swing.JInternalFrame;

import com.dakkra.pyxleos.ui.MainWindow;
import com.dakkra.pyxleos.util.Util;

public class TextEdit {
	private MainWindow mw;
	public TextEdit(MainWindow mw){
		this.mw = mw;
		makeUI();
	}
	private void makeUI(){
		JInternalFrame frame = Util.createIFrame("TextEdit");
		
		mw.addIFrame(frame);
	}
}
