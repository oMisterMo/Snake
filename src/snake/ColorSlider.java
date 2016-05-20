/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 19-May-2016, 02:20:05.
 *
 * @author Mo
 */
public class ColorSlider extends JPanel implements ChangeListener {

    private static final int MINOR_SPACING = 10;
    private static final int MAJOR_SPACING = 50;

    Player player;

    JSlider red = new JSlider(0, 255);
    JSlider green = new JSlider(0, 255);
    JSlider blue = new JSlider(0, 255);

    public ColorSlider(Player player) {
        this.player = player;
        
        //setLayout(new FlowLayout(FlowLayout.LEFT));
        setLayout(new GridLayout(3, 0));

        //Set red
        red.setMajorTickSpacing(MAJOR_SPACING);
        red.setMinorTickSpacing(MINOR_SPACING);
        //red.setSnapToTicks(true); //snapping
        red.setPaintTicks(true);
        red.setPaintLabels(true); //Slider value
        red.setValue(player.getRed());
        JPanel panel = new JPanel();
        //panel.setBackground(Color.RED);
        panel.add(new JLabel("Red"));
        panel.add(red);

        //Set green
        green.setMajorTickSpacing(MAJOR_SPACING);
        green.setMinorTickSpacing(MINOR_SPACING);
        //green.setSnapToTicks(true); //snapping
        green.setPaintTicks(true);
        green.setPaintLabels(true); //Slider value
        green.setValue(player.getGreen());
        JPanel panel2 = new JPanel();
        //panel2.setBackground(Color.GREEN);
        panel2.add(new JLabel("Green"));
        panel2.add(green);

        //set blue
        blue.setMajorTickSpacing(MAJOR_SPACING);
        blue.setMinorTickSpacing(MINOR_SPACING);
        //blue.setSnapToTicks(true); //snapping
        blue.setPaintTicks(true);
        blue.setPaintLabels(true); //Slider value
        blue.setValue(player.getBlue());
        JPanel panel3 = new JPanel();
        //panel3.setBackground(Color.BLUE);
        panel3.add(new JLabel("Blue"));
        panel3.add(blue);

        add(panel);
        add(panel2);
        add(panel3);
    }

    public void registerListerners() {
        red.addChangeListener(this);
        green.addChangeListener(this);
        blue.addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        
        //If the slider is not adjusting
        if (!source.getValueIsAdjusting()) {
            int value = (int) source.getValue();

            if (source == red) {
                System.out.println("Red: " + value);
                player.setRed(value);
            } else if (source == green) {
                System.out.println("Green: " + value);
                player.setGreen(value);
            } else {
                System.out.println("Blue: " + value);
                player.setBlue(value);
            }
        }
    }
}
