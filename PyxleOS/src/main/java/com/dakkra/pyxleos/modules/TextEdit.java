package com.dakkra.pyxleos.modules;

import com.dakkra.pyxleos.model.MainModel;
import com.dakkra.pyxleos.model.TextEditModel;
import com.dakkra.pyxleos.view.TextEditView;

public class TextEdit {
	MainModel m;
	TextEditModel tem;
	public TextEdit(MainModel m){
		this.m = m;
		TextEditView v = new TextEditView();
		tem = new TextEditModel();
		
		v.createAndShowGUI(m,tem);
		
	}
	public void setText(String text){
		tem.textArea.setText(text);
	}

}