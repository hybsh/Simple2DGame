package Object;

import Main.gamePannel;

import javax.imageio.ImageIO;
import java.io.IOException;
import Entity.Entity;

public class OBJ_Heart extends Entity{

    public OBJ_Heart(gamePannel gp){
        super(gp);
        name = "Heart";
        image = prepImg("/Objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = prepImg("/Objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = prepImg("/Objects/heart_blank", gp.tileSize, gp.tileSize);

    }
}
