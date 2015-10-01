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
    private JPanel editorPanel; //Center
    private JPanel toolPanel; //West
    private JPanel palettePanel; //East
    private JPanel animationPanel; //South

    public MainWindow() {
        frame = new JFrame();
        updateWindowTitle();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(frameW, frameH));
        frame.setLocationRelativeTo(null);
        frame.setIconImage(DakkraIO.loadImageResource("/Icon.png"));
        frame.setLayout(new BorderLayout());

        //Init the panels
        editorPanel = new JPanel();
        frame.add(editorPanel, BorderLayout.CENTER);

        toolPanel = new JPanel();
        toolPanel.setBackground(Color.YELLOW);
        frame.add(toolPanel, BorderLayout.WEST);

        palettePanel = new JPanel();
        frame.add(palettePanel, BorderLayout.EAST);

        animationPanel = new JPanel();
        frame.add(animationPanel, BorderLayout.SOUTH);

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
