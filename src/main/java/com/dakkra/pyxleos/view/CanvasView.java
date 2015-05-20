package com.dakkra.pyxleos.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.dakkra.pyxleos.controller.CanvasController;
import com.dakkra.pyxleos.model.CanvasModel;
import com.dakkra.pyxleos.model.MainModel;
import com.dakkra.pyxleos.specialcomponents.CanvasPad;

public class CanvasView {
	MainModel m;
	CanvasModel cvm;
	CanvasController cvc;

	public static final Color canvasBG = new Color(80, 80, 80);

	public CanvasView(MainModel m, CanvasModel cvm, CanvasController cvc) {
		this.m = m;
		this.cvm = cvm;
		this.cvc = cvc;
	}

	public void createAndShowGUI() {

		cvm.menuBar = new JMenuBar();
		// file menu
		cvm.fileMenu = new JMenu(" File ");
		cvm.fileMenu.setEnabled(false);
		cvm.fileNew = new JMenuItem("New");
		cvm.fileOpen = new JMenuItem("Open");
		cvm.fileSave = new JMenuItem("Save");
		cvm.fileSave.addActionListener(new SaveListener());
		cvm.fileExit = new JMenuItem("Exit");
		cvm.fileExit.addActionListener(new ExitListener());
		cvm.fileMenu.add(cvm.fileNew);
		cvm.fileMenu.add(cvm.fileOpen);
		cvm.fileMenu.add(cvm.fileSave);
		cvm.fileMenu.add(cvm.fileExit);
		// all together
		cvm.menuBar.add(cvm.fileMenu);

		cvm.canvasFrame = new JInternalFrame("Canvas", true, true, true, true);
		cvm.canvasFrame.setBounds(520, 10, 400, 400);
		cvm.canvasFrame.setJMenuBar(cvm.menuBar);
		cvm.canvasFrame
				.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

		// TODO wrap image in container in a scrollable
		cvm.containerPanel = new JPanel();
		cvm.containerPanel.setPreferredSize(new Dimension(cvm.canvasFrame
				.getWidth(), cvm.canvasFrame.getHeight()));
		cvm.containerPanel.setLayout(new BorderLayout());
		
		cvm.canvasPad = new CanvasPad(cvm, m);
		
		cvm.canvasPane = new JScrollPane(cvm.canvasPad);

		cvm.containerPanel.add(cvm.canvasPane, BorderLayout.CENTER);

		cvm.canvasFrame.add(cvm.containerPanel);

		cvm.canvasFrame.setVisible(true);
		m.mainJDPane.add(cvm.canvasFrame);
	}

	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cvm.canvasFrame.dispose();

		}
	}

	private class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				cvc.saveImage();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}
	 private class MouseAdapter implements MouseWheelListener, MouseListener, MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
            if(e.getWheelRotation() == 1) {
            //TODO make the canvas scale
            }else {
            }
			
		}
		 
	 }
}
