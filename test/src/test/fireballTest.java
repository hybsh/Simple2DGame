package src.test;
import static org.junit.jupiter.api.Assertions.*;
import Main.gamePannel;
import Main.keyHandler;
import Entity.Player;
import Object.*;
import org.junit.Test;

public class fireballTest {

    @Test
    public void testHasMana() {
        gamePannel gp = new gamePannel("user");
        keyHandler keyH = new keyHandler(gp);
        OBJ_Fireball test = new OBJ_Fireball(gp);
        Player testP = new Player(gp, keyH,"user");
        gp.player = testP;

        boolean rez = test.hasMana(testP);
        assertEquals(true, rez);

        gp.player.mana = 0;
        rez = test.hasMana(gp.player);
        assertEquals(false, rez);
    }



}
