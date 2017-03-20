/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp;

import java.awt.image.BufferedImage;

/**
 * Map for the game. Loaded via text file
 * @author Jonathon
 */
public class GameMap {
    
    private GameTile[][] map;
    private int width;
    private int height;
    public GameObject player = new GameObject("BILL", GameObjectType.PLAYER, "images/player.png");
    public int playerLocX = 0, playerLocY = 0;
    public BufferedImage roomFloor;
    public BufferedImage horizontal;
    public BufferedImage vertical;
    
    public GameMap() {
        //NEVER CALL THIS EXPLICITY - USE GAMEMAPLOADER
    }

    public void setMap(GameTile[][] map) {
        this.map = map;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    /*
    Use the functions to modify instead of this
    */
    public GameTile[][] getMap() {
        return map;
    }
    
    public GameTile getPlayerTile() {
        return map[playerLocX][playerLocY];
    }
    
    public boolean hasRoomToNorth(int x, int y) {
        if (y > 0 && y < height && x >= 0 && x < width ) {
            //On the map and at least one from the
            //edge on the north side
            return map[x][y-1] != null;
            
        } else {
            return false;
        }
    }
    public boolean hasRoomToSouth(int x, int y) {
        if (y >= 0 && y < height-1 && x >= 0 && x < width ) {
            //On the map and at least one from the
            //edge on the south side
            return map[x][y+1] != null;
        } else {
            return false;
        }
    }
    public boolean hasRoomToEast(int x, int y) {
        if (y >= 0 && y < height && x >= 0 && x < width-1 ) {
            //On the map and at least one from the
            //edge on the east side
            return map[x+1][y] != null;
            
        } else {
            return false;
        }
    }
    public boolean hasRoomToWest(int x, int y) {
        if (y >= 0 && y < height && x > 0 && x < width ) {
            //On the map and at least one from the
            //edge on the west side
            return map[x-1][y] != null;
            
        } else {
            return false;
        }
    }
}
