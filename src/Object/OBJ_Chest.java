package Object;

import Main.gamePannel;
import Entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends Entity {

    public OBJ_Chest(gamePannel gp){
        super(gp);
        name = "Chest";
        down1 = prepImg("/Objects/chest", gp.tileSize, gp.tileSize);
    }
}
