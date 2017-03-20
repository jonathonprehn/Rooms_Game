/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp;

/**
 *
 * @author Jonathon
 */
public class MagicBoxGameObject extends GameObject {

    private String portalLoc;
    private int portalX, portalY;
    
    public MagicBoxGameObject(String name, GameObjectType type, 
            String spriteLoc, String portalLoc, int portalX, int portalY) {
        super(name, type, spriteLoc);
        this.portalLoc = portalLoc;
        this.portalX = portalX;
        this.portalY = portalY;
    }

    public String getPortalLoc() {
        return portalLoc;
    }

    public int getPortalX() {
        return portalX;
    }

    public int getPortalY() {
        return portalY;
    }
    
    
    
}
