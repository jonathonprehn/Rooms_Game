/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Individual tiles for the game world
 * @author Jonathon
 */
public class GameTile {
    
    private static final int GAME_OBJECT_SPACE = 9;
    private ArrayList<GameObject> objects;
    
    public GameTile() {
        objects = new ArrayList<>();
    }
    
    public void addObject(GameObject obj) {
        if (objects.size()<GAME_OBJECT_SPACE) {
            objects.add(obj);
        }
    }
    
    public void removeObject(GameObject obj) {
            objects.remove(obj);
    }
    
    public int getObjectCount() {
        return objects.size();
    }
    
    public GameObject getObject(String name) {
        for (int i=0; i<objects.size(); i++) {
            if (objects.get(i).getName().equals(name)) {
                return objects.get(i);
            }
        }
        return null;
    }
    
    public GameObject getObject(GameObjectType type) {
        for (int i=0; i<objects.size(); i++) {
            if (objects.get(i).getType() == type) {
                return objects.get(i);
            }
        }
        return null;
    }
    
    public ArrayList<GameObject> getGameObjects() {
        return objects;
    }
    
    public void draw(Graphics2D g2d, int x, int y) {
        //Draw each object
        int i=0;
        for (int r=0; r < 3 && i < objects.size(); r++) {
            for (int c=0; c < 3 && i < objects.size(); c++) {
                g2d.drawImage(objects.get(i).getSprite(), x + 5 + r*45, y + 5 + c*45, null);
                i++;
            }
        }
    }
}
