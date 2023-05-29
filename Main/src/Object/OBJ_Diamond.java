package Object;

import Entity.Entity;
import Main.*;

public class OBJ_Diamond extends Entity {
    gamePannel gp;
    public static final String objName = "Diamond";

    public OBJ_Diamond(gamePannel gp){
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        name = objName;
        down1 = prepImg("/Objects/blueheart", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){
        dialogues[0] = "This looks shiny!";
    }
    public boolean use(){
        gp.gameState = gp.cutsceneState;
        gp.csManager.sceneNum = gp.csManager.end;
        return true;
    }
}
