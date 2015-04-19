package com.dakkra.pyxleos.modules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	public void setFile(String textFileURI){
		File textFile = new File(textFileURI);
		String name = textFile.getName();
		System.out.println("Opening "+name);
		FileReader reader = null;
		try {reader = new FileReader(textFile);} 
		catch (FileNotFoundException e1) {e1.printStackTrace();}
		BufferedReader bufferedReader = new BufferedReader(reader);
		String fullText = "";
		String line;
		
		try {
			while((line = bufferedReader.readLine()) != null){
				fullText += (line+"\n");
				tem.textArea.setText(fullText);
			}}
		catch (IOException e1) {e1.printStackTrace();}
		finally{
			try {reader.close();}
			catch (IOException e) {e.printStackTrace();}
			
			try {bufferedReader.close();}
			catch (IOException e) {e.printStackTrace();}
		}
	}

}