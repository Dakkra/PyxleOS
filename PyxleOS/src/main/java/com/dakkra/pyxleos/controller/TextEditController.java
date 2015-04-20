package com.dakkra.pyxleos.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

import com.dakkra.pyxleos.model.TextEditModel;
import com.dakkra.pyxleos.util.TextEditorFilter;

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
	
	public void saveTextFile(){
    	if (tem.fileURI != null){
    		File saveFile = new File(tem.fileURI);
    		String fullText = tem.textArea.getText();
    		System.out.println("Saving "+tem.fileName);
    		try {FileWriter writer = new FileWriter(saveFile);
    			writer.write(""+fullText);
    			writer.close();
    		}
    		catch (IOException e1) {e1.printStackTrace();}
    		tem.textEditFrame.setTitle(tem.fileName);
    		tem.fileReopen.setEnabled(true);
    	}else{
    		String fullText = tem.textArea.getText();
    		tem.saveJFC = new JFileChooser();
    		tem.saveJFC.setFileFilter(new TextEditorFilter());
    		int returnval = tem.saveJFC.showSaveDialog(null);
    		if (returnval == JFileChooser.APPROVE_OPTION){
    			System.out.println("Saving "+tem.saveJFC.getSelectedFile().getName()+".txt");
    			try(FileWriter fw = new FileWriter(tem.saveJFC.getSelectedFile()+".txt")){
    				fw.write(""+fullText);
    				tem.fileURI = tem.saveJFC.getSelectedFile().getAbsolutePath()+".txt";
    				tem.fileName = tem.saveJFC.getSelectedFile().getName()+".txt";
    				tem.textEditFrame.setTitle(tem.fileName);
    				tem.fileReopen.setEnabled(true);
    			} 
    			catch (IOException e1) {e1.printStackTrace();}
    			
    		}else{return;}
    	}
	}
}
