/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.graphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Jonathon
 * @param <T> the type of object to pass to graphics box to help it draw
 */
public abstract class GraphicsBox<T> {
    
    private int x, y, width, height;
    private boolean enabled;
    
    public GraphicsBox(int x, int y, int width, int height) {
        this.x = x;
        this.y= y;
        this.width = width;
        this.height = height;
        enabled = true;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isEnabled() {
        return enabled;
    }
    
    public abstract void draw(Graphics2D g2d, T obj);
    
    public void transformTo(Graphics2D g2d) {
        g2d.setClip(x, y, width, height);
    }
   
}
