package com.dakkra.pyxleos.modules;

import com.dakkra.pyxleos.model.CanvasModel;
import com.dakkra.pyxleos.model.MainModel;
import com.dakkra.pyxleos.view.CanvasView;

public class Canvas {
	CanvasModel cvm;
	CanvasView cvv;
	MainModel m;
	public Canvas(MainModel m){
		System.out.println("A new canvas was created");
		this.m = m;
		cvm = new CanvasModel();
		cvv = new CanvasView(m);
		
		cvv.createAndShowGUI(m,cvm);
		
	}
}
