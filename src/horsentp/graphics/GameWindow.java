/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.graphics;

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 *
 * @author Jonathon
 */
public class GameWindow extends JFrame {

    private BufferStrategy bs;
    public static int GAME_SCREEN_WIDTH = 640;
    public static int GAME_SCREEN_HEIGHT = 640;
    private boolean shouldClose = false;
    
    public GameWindow() throws HeadlessException {
        super("The Crystals of Betrayal");
        setIconImage(ImageManager.getManager().getImage("images/crystal3.png"));
        setResizable(false);
        setVisible(true);
        DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
        int widthWithInsets = getInsets().right+getInsets().left+GAME_SCREEN_WIDTH;
        int heightWithInsets = getInsets().top+getInsets().bottom+GAME_SCREEN_HEIGHT;
        setBounds(
                mode.getWidth()/2 - widthWithInsets/2,
                mode.getHeight()/2 - heightWithInsets/2,
                widthWithInsets, heightWithInsets);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                shouldClose = true;
            }
        });
        
    }

    public boolean isShouldClose() {
        return shouldClose;
    }

    public void createBuffer() {
        if (bs == null) {
            createBufferStrategy(2);
            bs = getBufferStrategy();
        }
    }
    
    public Graphics2D drawGraphics() {
        createBuffer();
        return (Graphics2D)bs.getDrawGraphics();
    }
    
    public void redraw() {
        bs.show();
    }
}
