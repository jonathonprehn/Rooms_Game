/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.graphics;

import horsentp.ActionMap;
import horsentp.GameAction;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathon
 */
public class ActionBox extends GraphicsBox<ActionMap> {

    private BufferedImage background;
    private BufferedImage actionButtonDown;
    private BufferedImage actionButtonUp;
    
    public ActionBox() {
        super(0, 400, 500, 240);
        background = ImageManager.getManager().getImage("images/actionBg.png");
        actionButtonDown = ImageManager.getManager().getImage("images/buttonDown.png");
        actionButtonUp = ImageManager.getManager().getImage("images/buttonUp.png");
        
    }

    @Override
    public void draw(Graphics2D g2d, ActionMap obj) {
        g2d.drawImage(background, getX(), getY(), null);
        for (int i=0; i<obj.actionCount(); i++) {
            GameAction act = obj.getAction(i);
            int x = obj.getCoordinateXFor(i);
            int y = obj.getCoordinateYFor(i);
            if (obj.over(obj.mouseX, obj.mouseY, i)) {
                g2d.drawImage(actionButtonDown, x, y, null);
            } else {
                g2d.drawImage(actionButtonUp, x, y, null);
            }
            //Draw a text label
            FontMetrics fm = g2d.getFontMetrics();
            g2d.drawString(act.getLabel(), x+100-fm.stringWidth(act.getLabel())/2, y+24);
        }
    }
    
}
