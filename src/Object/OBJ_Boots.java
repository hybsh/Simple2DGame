package Object;

import Main.gamePannel;
import Entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends Entity {

    public OBJ_Boots(gamePannel gp){
        super(gp);
        name = "Boots";
        down1 = prepImg("/Objects/boots", gp.tileSize, gp.tileSize);

    }
}
