package src.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Main.gamePannel;
import Main.keyHandler;
import Entity.Player;
import Object.*;
import Main.userInterface;

public class ui_addMessageTest {
    @Test
    public void testAddMsg(){
        gamePannel gp = new gamePannel("user");
        userInterface ui = new userInterface(gp);
        ui.addMessage("Yaaay");
        assertEquals("Yaaay", ui.message.get(0));
        ui.addMessage("NO");
        assertEquals("NO",ui.message.get(1));
    }
}
