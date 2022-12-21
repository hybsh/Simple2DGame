package Object;

import Main.gamePannel;

import javax.imageio.ImageIO;
import java.io.IOException;
import Entity.Entity;

public class OBJ_Key extends Entity {

    public OBJ_Key(gamePannel gp){
        super(gp);
        name = "Key";
        down1 = prepImg("/Objects/key", gp.tileSize, gp.tileSize);
        description = "["+name+"]\nYou can open doors \nwith this stuff";
    }
}
