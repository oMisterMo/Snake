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

import java.awt.Color;

/**
 * The TileType class is used to represent all enumerations of a tile.
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public enum TileType {

    /**
     * Snakes head
     */
    SnakeHead(Color.GREEN),
    /**
     * Snakes body
     */
    SnakeBody(Color.BLUE),
    /**
     * Snakes food
     */
    Food(Color.RED),
    /**
     * Empty tile
     */
    EMPTY(null);

    private final Color tile;

    /* Constructor is private or protected only */
    private TileType(Color color) {
        tile = color;
    }

    /**
     * Color of a tile
     *
     * @return tiles color
     */
    public Color getColor() {
        return tile;
    }
}
