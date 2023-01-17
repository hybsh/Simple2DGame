package src.test;
import static org.junit.jupiter.api.Assertions.*;
import Main.gamePannel;
import Main.keyHandler;
import Entity.Player;
import Main.userInterface;
import org.junit.Test;

public class userInterfaceTest {

    @Test
    public void testIndexFromInv(){
        gamePannel gp = new gamePannel();
        userInterface ui = new userInterface(gp);

        int ind = ui.getItemIndexFromInv(2,3);
        assertEquals(17,ind);

        ind = ui.getItemIndexFromInv(0,5);
        assertEquals(25,ind);
    }
}
