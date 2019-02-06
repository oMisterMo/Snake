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
import java.awt.Rectangle;

/**
 * Represents any object which is drawn to the screen.
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public abstract class GameObject {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Rectangle hitbox;

    abstract void gameUpdate();

    abstract void gameRender(Graphics2D g);

    /**
     * Sets the position and size of the game object.
     *
     * @param x the x position to set
     * @param y the y position to set
     * @param width the width to set
     * @param height the height to set
     */
    public void setGameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the x position of the game object
     *
     * @return x position
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y position of the game object
     *
     * @return y position
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the width of the game object
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the game object
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the bounding rectangle of the current game object
     *
     * @return
     */
    public Rectangle getHitbox() {
        return hitbox;
    }
}
