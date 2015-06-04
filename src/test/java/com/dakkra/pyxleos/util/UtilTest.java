package com.dakkra.pyxleos.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import junit.framework.TestCase;

public class UtilTest extends TestCase {
	public void testRead() throws Exception{
		String string = "Lollipop";
		
		InputStream stream = new ByteArrayInputStream(string.getBytes("utf-8"));
		
		String readString = Util.read(stream);
		
		assertEquals("String Mismatch",string,readString);
	}
}
