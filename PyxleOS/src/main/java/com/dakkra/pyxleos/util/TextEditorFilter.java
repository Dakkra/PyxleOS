package com.dakkra.pyxleos.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class TextEditorFilter extends FileFilter {
	
	//Accept all directories and all image files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
 
        String extension = Util.getExtension(f);
        if (extension != null) {
            if (extension.equals(Util.txt)) {
                    return true;
            } else {
                return false;
            }
        }
 
        return false;
    }
    
    public static boolean supportedType(File f) {
        if (f.isDirectory()) {
            return true;
        }
 
        String extension = Util.getExtension(f);
        if (extension != null) {
            if (extension.equals(Util.txt)) {
                    return true;
            } else {
                return false;
            }
        }
 
        return false;
    }
 
    //The description of this filter
    public String getDescription() {
        return "Text files (.txt)";
    }
	
}
