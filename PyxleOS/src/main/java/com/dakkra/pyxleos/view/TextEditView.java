package com.dakkra.pyxleos.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.dakkra.pyxleos.controller.TextEditController;
import com.dakkra.pyxleos.model.MainModel;
import com.dakkra.pyxleos.model.TextEditModel;
import com.dakkra.pyxleos.util.TextEditorFilter;

public class TextEditView {
	MainModel m;
	TextEditModel tem;
	TextEditController tec;
	public void createAndShowGUI(MainModel m,TextEditModel tem, TextEditController tec){
		this.m = m;
		this.tem = tem;
		this.tec = tec;
		
		//Menu bar
		tem.menuBar = new JMenuBar();
		//file menu
		tem.fileMenu = new JMenu(" File ");
		tem.fileNew = new JMenuItem("New");
		tem.fileNew.addActionListener(new NewListener());
		tem.fileOpen = new JMenuItem("Open");
		tem.fileOpen.addActionListener(new OpenListener());
		tem.fileReopen = new JMenuItem("Reopen");
		tem.fileReopen.addActionListener(new ReopenListener());
		tem.fileSave = new JMenuItem("Save");
		tem.fileSave.addActionListener(new SaveListener());
		tem.fileSaveAs = new JMenuItem("Save As");
		tem.fileSaveAs.addActionListener(new SaveAsListener());
		tem.fileExit = new JMenuItem("Exit");
		tem.fileExit.addActionListener(new ExitListener());
		tem.fileMenu.add(tem.fileNew);
		tem.fileMenu.add(tem.fileOpen);
		tem.fileMenu.add(tem.fileReopen);
		tem.fileReopen.setEnabled(false);
		tem.fileMenu.add(tem.fileSave);
		tem.fileMenu.add(tem.fileSaveAs);
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
		tem.textEditFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		
		//Setup text area (inside scrollpane so it can scroll)
		tem.textArea = new JTextArea();
		tem.textArea.setFont(tem.textAreaFont);
		tem.textAreaPane = new JScrollPane(tem.textArea);
		
		tem.textEditFrame.add(tem.textAreaPane);
		
		tem.textEditFrame.setVisible(true);
		
		m.mainJDPane.add(tem.textEditFrame);
		
		
	}
	private class NewListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	tem.textArea.setText(null);
	    	tem.fileURI = null;
	    	tem.fileName = null;
	    	tem.fileReopen.setEnabled(false);
	    	tem.textEditFrame.setTitle("TextEdit");
	    	
	    }
	}
	private class OpenListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	tem.openJFC = new JFileChooser();
	    	tem.openJFC.setFileFilter(new TextEditorFilter());
	    	int returnval = tem.openJFC.showOpenDialog(tem.openJFC);
	    	if (returnval == JFileChooser.APPROVE_OPTION){
	    		if (!TextEditorFilter.supportedType(tem.openJFC.getSelectedFile())){
	    			JOptionPane.showMessageDialog(null, "Invalid file!");
	    			return;
	    		}
	    		File textFile = tem.openJFC.getSelectedFile();
	    		tem.fileName = textFile.getName();
	    		tem.fileURI = textFile.getAbsolutePath();
	    		tec.readTextFile();
	    		
	    	}
	    	
	    }
	}
	private class SaveListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	tec.saveTextFile();
	    	}
	    }
	private class SaveAsListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	tem.saveJFC = new JFileChooser();
	    	int returnval = tem.saveJFC.showSaveDialog(null);
	    	if (returnval == JFileChooser.APPROVE_OPTION){
	    		tem.fileURI = tem.saveJFC.getSelectedFile().getAbsolutePath()+".txt";
	    		tem.fileName = tem.saveJFC.getSelectedFile().getName()+".txt";
	    	}else{return;}
	    	
	    	tec.saveTextFile();
	    	}
	    }
	private class ExitListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	tem.textEditFrame.dispose();
	    	
	    }
	}
	private class ReopenListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	tec.readTextFile();
	    	
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
