package Object;

import Main.gamePannel;
import Entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends Entity {
    gamePannel gp;
    Entity loot;
    boolean opened = false;

    public OBJ_Chest(gamePannel gp, Entity loot){
        super(gp);
        this.gp = gp;
        this.loot = loot;

        type = type_obstacle;
        name = "Chest";
        image = prepImg("/Objects/chest", gp.tileSize, gp.tileSize);
        image2 = prepImg("/Objects/chest_opened", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void interact(){
        gp.gameState =  gp.dialogueState;
        if(opened == false){
            //play SFX
            StringBuilder sb = new StringBuilder();
            sb.append("You open the chest and find a " + loot.name + "!");
            if(gp.player.inventory.size() == gp.player.maxInvSize){
                sb.append("\n ...But you cannot carry any more items!");
            }
            else{
                sb.append("\n You obtain the loot for free!");
                gp.player.inventory.add(loot);
                opened = true;
                down1 = image2;
            }
            gp.UI.currentDialogue = sb.toString();
        }
        else{
            gp.UI.currentDialogue = "It's empty!";
        }
    }
}
