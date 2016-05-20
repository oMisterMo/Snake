/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JSlider;
/**
 * Main Class
 * 
 * 25/01/2014
 * 
 * @author Mo
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Variables
        JFrame sliderFrame = new JFrame("FPS Slider");
        JFrame window = new JFrame("First Game");
        JFrame colorFrame = new JFrame("Player Color");
        GamePanel game = new GamePanel();
        
        JSlider fpsSlider = new JSlider(0, 60);
        
        fpsSlider.setMajorTickSpacing(10);
        fpsSlider.setMinorTickSpacing(5);
        fpsSlider.setSnapToTicks(true); //snapping
        fpsSlider.setPaintTicks(true);
        fpsSlider.setPaintLabels(true); //Slider value
        
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(game);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);
        
        
        //Left Slider
        sliderFrame.add(fpsSlider);
        sliderFrame.pack();
        
        Point p = window.getLocationOnScreen();
        //(p.x, p.y) is 0,0 of game frame
        int x = p.x - sliderFrame.getWidth() - 15;
        int y = p.y + window.getHeight()/2;
        
        sliderFrame.setLocation(x, y);
        //popUp.setLocationRelativeTo(window); -> centers dialog center
        sliderFrame.setResizable(false);
        sliderFrame.setVisible(true);
        
        
        //Right Slider
        ColorSlider colorSlider = new ColorSlider(game.getPlayer());
        colorSlider.registerListerners();
        colorFrame.add(colorSlider);
        //colorFrame.add(new ColorSlider());
        colorFrame.pack();
        
        Point p2 = window.getLocationOnScreen();
        //(p.x, p.y) is 0,0 of game frame
        int x2 = p2.x + window.getWidth() + 15;
        int y2 = p2.y + window.getHeight()/2 - colorFrame.getHeight()/2;
        
        colorFrame.setLocation(x2, y2);
        //popUp.setLocationRelativeTo(window); -> centers dialog center
        colorFrame.setResizable(false);
        colorFrame.setVisible(true);
        
        
    }
    
}
