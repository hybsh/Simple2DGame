package Object;

import Entity.Entity;
import Interfaces.Useable;
import Main.gamePannel;

public class OBJ_Potion_Red extends Entity implements Useable {
    gamePannel gp;
    public OBJ_Potion_Red(gamePannel gp) {
        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = "Red Potion";
        value = 2;
        down1 = prepImg("/Objects/potion_red", gp.tileSize,gp.tileSize);
        description = "This might heal you";
        price = 20;
    }
    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.UI.currentDialogue = "You drink the potion! \n Luckily you are not dead!";
        entity.life += value;
    }
}
