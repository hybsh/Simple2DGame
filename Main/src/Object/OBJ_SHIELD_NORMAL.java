package Object;

import Entity.Entity;
import Main.gamePannel;

public class OBJ_SHIELD_NORMAL extends Entity {


    public OBJ_SHIELD_NORMAL(gamePannel gp) {
        super(gp);
        type = type_shield;
        name = "Normal Shield";
        down1 = prepImg("/Objects/shield_wood",gp.tileSize,gp.tileSize);
        description = "Worst shield ever";
        defenseVal = 1;
    }
}
