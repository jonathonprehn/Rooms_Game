/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.graphics;

import horsentp.GameMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathon
 */
public class TilesBox extends GraphicsBox<GameMap> {
    
    public TilesBox() {
        super(0, 0, 640, 400);
    }

    @Override
    public void draw(Graphics2D g2d, GameMap obj) {
        int xOffset = obj.playerLocX*160 + 70;
        int yOffset = obj.playerLocY*160 + 70;
        for (int x = 0; x < obj.getWidth(); x++) {
            for (int y = 0; y < obj.getHeight(); y++) {
                if (obj.getMap()[x][y] != null) {
                    int xLoc = -xOffset+(x*160)+getWidth()/2;
                    int yLoc = -yOffset+(y*160)+getHeight()/2;
                    g2d.drawImage(obj.roomFloor,xLoc, yLoc, null);
                    
                    if (obj.hasRoomToNorth(x, y)) {
                        //Draw the north thingy
                        g2d.drawImage(obj.vertical, xLoc+40, yLoc-20, null);
                    }
                    if (obj.hasRoomToSouth(x, y)) {
                        g2d.drawImage(obj.vertical, xLoc+40, yLoc+140, null);
                    }
                    if (obj.hasRoomToEast(x, y)) {
                        g2d.drawImage(obj.horizontal, xLoc+140, yLoc+40, null);
                    }
                    if (obj.hasRoomToWest(x, y)) {
                        g2d.drawImage(obj.horizontal, xLoc-20, yLoc+40, null);
                    }
                    
                    obj.getMap()[x][y].draw(g2d, xLoc, yLoc);
                }
            }   
        }
    }
    
}
