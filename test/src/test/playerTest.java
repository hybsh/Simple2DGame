package src.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Main.gamePannel;
import Main.keyHandler;
import Entity.Player;
import Object.*;


public class playerTest {

    @Test
    public void testPlayerConstructor(){
        gamePannel gp = new gamePannel();
        keyHandler keyH = new keyHandler(gp);
        Player test = new Player(gp,keyH);

        assertEquals(1,test.level);
        assertEquals(6,test.life);
        assertEquals(1,test.attack);
        assertEquals(1,test.dexterity);
    }
    @Test
    public void testLvlUp(){
        gamePannel gp = new gamePannel();
        keyHandler keyH = new keyHandler(gp);
        Player test = new Player(gp,keyH);
        test.XP += 12;
        test.checkLvlUp();
        assertEquals(2,test.level);
    }

    @Test
    public void testGetAttack(){
        gamePannel gp = new gamePannel();
        keyHandler keyH = new keyHandler(gp);
        Player test = new Player(gp,keyH);
        assertEquals(1,test.attack);
        test.XP += 12;
        test.checkLvlUp();
        assertEquals(2,test.attack);
    }

}
