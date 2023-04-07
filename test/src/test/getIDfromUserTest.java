package src.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Main.gamePannel;
import Main.keyHandler;
import Entity.Player;
import Object.*;
import Main.GUI;

public class getIDfromUserTest {
    @Test
    public void testIDfromUser(){
        gamePannel gp = new gamePannel("user");
        GUI g = new GUI();
        g.getIDfromUser("test");
        assertEquals(12, g.currentUserId);
    }
}
