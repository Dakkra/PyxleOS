package com.dakkra.pyxleos.modules;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.dakkra.pyxleos.model.MainModel;

public class TextEdit {
	MainModel m;
	public TextEdit(MainModel m){
		this.m = m;
		TextEditView v = new TextEditView();
		
		v.createAndShowGUI(m);
		
	}
}

class TextEditView{
	MainModel m;
	void createAndShowGUI(MainModel m){
		this.m = m;
		JInternalFrame textEditFrame = new JInternalFrame("TextEdit",true,true,true,true);
		textEditFrame.setBounds(10, 10, 500, 400);
		
		textEditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		JTextArea textArea = new JTextArea();
		JScrollPane textAreaPane = new JScrollPane(textArea);
		
		textEditFrame.add(textAreaPane);
		
		textEditFrame.setVisible(true);
		
		m.mainJDPane.add(textEditFrame);
	}
	
}