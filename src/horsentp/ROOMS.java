/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp;

import horsentp.graphics.ActionBox;
import horsentp.graphics.CombatBox;
import horsentp.graphics.GameWindow;
import horsentp.graphics.ImageManager;
import horsentp.graphics.Music;
import horsentp.graphics.TilesBox;
import horsentp.graphics.ValuesBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Jonathon
 */
public class ROOMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println(new File("").getAbsolutePath());
        
        
        GameMap mp = new GameMap();
        GameMapLoader.load("maps/map1.txt", mp);
        
        //Screen for winning & losing
        BufferedImage winImage = ImageManager.getManager().getImage("images/win.png");
        BufferedImage loseImage = ImageManager.getManager().getImage("images/lose.png");
        BufferedImage startImage = ImageManager.getManager().getImage("images/start.png");
        boolean started = false;
        
        //game values
        final ActionMap am = new ActionMap();
        final CombatMap cm = new CombatMap();
        final ValuesMap vm = new ValuesMap();
        
        //graphics
        final GameWindow win = new GameWindow();
                
        ActionBox aBox = new ActionBox();
        TilesBox tBox = new TilesBox();
        ValuesBox vBox = new ValuesBox();
        CombatBox cBox = new CombatBox();
        Font gameFont = new Font(Font.MONOSPACED, Font.BOLD, 14);
        
        final ArrayList<MouseEvent> mousePresses = new ArrayList<>();
        
        //setup input
        win.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                synchronized(mousePresses) {
                    mousePresses.add(e);
                }
            }
            
        });
        win.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                synchronized(am) {
                    am.mouseX = e.getX()-win.getInsets().left;
                    am.mouseY = e.getY()-win.getInsets().top;
                }
            }

            
        });
        
        Music music = new Music(win);

        
        am.refreshActions(mp, cm, vm);
        
        long bef;
        long dif;
        while(!win.isShouldClose()) {
            bef = System.currentTimeMillis();
            //Update the game
            if (vm.getCrystals() == 7) {
                //Then you have won
                Graphics2D g2d = win.drawGraphics();
                g2d.translate(win.getInsets().left, win.getInsets().top);
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, 640, 640);
                
                g2d.drawImage(winImage, 0, 0, null);
                
                g2d.dispose();
                win.redraw();
            } else if (vm.getHealth()<=0) {
                //Then you have lost
                Graphics2D g2d = win.drawGraphics();
                g2d.translate(win.getInsets().left, win.getInsets().top);
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, 640, 640);
                
                g2d.drawImage(loseImage, 0, 0, null);
                
                g2d.dispose();
                win.redraw();
            }else {
            
            //Evaluate where the player is located and derive actions based on
            //that by refreshing the buttons
            
                if (started) {
                    Graphics2D g2d = win.drawGraphics();
                    g2d.translate(win.getInsets().left, win.getInsets().top);
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(0, 0, 640, 640);

                    //Setup for drawing text
                    g2d.setColor(Color.WHITE);
                    g2d.setFont(gameFont);

                    AffineTransform affine = g2d.getTransform();

                    tBox.transformTo(g2d);
                    tBox.draw(g2d, mp);
                    g2d.setTransform(affine);

                    aBox.transformTo(g2d);
                    aBox.draw(g2d, am);
                    g2d.setTransform(affine);

                    vBox.transformTo(g2d);
                    vBox.draw(g2d, vm);      
                    g2d.setTransform(affine);

                    cBox.transformTo(g2d);
                    cBox.draw(g2d, cm);      
                    g2d.setTransform(affine);

                    g2d.dispose();
                    win.redraw();
                } else {
                    //Start screen
                    Graphics2D g2d = win.drawGraphics();
                    g2d.translate(win.getInsets().left, win.getInsets().top);
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(0, 0, 640, 640);

                    g2d.drawImage(startImage, 0, 0, null);

                    g2d.dispose();
                    win.redraw();
                }
                //Input polling
                synchronized(mousePresses) {
                    MouseEvent cur = null;
                    while (mousePresses.size() > 0 && ( cur = mousePresses.get(0)) != null) {
                        if (!started) {
                            started = true;
                        } else {
                            //Process mouse press
                            GameAction clicked = am.getClickedAction(cur.getX()-win.getInsets().left, cur.getY()-win.getInsets().top);
                            //System.out.println("CLICKY");
                            if (clicked != null) {
                                clicked.doAction(am, cm, mp, vm);
                            }
                        }
                        mousePresses.remove(0);
                    }
                }
            }

            dif = System.currentTimeMillis()-bef;
            if (dif < 40) {
                try {
                     Thread.sleep(40 - dif);
                }catch(Exception e) {
                    System.err.println("Something went wrong while waiting");
                }
            }
        }
        //cleanup stuff
        win.dispose();
    }
    
}
