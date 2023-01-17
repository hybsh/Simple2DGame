package Entity;

import Main.gamePannel;
import Object.*;

import java.util.Random;

public class NPC_Merchant extends Entity{
    public NPC_Merchant(gamePannel gp) {
        super(gp);
        direction = "down";
        speed = 0;

        getNPCImage();
        setDialogue();
        setItems();
    }

    public void getNPCImage() {

        up1 = prepImg("/NPCs/merchant_down_1", gp.tileSize, gp.tileSize);
        up2 = prepImg("/NPCs/merchant_down_2", gp.tileSize, gp.tileSize);
        down1 = prepImg("/NPCs/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = prepImg("/NPCs/merchant_down_2", gp.tileSize, gp.tileSize);
        left1 = prepImg("/NPCs/merchant_down_1", gp.tileSize, gp.tileSize);
        left2 = prepImg("/NPCs/merchant_down_2", gp.tileSize, gp.tileSize);
        right1 = prepImg("/NPCs/merchant_down_1", gp.tileSize, gp.tileSize);
        right2 = prepImg("/NPCs/merchant_down_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0] = "What you wanna trade fam?";
    }
    public void setItems(){
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Shield_Blue(gp));
    }
    public void speak(){
        super.speak();
        gp.gameState = gp.tradeState;
        gp.UI.merchant = this;
    }
}
