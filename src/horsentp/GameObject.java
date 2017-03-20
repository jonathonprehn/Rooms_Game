/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp;

import horsentp.graphics.ImageManager;
import java.awt.image.BufferedImage;

/**
 * The things to be put in a game tile.
 * @author Jonathon
 */
public class GameObject {
    
    private String name;
    private GameObjectType type;
    private BufferedImage sprite;

    public GameObject(String name, GameObjectType type, String spriteLoc) {
        this.name = name;
        this.type = type;
        sprite = ImageManager.getManager().getImage(spriteLoc);
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    
    
    
    public String getName() {
        return name;
    }

    public GameObjectType getType() {
        return type;
    }
    
    
}
