package com.dakkra.pyxleos;

import javax.swing.*;

public class PyxleOS {

    public static void main(String[] args){
        System.out.println("Initializing...");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initApp();
            }
        });
    }

    private static void initApp(){
        //Init swing here
    }

}
