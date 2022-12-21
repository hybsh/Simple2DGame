package Object;

import Entity.Entity;
import Main.gamePannel;

public class OBJ_Potion_Red extends Entity {
    int value = 1;
    gamePannel gp;

    public OBJ_Potion_Red(gamePannel gp) {
        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = "Red Potion";
        down1 = prepImg("/Objects/potion_red", gp.tileSize,gp.tileSize);
        description = "Drink to heal noob";
    }
    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.UI.currentDialogue = "You drink the potion! \n Good job you cunt!";
        entity.life += value;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }

    }
}
