/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Mo
 */
public class Pixel extends GameObject{
    
    private BufferedImage bufferedImage;
    
    public Pixel(){
        bufferedImage = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
        
        Color[] arrayOfColor = {Color.WHITE};
        Rectangle pointRectangle = new Rectangle(0,0,1,1);
        
//        bufferedImage.setData( <Color>(0,pointRectangle, arrayOfColor, 0, 1) );
//        bufferedImage.setData(  );
    }
    
    @Override
    void gameUpdate() {
    }

    @Override
    void gameRender(Graphics2D g) {
        g.drawImage(bufferedImage, null, x, y);
    }
    
}
