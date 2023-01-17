package Object;

import Entity.Entity;
import Main.gamePannel;

public class OBJ_Shield_Blue extends Entity {
    public OBJ_Shield_Blue(gamePannel gp) {
        super(gp);
        type = type_shield;
        name = "Blue shield";
        down1 = prepImg("/Objects/shield_blue",gp.tileSize,gp.tileSize);
        defenseVal = 2;
        description ="Best shield in the game";
    }
}
