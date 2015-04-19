package com.dakkra.pyxleos.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.dakkra.pyxleos.model.MainModel;
import com.dakkra.pyxleos.model.TextEditModel;

public class TextEditView {
	MainModel m;
	TextEditModel tem;
	public void createAndShowGUI(MainModel m,TextEditModel tem){
		this.m = m;
		this.tem = tem;
		
		//Menu bar
		tem.menuBar = new JMenuBar();
		//file menu
		tem.fileMenu = new JMenu(" File ");
		tem.fileOpen = new JMenuItem("Open");
		tem.fileOpen.addActionListener(new OpenListener());
		tem.fileReopen = new JMenuItem("Reopen");
		tem.fileReopen.addActionListener(new ReopenListener());
		tem.fileSave = new JMenuItem("Save");
		tem.fileSave.addActionListener(new SaveListener());
		tem.fileExit = new JMenuItem("Exit");
		tem.fileExit.addActionListener(new ExitListener());
		tem.fileMenu.add(tem.fileOpen);
		tem.fileMenu.add(tem.fileSave);
		tem.fileMenu.add(tem.fileExit);
		//options menu
		tem.optionsMenu = new JMenu(" Options ");
		tem.optionsTextSize = new JMenuItem("Text Size");
		tem.optionsTextSize.addActionListener(new OptionsTextSizeListener());
		tem.optionsMenu.add(tem.optionsTextSize);
		//put it all together
		tem.menuBar.add(tem.fileMenu);
		tem.menuBar.add(tem.optionsMenu);
		
		//Set up internal frame
		tem.textEditFrame = new JInternalFrame("TextEdit",true,true,true,true);
		tem.textEditFrame.setBounds(10, 10, 500, 400);
		tem.textEditFrame.setJMenuBar(tem.menuBar);
		tem.textEditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Setup text area (inside scrollpane so it can scroll)
		tem.textArea = new JTextArea();
		tem.textArea.setFont(tem.textAreaFont);
		tem.textAreaPane = new JScrollPane(tem.textArea);
		
		tem.textEditFrame.add(tem.textAreaPane);
		
		tem.textEditFrame.setVisible(true);
		
		m.mainJDPane.add(tem.textEditFrame);
		
		
	}
	private class OpenListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	JFileChooser openJFC = new JFileChooser();
	    	int returnval = openJFC.showOpenDialog(openJFC);
	    	if (returnval == JFileChooser.APPROVE_OPTION){
	    		File textFile = openJFC.getSelectedFile();
	    		tem.fileName = textFile.getName();
	    		tem.fileURI = textFile.getAbsolutePath();
	    		System.out.println("Opening "+tem.fileName);
	    		FileReader reader = null;
	    		try {reader = new FileReader(textFile);} 
	    		catch (FileNotFoundException e1) {e1.printStackTrace();}
	    		BufferedReader bufferedReader = new BufferedReader(reader);
	    		String fullText = "";
	    		String line;
	    		
	    		try {
	    			if (bufferedReader.readLine() != null){
						while((line = bufferedReader.readLine()) != null){
							fullText += (line+"\n");
							tem.textArea.setText(fullText);
						}
	    			}else{
	    				tem.textArea.setText(null);
	    			}
	    		}
	    		catch (IOException e1) {e1.printStackTrace();}
	    		finally{
	    		try {reader.close();}
	    		catch (IOException e1) {e1.printStackTrace();}
	    		try {bufferedReader.close();}
	    		catch (IOException e1) {e1.printStackTrace();}
	    		}
	    		tem.textEditFrame.setTitle(tem.fileName);
	    		
	    		
	    	}
	    	
	    }
	}
	private class SaveListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	if (tem.fileURI != null){
	    		File saveFile = new File(tem.fileURI);
	    		String fullText = tem.textArea.getText();
	    		
	    		
	    	}else{
	    		
	    	}
	    }
	}
	private class ExitListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	tem.textEditFrame.dispose();
	    	
	    }
	}
	private class ReopenListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	System.out.println("Yet to be implemented");
	    	//TODO implement reopen
	    	
	    }
	}
	private class OptionsTextSizeListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	//TODO error handling
	    	tem.textAreaFont = new Font("Arial",Font.PLAIN,Integer.parseInt(JOptionPane.showInputDialog("Input a new font size:",tem.textAreaFont.getSize())));
	    	tem.textArea.setFont(tem.textAreaFont);
	    }
	}
}
