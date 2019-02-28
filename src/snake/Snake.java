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

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * The Snake class represents the dynamic snake that has to navigate through the
 * world finding food (08-Jun-2016, 18:57:32).
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public class Snake extends GameObject {

    private World world;    //world reference

    //Movement direction
    /**
     * Up state
     */
    public static final int UP = 0;

    /**
     * Down state
     */
    public static final int DOWN = 1;

    /**
     * Left state
     */
    public static final int LEFT = 2;

    /**
     * Right state
     */
    public static final int RIGHT = 3;
    private int currentState;

    //Actual snake size
    LinkedList<Point> snake;
    //Current Poition
    Point head;
    Point temp;

    private Random ran;

    /**
     * Creates a snake with default positioning.
     *
     * @param world reference to the world the snake lives in
     */
    public Snake(World world) {
        this.world = world;
        snake = new LinkedList();

        head = new Point(10, 10);
        snake.add(head);
        world.setTile(head.x, head.y, TileType.SnakeHead);
        currentState = Snake.RIGHT;
        temp = new Point(0, 0);

        ran = new Random();
    }

    /**
     * Set snakes state when a key is pressed.
     *
     * @param e key event
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        //stop the snake from turning back into itself
        if (currentState == Snake.UP && key == KeyEvent.VK_DOWN) {
            return;
        }
        if (currentState == Snake.LEFT && key == KeyEvent.VK_RIGHT) {
            return;
        }
        if (currentState == Snake.RIGHT && key == KeyEvent.VK_LEFT) {
            return;
        }
        if (currentState == Snake.DOWN && key == KeyEvent.VK_UP) {
            return;
        }

        //Update current state depending on movement
        if (key == KeyEvent.VK_UP) {
            currentState = Snake.UP;
        } else if (key == KeyEvent.VK_LEFT) {
            currentState = Snake.LEFT;
        } else if (key == KeyEvent.VK_RIGHT) {
            currentState = Snake.RIGHT;
        } else if (key == KeyEvent.VK_DOWN) {
            currentState = Snake.DOWN;
        }
    }

    @Override
    void gameUpdate() {
        //Handle Movement
        Point tempHead = snake.getFirst();
        switch (currentState) {
            case Snake.DOWN:
                /*DON'T NEED TO CREATE NEW POINT EVERY MOVE*/
                //Push to front of the stack
                snake.push(new Point(tempHead.x, tempHead.y + 1));
                break;
            case Snake.LEFT:
                snake.push(new Point(tempHead.x - 1, tempHead.y));
                break;
            case Snake.RIGHT:
                snake.push(new Point(tempHead.x + 1, tempHead.y));
                break;
            case Snake.UP:
                snake.push(new Point(tempHead.x, tempHead.y - 1));
                break;
        }

        //Update our head variable with new head position
        head = snake.getFirst();
        //To the right of screen
        if (head.x > World.NO_OF_TILES_X - 1) {
            head.x = 0;
        }
        //To the botom
        if (head.y > World.NO_OF_TILES_Y - 1) {
            head.y = 0;
        }
        //To the left
        if (head.x < 0) {
            head.x = World.NO_OF_TILES_X - 1;
        }
        //To the top
        if (head.y < 0) {
            head.y = World.NO_OF_TILES_Y - 1;
        }

        //if the current head is not a food
        if (!isFood(head.x, head.y)) {
            //Remove our old head value
            //System.out.println("notFood");
            Point previous = snake.removeLast();
            world.setTile(previous.x, previous.y, TileType.EMPTY);
        } else {
            spawnFood();
        }

        //Update the new head
        world.setTile(head.x, head.y, TileType.SnakeHead);
        //Add the body that follows head

        /* Handle food spawn */
    }

    @Override
    void gameRender(Graphics2D g) {
        //Do nothing
    }

    private void spawnFood() {
        int x = ran.nextInt(World.NO_OF_TILES_X);
        int y = ran.nextInt(World.NO_OF_TILES_Y);

        //While the current tile is not empty
        while (!isEmpty(x, y)) {
            /*Later in the game, we could be stuck here a while looking for free slots*/
            int num = 0;
            System.out.println("CURRENT TILE NOT EMPTY FOUND NEW SPAWN: ");
            x = ran.nextInt(World.NO_OF_TILES_X);
            y = ran.nextInt(World.NO_OF_TILES_Y);
            System.out.println("x: " + x + " y: " + y);
            System.out.println("Times looked: " + ++num);
        }

        setFood(x, y);
    }

    private void setFood(int x, int y) {
        world.setTile(x, y, TileType.Food);
    }

    private void setHead(int x, int y) {
        world.setTile(x, y, TileType.SnakeHead);
    }

    private void setBody(int x, int y) {
        world.setTile(x, y, TileType.SnakeBody);
    }

    /**
     * Checks whether a given tile is a snake body.
     *
     * @param x the x index to check
     * @param y the y index to check
     * @return true is the given arguments is a snake body
     */
    public boolean isBody(int x, int y) {
        return world.getTile(x, y).equals(TileType.SnakeBody);
    }

    /**
     * Checks whether a given tile is a food tile.
     *
     * @param x the x index to check
     * @param y the y index to check
     * @return true is the given arguments is a food tile
     */
    public boolean isFood(int x, int y) {
        return world.getTile(x, y).equals(TileType.Food);
    }

    /**
     * Checks whether a given tile is a empty tile.
     *
     * @param x the x index to check
     * @param y the y index to check
     * @return true is the given arguments is a empty tile
     */
    public boolean isEmpty(int x, int y) {
        return world.getTile(x, y).equals(TileType.EMPTY);
    }

}
