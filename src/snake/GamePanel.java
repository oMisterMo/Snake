/* 
 * Copyright (C) 2019 Mohammed Ibrahim
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package snake;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.*;

/**
 * Game screen which ties together all classes (15 ‎May ‎2016).
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public class GamePanel extends JPanel implements Runnable {

    //GAMES WIDTH & HEIGHT
    /**
     * Width of the screen
     */
    public static final int GAME_WIDTH = 600;

    /**
     * Height of the screen
     */
    public static final int GAME_HEIGHT = 600;

    private Thread thread;
    private boolean running;
    private BufferedImage image;
    private Graphics2D g;

    private final int FPS = 25;
    private long averageFPS;

    //GAME VARIABLES HERE
    private World world;
    private Snake snake;
    private Color backgroundColor;    //Represents colour of background

    //CONSTRUCTOR
    /**
     * Creates a new game screen with default positions.
     */
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        setFocusable(true);
        requestFocus(); //-> platform dependant

        //initialise varialbes
        init();
    }

    private void init() {
        world = new World();
        snake = new Snake(world);

        //square = new Square(100, 100, 150, 150);
        backgroundColor = new Color(0, 0, 0);    //Represents colour of background
        //backgroundColor = new Color(100, 149, 237);    //Represents colour of background
//        food = new Food();

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

        running = true;

        image = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        //GAME LOOP
        while (running) {
            startTime = System.nanoTime();
            gameUpdate();
            gameRender(g);
            gameDraw();

            //How long it took to run
            timeTaken = (System.nanoTime() - startTime) / 1000000;
            //16ms - targetTime
            waitTime = targetTime - timeTaken;

            //System.out.println(timeTaken);
            if (waitTime < 0) {
                //I get a negative value at the beg
                System.out.println("NEGATIVE: " + waitTime);
                System.out.println("targetTime = " + targetTime);
                System.out.println("timeTaken = " + timeTaken + "\n");
            }

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
        //world.gameUpdate();
        snake.gameUpdate();
    }

    private void gameRender(Graphics2D g) {
        //Clear screen
        g.setColor(backgroundColor);
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

//        //Any test code here
//        g.drawString("getRGB() ->   " + backgroundColor.getRGB(), 450, 50);
//        g.drawString("Red: " + backgroundColor.getRed() + " ", 450, 100);
//        g.drawString("Green: " + backgroundColor.getGreen() + " ", 450, 150);
//        g.drawString("Blue: " + backgroundColor.getBlue() + " ", 450, 200);
        //********** Do drawings HERE **********
        world.gameRender(g);
        //Draw text information
        g.setColor(Color.WHITE);
        g.drawString("FPS:" + averageFPS, 10, 20);

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
            //Handle snake from world movement
            snake.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    //Getters and Setters
    /**
     *
     * @return
     */
    public Color getColor() {
        return backgroundColor;
    }

}
