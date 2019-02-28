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

import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JSlider;

/**
 * Main Class
 *
 * (15 ‎May ‎2016)
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public class GameMain {

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Variables
        JFrame window = new JFrame("Snake");
        GamePanel game = new GamePanel();

        window.setAlwaysOnTop(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setSize(GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT);
        window.setResizable(false);
        window.add(game);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        setLeftSlider(window);
    }

    private static void setLeftSlider(JFrame window) {
        //Left Slider
        JFrame sliderFrame = new JFrame("FPS Slider");
        JSlider fpsSlider = new JSlider(0, 60);
        fpsSlider.setMajorTickSpacing(10);
        fpsSlider.setMinorTickSpacing(5);
        fpsSlider.setSnapToTicks(true); //snapping
        fpsSlider.setPaintTicks(true);
        fpsSlider.setPaintLabels(true); //Slider value

        sliderFrame.add(fpsSlider);
        sliderFrame.pack();
        Point p = window.getLocationOnScreen();
        //(p.x, p.y) is 0,0 of game frame
        int x = p.x - sliderFrame.getWidth() - 15;
        int y = p.y + window.getHeight() / 2;
        sliderFrame.setLocation(x, y);
        //popUp.setLocationRelativeTo(window); -> centers dialog center
        sliderFrame.setResizable(false);
        sliderFrame.setVisible(true);
    }

}
