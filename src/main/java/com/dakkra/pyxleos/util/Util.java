package com.dakkra.pyxleos.util;

import javax.swing.JInternalFrame;

public class Util {
	private Util() {

	}

	public static JInternalFrame createIFrame(String name) {
		JInternalFrame iFrame = new JInternalFrame(name, true, true, true, true);
		return iFrame;
	}
}