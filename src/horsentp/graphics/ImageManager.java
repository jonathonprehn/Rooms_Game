/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author Jonathon
 */
public class ImageManager {
    
    private static ImageManager imageManager = new ImageManager();
    
    public static ImageManager getManager() {
        return imageManager;
    }
    
    private HashMap<String, BufferedImage> imageMap = new HashMap<String, BufferedImage>();
            
    /**
     * Gets an image from the images folder relative to the jar file
     * @param src the src of the image
     * @return the image
     */
    public BufferedImage getImage(String src) {
        BufferedImage image = imageMap.get(src);
        if (image == null) {
            try {
                image = ImageIO.read(ImageManager.class.getResourceAsStream("/" + src));
                imageMap.put(src, image);
            } catch(IOException e) { System.err.println("Could not load image: " + src); }
        }
        return image;
    }
            
}
