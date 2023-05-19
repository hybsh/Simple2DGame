package Object;

import Entity.Entity;
import Interfaces.Useable;
import Main.gamePannel;

public class OBJ_Mana extends Entity implements Useable {
    gamePannel gp;
    public OBJ_Mana(gamePannel gp) {
        super(gp);
        this.gp = gp;
        type = type_pickUpOnly;
        down1 = prepImg("/Objects/manacrystal_full", gp.tileSize,gp.tileSize);
        value = 1;
        name = "Mana crystal";
        image = prepImg("/Objects/manacrystal_full", gp.tileSize,gp.tileSize);
        image2 = prepImg("/Objects/manacrystal_blank", gp.tileSize,gp.tileSize);

    }

    public boolean use(Entity entity){
        gp.UI.addMessage("Mana + " + value);
        entity.mana += value;
        return true;
    }
}
