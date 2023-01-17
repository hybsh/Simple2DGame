package src.test;
import Entity.Player;
import Main.gamePannel;
import Main.keyHandler;
import MOBZ.Slime;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class slimeTest {

    @Test
    public void testSlimeConstructor(){
        gamePannel gp = new gamePannel("user");
        Slime test = new Slime(gp);

        assertEquals("Slime",test.name);
        assertEquals(1,test.defaultSpeed);
        assertEquals(3,test.maxLife);
        assertEquals(5,test.attack);
    }

    @Test
    public void testDamageReaction(){

        gamePannel gp = new gamePannel("user");
        Slime test = new Slime(gp);
        keyHandler keyH = new keyHandler(gp);
        Player testP = new Player(gp,keyH,"user");
        gp.player = testP;
        test.damageReaction();
        assertEquals("down",test.direction);
    }
}
