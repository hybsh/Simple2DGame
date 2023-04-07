package src.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Main.gamePannel;
import Main.keyHandler;
import Entity.Player;
import Object.*;
import Main.utilities;

import java.awt.image.BufferedImage;

public class scaleImageTest {
    @Test
    public void testScale(){
        BufferedImage test = new BufferedImage(10,10,BufferedImage.TYPE_INT_RGB);
        utilities u = new utilities();
        BufferedImage rez = u.scaleImage(test,100,100);
        assertEquals(100,rez.getHeight());
        assertEquals(100,rez.getWidth());

    }
}
