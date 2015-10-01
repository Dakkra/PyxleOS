package com.dakkra.pyxleos;

import com.dakkra.pyxleos.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class PyxleOS {

    public static final GraphicsDevice SYSTEM_DISPLAY = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    public static final int DISPLAY_WIDTH = SYSTEM_DISPLAY.getDisplayMode().getWidth();
    public static final int DISPLAY_HEIGHT = SYSTEM_DISPLAY.getDisplayMode().getHeight();

    public static void main(String[] args) {
        System.out.println("Initializing...");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initApp();
            }
        });
    }

    private static void initApp() {
        MainWindow mw = new MainWindow();

        mw.showMainWindow();
    }

}
