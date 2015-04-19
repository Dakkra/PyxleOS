package com.dakkra.pyxleos.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.dakkra.pyxleos.model.TextEditModel;

public class TextEditController {
	public TextEditModel tem;
	public void readTextFile(){
		File iFile = new File(tem.fileURI);
		System.out.println("Opening "+tem.fileName);
		FileReader reader = null;
		try {reader = new FileReader(iFile);} 
		catch (FileNotFoundException e1) {e1.printStackTrace();}
		BufferedReader bufferedReader = new BufferedReader(reader);
		String fullText = "";
		String line;
		
		try {
				while((line = bufferedReader.readLine()) != null){
					fullText += (line+"\n");
				}
		}
		catch (IOException e1) {e1.printStackTrace();}
		finally{
		try {reader.close();}
		catch (IOException e1) {e1.printStackTrace();}
		try {bufferedReader.close();}
		catch (IOException e1) {e1.printStackTrace();}
		}
		tem.textArea.setText(fullText);
		tem.textEditFrame.setTitle(tem.fileName);
		tem.fileReopen.setEnabled(true);
	}
}
