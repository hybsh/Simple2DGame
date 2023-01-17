package src.test;
import Entity.Player;
import Main.gamePannel;
import Main.keyHandler;
import Object.*;
import Entity.NPC_Merchant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class merchantInvTest {

    @Test
    public void testInv(){

        gamePannel gp = new gamePannel();
        NPC_Merchant test = new NPC_Merchant(gp);
        test.setItems();
        assertEquals(6,test.inventory.size());
        assertEquals("Red Potion",test.inventory.get(0).name);
        assertEquals("Diamond Axe", test.inventory.get(1).name);
    }
}
