package snake;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 16/05/2016
 * 
 * @author Mo
 */
public class GamePanel extends JPanel implements Runnable {

    //GAMES WIDTH & HEIGHT
    public static final int GAME_WIDTH = 600;
    public static final int GAME_HEIGHT = 600;

    private Thread thread;
    private boolean running;
    private BufferedImage image;
    private Graphics2D g;

    private final int FPS = 60;
    private long averageFPS;
    //dont need 
    
    private int counter = 0;


    //GAME VARIABLES HERE
    private Player player;
    private Square square;
    private Color backgroundColor;    //Represents colour of background

    //CONSTRUCTOR
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        setFocusable(true);
        //System.out.println(requestFocusInWindow());
        requestFocus(); //-> platform dependant
        
        //initialise varialbes
        init();
    }
    
    private void init(){
        player = new Player();
        //player = new Player(50, GAME_HEIGHT - 100, 15, 15);
        square = new Square(100, 100, 150, 150);
        backgroundColor = new Color(100, 149, 237);    //Represents colour of background
        
        addKeyListener(new TAdapter());
    }

    //METHODS
    
    /*
     Is called after our JPanel has been added to the JFrame component.
    */
    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        long startTime;
        long timeTaken;
        long frameCount = 0;
        long totalTime = 0;
        long waitTime;
        long targetTime = 1000 / FPS;
        
        //To test independent speed
        long start2 = 0;
        long timeMillis2 = 0;
        long timeMillis3 = 0;
        long timeMillis4 = 0;

        running = true;

        image = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        //GAME LOOP
        while (running) {
            startTime = System.nanoTime();
            
            
            
            start2 = System.nanoTime();
            gameUpdate();
            timeMillis2 = (System.nanoTime() - start2) / 1000000;

            start2 = System.nanoTime();
            gameRender(g);
            timeMillis3 = (System.nanoTime() - start2) / 1000000;

            start2 = System.nanoTime();
            gameDraw();
            timeMillis4 = (System.nanoTime() - start2) / 1000000;
            
//            gameUpdate();
//            gameRender(g);
//            gameDraw();
            
            //How long it took to run
            timeTaken = (System.nanoTime() - startTime) / 1000000;
            //16ms - targetTime
            waitTime = targetTime - timeTaken;

            //System.out.println(timeTaken);
            if (waitTime < 0) {
                //I get a negative value at the beg
                System.out.println(counter++ + " - NEGATIVE: " + waitTime);
                System.out.println("targetTime = "+ targetTime);
                System.out.println("timeTaken = " + timeTaken+"\n");
            }
            
//            //Speed TEST methods
//            if(frameCount >= 58) {
//                //Test the time taken to run
//                System.out.println("Process input time: " + timeMillis2);
//                System.out.println("Update time: " + timeMillis3);
//                System.out.println("Draw time: " + timeMillis4);
//                System.out.println("------------------------------------------");
//            }
            
            try {
                //System.out.println("Sleeping for: " + waitTime);
                //thread.sleep(waitTime);
                Thread.sleep(waitTime);
            } catch (Exception ex) {

            }
            totalTime += System.nanoTime() - startTime;
            frameCount++;

            //If the current frame == 60  we calculate the average frame count
            if (frameCount >= FPS) {
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                //System.out.println("Average fps: " + averageFPS);
            }
        }

    }

    private void gameUpdate() {
        
        //********** Do updates HERE **********
        player.gameUpdate();
        square.gameUpdate();
    }

    private void gameRender(Graphics2D g) {
        g.setColor(backgroundColor);

        //g.setColor(Color.WHITE);
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawString("FPS:" + averageFPS, 30, 50);

//        //Any test code here
//        g.drawString("getRGB() ->   " + backgroundColor.getRGB(), 450, 50);
//        g.drawString("Red: " + backgroundColor.getRed() + " ", 450, 100);
//        g.drawString("Green: " + backgroundColor.getGreen() + " ", 450, 150);
//        g.drawString("Blue: " + backgroundColor.getBlue() + " ", 450, 200);

        
        
        
        //********** Do drawings HERE **********
        player.gameRender(g);
        square.gameRender(g);

    }

    private void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }
    
    
    
    //Handle Input ** Inner Class
    private class TAdapter extends KeyAdapter {

        //When a key is pressed, let the CRAFT class deal with it.
        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
            
            square.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
            
            square.keyReleased(e);
        }
    }
    
    //Getters and Setters

    //Getters and Setters
    public Player getPlayer() {
        return player;
    }
    
    public Color getColor(){
        return backgroundColor;
    }

}
