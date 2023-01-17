package Object;

import Entity.Entity;
import Main.gamePannel;

public class OBJ_SWORD_NORMAL extends Entity {

    public OBJ_SWORD_NORMAL(gamePannel gp){
        super(gp);
        type = type_sword;
        name = "Normal Sword";
        down1 = prepImg("/Objects/sword_normal", gp.tileSize, gp.tileSize);
        attackArea.width = 36;
        attackArea.height = 36;
        attackVal = 1;
        description = "[" + name + "]\nAn ancient sword";
        knockbackPower = 2;
    }
}
