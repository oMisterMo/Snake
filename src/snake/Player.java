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
import java.util.Random;

/**
 * 16/05/2016
 * 
 * @author Mo
 */
public class Player extends GameObject {
    
    private int dx;
    private int dy;
    
    private Random ran;
    private Color color;
    private int red;
    private int green;
    private int blue;
    
    //Movement direction
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    private int currentState;
    
    private int score;
    
//    //Body Size -> WILL ALWAYS BE THE SAME AS A SINGLE TILE
//    public static final int BODY_WIDTH = 10;
//    public static final int BODY_HEIGHT = 10;
    
    /**
     * Default starting position and body size
     */
    public Player(){
        this.x = GamePanel.GAME_WIDTH/2;
        this.y = GamePanel.GAME_HEIGHT/2;
        this.width = World.TILE_WIDTH;
        this.height = World.TILE_HEIGHT;
        
        this.hitbox = new Rectangle(x, y, width, height);
        
        ran = new Random();
        red = 15;
        green = 15;
        blue = 15;
        color = new Color(red, green, blue);
        
        //Speed/direction player moves
        currentState= Player.RIGHT;
        dx = 0;
        dy = 0;
        
        score = 0;
    }
    
    /**
     * Custom snake variables
     * 
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(x, y, width, height);
        
        /*
         set color of grahpic object
         -> SexyOrange (255, 255, 140)
         */
        ran = new Random();
        red = 255;
        green = 255;
        blue = 140;
        color = new Color(red, green, blue);
        
        
        //Speed/direction player moves
        currentState= Player.RIGHT;
        dx = 0;
        dy = 0;
        
        score = 0;
    }

    //Move when key is pressed
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            dy = -1;
            currentState = Player.UP;
        }
        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
            currentState = Player.LEFT;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
            currentState = Player.RIGHT;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
            currentState = Player.DOWN;
        }
        
        //Increment color depending on button pressed
        if (key == KeyEvent.VK_R){
//            if(red>=255){
//                red = 0;
//            }
//            red++;
            //0<= red < 256
            red = ran.nextInt(256);
        }
        if (key == KeyEvent.VK_G){
//            if(green>=255){
//                green = 0;
//            }
//            green++;
            green = ran.nextInt(256);
        }
        if (key == KeyEvent.VK_B){
//            if(blue>=255){
//                blue = 0;
//            }
//            blue++;
            blue = ran.nextInt(256);
        }
        color = new Color(red, green, blue);
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
    }

    @Override
    void gameUpdate() {
        //Handle Movement
        switch(currentState){
            case Player.DOWN:
                //Movement here
                y += World.TILE_HEIGHT *0.3;
                break;
            case Player.LEFT:
                x -= World.TILE_WIDTH*0.3;
                break;
            case Player.RIGHT:
                x += World.TILE_WIDTH*0.3;
                break;
            case Player.UP:
                y -= World.TILE_HEIGHT*0.3;
                break;
        }
        
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
//        if (x < GamePanel.GAME_WIDTH - width || y < GamePanel.GAME_HEIGHT - height) {
//            x = GamePanel.GAME_WIDTH - width;
//            y = GamePanel.GAME_HEIGHT - height;
//        }
        

//        x+=dx;
//        y+=dy;
    }

    @Override
    void gameRender(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    
    //Getters and Setters
    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public void setRed(int red) {
        if(red < 0 || red > 255){
            System.out.println("Can't be >255 or < 0");
            red = 0;
        }
        this.red = red;
        color = new Color(red, green, blue);
    }

    public void setGreen(int green) {
        if(green < 0 || green > 255){
            System.out.println("Can't be >255 or < 0");
            green = 0;
        }
        this.green = green;
        color = new Color(red, green, blue);
    }

    public void setBlue(int blue) {
        if(blue < 0 || blue > 255 ){
            System.out.println("Can't be >255 or < 0");
            blue = 0;
        }
        this.blue = blue;
        color = new Color(red, green, blue);
    }
}
