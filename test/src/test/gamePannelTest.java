package src.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Main.gamePannel;
import Main.keyHandler;
import Entity.Player;
import Object.*;

public class gamePannelTest {
    @Test
    public void testRetry(){
        gamePannel gp = new gamePannel("user");
        keyHandler kH = new keyHandler(gp);
        Player test = new Player(gp,kH,"user");
        test.XP += 10;
        test.checkLvlUp();
        gp.retry();
        assertEquals(2,test.level);
    }
}
