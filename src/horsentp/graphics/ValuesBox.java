/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.graphics;

import horsentp.ValuesMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathon
 */
public class ValuesBox extends GraphicsBox<ValuesMap> {

    private BufferedImage background;
    
    public ValuesBox() {
        super(500, 400, 140, 240);
        background = ImageManager.getManager().getImage("images/valuesBg.png");
    }

    @Override
    public void draw(Graphics2D g2d, ValuesMap obj) {
        g2d.drawImage(background, getX(), getY(), null);
        if (obj.getWeapons() == 0) {
            g2d.setColor(Color.YELLOW);
        } else {
            g2d.setColor(Color.WHITE);
        }
        g2d.drawString("" + obj.getWeapons(), getX()+80, getY()+40);
        if (obj.getArmor() == 0) {
            g2d.setColor(Color.YELLOW);
        } else {
            g2d.setColor(Color.WHITE);
        }
        g2d.drawString("" + obj.getArmor(), getX()+80, getY()+80);
        if (obj.getKeys() == 0) {
            g2d.setColor(Color.YELLOW);
        } else {
            g2d.setColor(Color.WHITE);
        }
        g2d.drawString("" + obj.getKeys(), getX()+80, getY()+120);
        g2d.setColor(Color.WHITE);
        g2d.drawString("" + obj.getHealth(), getX()+80, getY()+160);
        g2d.drawString("" + obj.getCrystals(), getX()+80, getY()+210);
        
        
    }
    
}
