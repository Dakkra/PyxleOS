package com.dakkra.pyxleos.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import com.dakkra.pyxleos.PyxleOS;

public class Util {

	private Util() {

	}

	public static JInternalFrame createIFrame(String name) {
		JInternalFrame iFrame = new JInternalFrame(name, true, true, true, true);
		iFrame.setBounds(0, 0, 400, 400);
		// iFrame.requestFocusInWindow();
		iFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		// iFrame.toFront();
		try {
			iFrame.setSelected(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
		iFrame.setVisible(true);
		return iFrame;
	}

	public static void exitApp() {
		int confirm = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to exit the application?");
		if (confirm == JOptionPane.OK_OPTION) {
			System.exit(0);
		} else {
			return;
		}
	}

	public static void exitIFrame(JInternalFrame iFrame) {
		int confirm = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to close this module?");
		if (confirm == JOptionPane.OK_OPTION) {
			iFrame.dispose();
		} else {
			return;
		}
	}

	public static String read(InputStream stream) {
		StringWriter writer = new StringWriter();

		int read = 0;
		byte[] buffer = new byte[4096];
		try {
			while ((read = stream.read(buffer)) != -1) {
				writer.write(new String(buffer, 0, read));
			}
		} catch (IOException exception) {
			exception.printStackTrace(System.err);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException closeException) {
					closeException.printStackTrace(System.err);
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException closeException) {
					closeException.printStackTrace(System.err);
				}
			}
		}

		return writer.toString();
	}

	public static Font makeFont(int size, int style) {
		InputStream in = PyxleOS.class
				.getResourceAsStream("/SourceSansPro-Regular.ttf");
		Font myFont = null;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, in).deriveFont(style,
					size);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (myFont == null) {
			myFont = new Font("Plain", style, 12);
		}
		return myFont;
	}

}