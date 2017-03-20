/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.graphics;

import horsentp.CombatMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathon
 */
public class CombatBox extends GraphicsBox<CombatMap> {

    private BufferedImage background;
    
    public CombatBox() {
        super(460, 260, 180, 140);
        background = ImageManager.getManager().getImage("images/combatBg.png");
    }

    @Override
    public void draw(Graphics2D g2d, CombatMap obj) {
        g2d.drawImage(background, getX(), getY(), null);
        for (int i=0; i<obj.getLogItems(); i++) {
            if (i == obj.getLogItems()-1) {
                g2d.setColor(Color.YELLOW);
            } else {
                g2d.setColor(Color.WHITE);
            }
            g2d.drawString(obj.getLogItem(i), getX()+16, getY()+22+ i*(g2d.getFontMetrics().getHeight()) );
        }
    }
    
}
