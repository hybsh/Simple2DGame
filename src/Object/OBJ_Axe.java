package Object;

import Entity.Entity;
import Main.gamePannel;

public class OBJ_Axe extends Entity {
    public OBJ_Axe(gamePannel gp) {
        super(gp);
        type = type_axe;
        name = "Diamond Axe";
        down1 = prepImg("/Objects/axe", gp.tileSize, gp.tileSize);
        attackVal = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "Dogshit axe \nFind better lmao \nBetter than sword tho";
    }
}
