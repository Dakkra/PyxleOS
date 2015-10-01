package com.dakkra.pyxleos.io;

import com.dakkra.pyxleos.PyxleOS;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class DakkraIO {

    /***
     *Loads an image from the resources
     * @param uri path to the image resource
     * @return Image that has been loaded
     */
    public static BufferedImage loadImageResource(String uri) {
        try {
            BufferedImage img = ImageIO.read(PyxleOS.class.getResourceAsStream(uri));
            return img;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
