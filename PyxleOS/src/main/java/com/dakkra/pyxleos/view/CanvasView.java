package com.dakkra.pyxleos.view;

import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.dakkra.pyxleos.model.CanvasModel;
import com.dakkra.pyxleos.model.MainModel;

public class CanvasView {
	MainModel m;
	CanvasModel cvm;
	public CanvasView(MainModel m){
		this.m = m;
	}
	
	public void createAndShowGUI(MainModel m,CanvasModel cvm){
		this.m = m;
		this.cvm = cvm;
		
		cvm.menuBar = new JMenuBar();
		//file menu
		cvm.fileMenu = new JMenu(" File ");
		cvm.fileNew = new JMenuItem("New");
		cvm.fileOpen = new JMenuItem("Open");
		cvm.fileSave = new JMenuItem("Save");
		cvm.fileExit = new JMenuItem("Exit");
		cvm.fileMenu.add(cvm.fileNew);
		cvm.fileMenu.add(cvm.fileOpen);
		cvm.fileMenu.add(cvm.fileSave);
		cvm.fileMenu.add(cvm.fileExit);
		//all together
		cvm.menuBar.add(cvm.fileMenu);
		
		cvm.canvasFrame = new JInternalFrame("Canvas",true,true,true,true);
		cvm.canvasFrame.setBounds(520, 10, 400, 400);
		cvm.canvasFrame.setJMenuBar(cvm.menuBar);
		cvm.canvasFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		
		
		cvm.canvasFrame.setVisible(true);
		m.mainJDPane.add(cvm.canvasFrame);
	}
}
