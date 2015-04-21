package com.dakkra.pyxleos.modules;

import com.dakkra.pyxleos.controller.CanvasController;
import com.dakkra.pyxleos.model.CanvasModel;
import com.dakkra.pyxleos.model.MainModel;
import com.dakkra.pyxleos.view.CanvasView;

public class Canvas {
	CanvasModel cvm;
	CanvasView cvv;
	CanvasController cvc;
	MainModel m;
	public Canvas(MainModel m){
		System.out.println("A new canvas was created");
		this.m = m;
		cvm = new CanvasModel();
		cvc = new CanvasController(cvm);
		cvv = new CanvasView(m,cvm,cvc);
		
		cvv.createAndShowGUI();
		
	}
}
