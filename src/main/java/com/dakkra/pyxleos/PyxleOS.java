package com.dakkra.pyxleos;

import java.io.IOException;
import java.io.InputStream;

import com.dakkra.pyxleos.modules.textedit.TextEdit;
import com.dakkra.pyxleos.ui.MainWindow;
import com.dakkra.pyxleos.ui.UISettings;
import com.dakkra.pyxleos.util.Util;

public class PyxleOS {
	public static void main(String[]args){
		System.out.println("Initializing PyxleOS::E");
		
		MainWindow mw = new MainWindow();
		
		UISettings uis = new UISettings();
		
		mw.setUIS(uis);
		mw.cnsUI();
		
		TextEdit te = new TextEdit(mw);
		InputStream input = PyxleOS.class.getResourceAsStream("/greeting.txt");
		String content = Util.read(input);
		try {
			input.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		te.setText(content);
		
		System.out.println("Ready!");
	}
}
