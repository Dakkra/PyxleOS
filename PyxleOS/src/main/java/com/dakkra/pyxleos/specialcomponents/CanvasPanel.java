package com.dakkra.pyxleos.specialcomponents;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class CanvasPanel extends JPanel {
	private static final long serialVersionUID = -1077951759175752806L;
	private BufferedImage image;

    public CanvasPanel(BufferedImage image) {
    	this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters        
    }
    
    public void updateCanvas(){
    	
    }
}
