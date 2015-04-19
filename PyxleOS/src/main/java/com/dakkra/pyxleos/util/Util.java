package com.dakkra.pyxleos.util;

import java.io.File;

public class Util {
	public final static String txt = "txt";
	
	//Get file extension
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
