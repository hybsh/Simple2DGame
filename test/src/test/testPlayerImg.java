package src.test;
import Entity.Player;
import Main.gamePannel;
import Main.keyHandler;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;


public class testPlayerImg {

    @Test
    public void testPlayerImg(){
        gamePannel gp = new gamePannel();
        keyHandler kH = new keyHandler(gp);
        Player test = new Player(gp,kH);

        test.getPlayerImage();
        Assert.assertTrue(test.up1 instanceof BufferedImage);
        Assert.assertTrue(test.up2 instanceof BufferedImage);

    }
}
