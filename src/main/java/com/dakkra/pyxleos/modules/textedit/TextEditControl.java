package com.dakkra.pyxleos.modules.textedit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;

public class TextEditControl {

	private TextEditControl() {

	}

	public static String readText(File textFile, JInternalFrame frame) {
		String name = textFile.getName();
		frame.setTitle(name);
		
		System.out.println("Opening " + textFile);
		FileReader reader = null;
		try {
			reader = new FileReader(textFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(reader);
		String fullText = "";
		String line;

		try {
			while ((line = bufferedReader.readLine()) != null) {
				fullText += (line + "\n");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				bufferedReader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		return fullText;
	}

	public static File saveText(File textFile, JTextArea textArea, JInternalFrame frame) {
		
		if (textFile != null) {
			File saveFile = new File(textFile.getAbsolutePath());
			String fullText = textArea.getText();
			System.out.println("Saving " + textFile.getName());
			try {
				FileWriter writer = new FileWriter(saveFile);
				writer.write("" + fullText);
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			textFile = saveFile;
			frame.setTitle(textFile.getName());
			return textFile;
		} else {
			String fullText = textArea.getText();
			JFileChooser saveJFC = new JFileChooser();
			int returnval = saveJFC.showSaveDialog(null);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				textFile = saveJFC.getSelectedFile();
				System.out.println("Saving "
						+ textFile.getName());
				try (FileWriter fw = new FileWriter(
						textFile)) {
					fw.write("" + fullText);
					System.out.println("Got here");
					frame.setTitle(textFile.getName());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return textFile;
			} else {
			}
		}
		return textFile;
		
	}

	public static void reset(JInternalFrame frame, JTextArea textArea) {
		frame.setTitle("TextEdit");
		textArea.setText("");
	}

}
