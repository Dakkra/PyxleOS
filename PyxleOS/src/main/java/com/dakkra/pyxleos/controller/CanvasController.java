package com.dakkra.pyxleos.controller;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.dakkra.pyxleos.model.CanvasModel;

public class CanvasController {
	CanvasModel cvm;
	public CanvasController (CanvasModel cvm){
		this.cvm = cvm;
	}
	public void saveImage() throws IOException{
		System.out.println("Sacing image");
		File oFile = new File("render.png");
		System.out.println(oFile.getAbsolutePath());
		ImageIO.write(cvm.canvasImage, "png", oFile);
	}
}
