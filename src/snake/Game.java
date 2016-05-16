/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;
import java.awt.Dimension;
import javax.swing.JFrame;
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
        // TODO code application logic here
        JFrame window = new JFrame("First Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GamePanel game = new GamePanel();
        
        window.add(game);
        
        window.pack();
        
//        //To fix frame size
//        System.out.println("frame height: "+window.getHeight());
//        System.out.println("content pane width : "+window.getContentPane().getWidth());
//        System.out.println("content pane height: "+window.getContentPane().getHeight());
//        System.out.println("width  of left + right  borders: "+(window.getWidth()-window.getContentPane ().getWidth()));
//        System.out.println("height of top  + bottom borders: "+(window.getHeight()-window.getContentPane().getHeight()));
        
        //window.setSize(GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);
    }
    
}
