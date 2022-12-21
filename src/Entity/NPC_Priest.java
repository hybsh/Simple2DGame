package Entity;

import Main.gamePannel;
import Main.utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_Priest extends Entity {

    public NPC_Priest(gamePannel gp) {
        super(gp);
        direction = "down";
        speed = 1;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {

        up1 = prepImg("/NPCs/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = prepImg("/NPCs/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = prepImg("/NPCs/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = prepImg("/NPCs/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = prepImg("/NPCs/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = prepImg("/NPCs/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = prepImg("/NPCs/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = prepImg("/NPCs/oldman_right_2", gp.tileSize, gp.tileSize);
        System.out.println("YES");

    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            System.out.println(i);

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }

    public void setDialogue() {
        dialogues[0] = "Hello, lad";
        dialogues[1] = "My name is Daniel, \nI used to be a patriarch until a silly man \nwith a moustache sent me to this island";
        dialogues[2] = "My mission is to defeat the guardian of the \n island and leave this place";
        dialogues[3] = "Help me and you'll be kindly rewarded";
    }

    public void speak() {
        super.speak();
    }
}
