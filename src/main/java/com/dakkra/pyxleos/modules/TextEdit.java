package com.dakkra.pyxleos.modules;

import com.dakkra.pyxleos.controller.TextEditController;
import com.dakkra.pyxleos.model.MainModel;
import com.dakkra.pyxleos.model.TextEditModel;
import com.dakkra.pyxleos.view.TextEditView;

public class TextEdit {
	MainModel m;
	TextEditModel tem;
	TextEditController tec;
	public TextEdit(MainModel m){
		System.out.println("A new TextEdit was created");
		this.m = m;
		TextEditView v = new TextEditView();
		tec = new TextEditController();
		tem = new TextEditModel();
		tec.tem = tem;
		
		v.createAndShowGUI(m,tem,tec);
		
	}
	public void setText(String text){
		tem.textArea.setText(text);
	}

}