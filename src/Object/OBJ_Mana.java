package Object;

import Entity.Entity;
import Main.gamePannel;

public class OBJ_Mana extends Entity {
    gamePannel gp;
    public OBJ_Mana(gamePannel gp) {
        super(gp);
        this.gp = gp;

        name = "Mana crystal";
        image = prepImg("/Objects/manacrystal_full", gp.tileSize,gp.tileSize);
        image2 = prepImg("/Objects/manacrystal_blank", gp.tileSize,gp.tileSize);

    }
}
