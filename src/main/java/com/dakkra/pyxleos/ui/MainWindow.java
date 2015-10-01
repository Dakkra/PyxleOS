package com.dakkra.pyxleos.ui;

import com.dakkra.pyxleos.PyxleOS;
import com.dakkra.pyxleos.io.DakkraIO;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private JFrame frame;
    private String projectTitle = "untitled";
    private String modifiedSymbol = "*";
    private boolean isModified = false;
    private int frameW = (int) Math.round(PyxleOS.DISPLAY_WIDTH / 1.5);
    private int frameH = (int) Math.round(PyxleOS.DISPLAY_HEIGHT / 1.5);

    public MainWindow() {
        frame = new JFrame();
        updateWindowTitle();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(frameW, frameH));
        frame.setLocationRelativeTo(null);
        frame.setIconImage(DakkraIO.loadImageResource("/Icon.png"));

    }

    public void showMainWindow() {
        frame.setVisible(true);
    }

    public void updateWindowTitle() {
        frame.setTitle("PyxleOS: " + projectTitle + isModifiedSymbol());
    }

    private String isModifiedSymbol() {
        return isModified ? modifiedSymbol : "";
    }

}
