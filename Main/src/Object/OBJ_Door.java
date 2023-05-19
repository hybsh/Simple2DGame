package Object;

import Main.gamePannel;

import javax.imageio.ImageIO;
import java.io.IOException;
import Entity.Entity;

public class OBJ_Door extends Entity{
    gamePannel gp;
    public OBJ_Door(gamePannel gp){
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Door";
        down1 = prepImg("/Objects/door", gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x=0;
        solidArea.y=16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    @Override
    public void interact() {
        gp.gameState = gp.dialogueState;
        gp.UI.currentDialogue = "You need a key to open this";
    }
}
