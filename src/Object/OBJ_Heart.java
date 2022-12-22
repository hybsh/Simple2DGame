package Object;

import Main.gamePannel;

import javax.imageio.ImageIO;
import java.io.IOException;
import Entity.Entity;

public class OBJ_Heart extends Entity{
    gamePannel gp;
    public OBJ_Heart(gamePannel gp){
        super(gp);
        this.gp = gp;
        name = "Heart";
        value = 2;
        type = type_pickUpOnly;
        down1 = prepImg("/Objects/heart_full", gp.tileSize, gp.tileSize);
        image = prepImg("/Objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = prepImg("/Objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = prepImg("/Objects/heart_blank", gp.tileSize, gp.tileSize);
    }
    public void use(Entity entity){
        gp.UI.addMessage("Life + " + value);
        entity.life += value;
    }
}
