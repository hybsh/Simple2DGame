package Object;
import Entity.Entity;
import Main.*;

public class OBJ_Tent extends Entity {
    gamePannel gp;

    public OBJ_Tent(gamePannel gp){
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Tent";
        down1 = prepImg("/objects/tent",gp.tileSize,gp.tileSize);
        description = "Use this to make day!";
        price = 300;
        stackable = true;
    }
    public boolean use(Entity entity){
        gp.gameState = gp.sleepState;
        gp.player.life = gp.player.maxLife;
        gp.player.mana = gp.player.maxMana;
        gp.player.getSleepingImage(down1);
        return true;
    }
}
