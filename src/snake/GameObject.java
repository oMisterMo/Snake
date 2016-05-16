/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Represents any object which is drawn to the screen.
 *
 * Possible subclasses: -> Tile -> Particle -> Player -> Square
 *
 * ALT + SHIFT + F = auto re-factor
 *
 * @author Mo
 */
public abstract class GameObject {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    
    protected Rectangle hitbox;

    BufferedImage image;

    abstract void gameUpdate();

    abstract void gameRender(Graphics2D g);
    
    
    //Getters and Setter
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
    
    

}
