package com.dakkra.pyxleos.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class Util {
	public final static String txt = "txt";

	// Get file extension
	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
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
			if( stream != null ) {
				try {
					stream.close();
				} catch (IOException closeException) {
					closeException.printStackTrace(System.err);
				}
			}
			if( writer != null ) {
				try {
					writer.close();
				} catch (IOException closeException) {
					closeException.printStackTrace(System.err);
				}
			}
		}

		return writer.toString();
	}
}
