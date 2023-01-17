package src.test;
import Entity.Player;
import Main.gamePannel;
import Main.keyHandler;
import Object.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class pickUpCoinObjectsTest {

    @Test
    public void testPickUpObject(){

        gamePannel gp = new gamePannel("user");
        keyHandler keyH = new keyHandler(gp);
        Player test = new Player(gp,keyH,"user");
        gp.player = test;

        OBJ_Heart testHeart = new OBJ_Heart(gp);
        test.life = 3 ;
        testHeart.use(test);
        assertEquals(5,test.life);

        OBJ_Mana testMana = new OBJ_Mana(gp);
        test.mana = 2;
        testMana.use(test);
        assertEquals(3,test.mana);

        OBJ_Gold_Coin testCoin = new OBJ_Gold_Coin(gp);
        test.money = 0;
        testCoin.use(test);
        assertEquals(test.money,1);
    }


}
