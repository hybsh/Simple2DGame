package Object;

import Main.gamePannel;

import javax.imageio.ImageIO;
import java.io.IOException;
import Entity.Entity;

public class OBJ_Key extends Entity {
    gamePannel gp;
    public OBJ_Key(gamePannel gp){
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Key";
        down1 = prepImg("/Objects/key", gp.tileSize, gp.tileSize);
        description = "["+name+"]\nYou can open doors \nwith this stuff";
    }
    public boolean use(Entity entity){
        gp.gameState = gp.dialogueState;

        int  objIndex = getDetected(entity, gp.obj, "Door");

            if(objIndex != 999) {
                //play SFX
                gp.UI.currentDialogue = "You use the key and open the door";
                gp.obj[gp.currentMap][objIndex] = null;
                return true;
            }
            else{
                gp.UI.currentDialogue = "What are you doing??";
                return false;
            }
    }
}
