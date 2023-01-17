package Object;

import Main.gamePannel;

import javax.imageio.ImageIO;
import java.io.IOException;
import Entity.Entity;

public class OBJ_PICK extends Entity {
    public OBJ_PICK(gamePannel gp){
        super(gp);
        name = "PICKAXE";
        down1 = prepImg("/Objects/pickaxe", gp.tileSize, gp.tileSize);
    }
}
