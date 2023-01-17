package src.test;
import static org.junit.jupiter.api.Assertions.*;
import Main.gamePannel;
import Main.keyHandler;
import Entity.Player;
import org.junit.Test;

public class damagePlayerTest {

    @Test
    public void testPlayerDamage(){
        gamePannel gp = new gamePannel();
        keyHandler keyH = new keyHandler(gp);
        Player test = new Player(gp,keyH);
        gp.player = test;
        assertEquals(6,test.life);
        test.damagePlayer(2);
        assertEquals(5,test.life);

    }
}
