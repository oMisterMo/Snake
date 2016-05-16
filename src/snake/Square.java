/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author Mo
 */
public class Square extends GameObject {
    
    private Color color;
    
    private int dx;
    private int dy;

    public Square(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(x, y, width, height);
        
        /*
        set color of grahpic object
            -> SexyOrange (255, 255, 140)
        */
        color = new Color(255, 149, 237);
        
        dx = 0;
        dy = 0;
    }
    
        //Move when key is pressed
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }
        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
    }

    //Stop moving when the key is released
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        System.out.println(y + height);
    }

    @Override
    void gameUpdate() {
        //To the right of screen
        if (x > GamePanel.GAME_WIDTH - width) {
            x = 0;
        }
        //To the botom
        if(y > GamePanel.GAME_HEIGHT - height){
            y = 0;
        }
        //To the left
        if (x < 0) {
            x = GamePanel.GAME_WIDTH - width;
        }
        //To the top
        if(y < 0){
            y = GamePanel.GAME_HEIGHT - height;
        }
        x+=dx;
        y+=dy;
    }

    @Override
    void gameRender(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

}
