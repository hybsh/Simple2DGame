package src.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Main.gamePannel;
import Main.keyHandler;
import Entity.Player;
import Object.*;
import Main.Sound;

public class soundTest {
    @Test
    public void testVolumeScale(){
        gamePannel gp = new gamePannel("user");
        Sound s = new Sound();
        s.setFile(0);
        s.volumeScale = 0;
        s.checkVolume();
        assertEquals(-80f , s.volume);
        s.volumeScale = 3;
        s.checkVolume();
        assertEquals(-5f,s.volume);
    }
}
