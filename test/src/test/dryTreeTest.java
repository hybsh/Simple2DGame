package src.test;
import static org.junit.jupiter.api.Assertions.*;

import InteractiveTile.IT_Dry_Tree;
import Main.gamePannel;
import Main.keyHandler;
import Entity.Player;
import Object.*;
import org.junit.Test;
public class dryTreeTest {
    @Test
    public void testCorrectWeapon(){
        gamePannel gp = new gamePannel("user");
        keyHandler kH = new keyHandler(gp);
        Player test = new Player(gp,kH,"user");
        IT_Dry_Tree tree = new IT_Dry_Tree(gp,10,10);
        assertEquals(false, tree.useCorrectItem(test));
        test.currentWeapon = new OBJ_Axe(gp);
        assertEquals(true, tree.useCorrectItem(test));
    }
}
