package MOBZ;

import Entity.Entity;
import Main.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Boss extends Entity {
    gamePannel gp;

    int counter = 0;

    int counter_attack = 0;

    public Boss(gamePannel gp) {
        super(gp);
        this.gp = gp;
        type = type_mob;
        boss = true;
        name = "Skeletor";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 3;
        life = maxLife;
        attack = 5;
        defense = 0;
        XP = 2;
        knockbackPower = 5;
        sleep = true;

        int size = gp.tileSize * 3;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48*2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 170;
        attackArea.height = 170;

        getImage();
        getAttackImage();
        setDialogue();
    }

    public void getImage() {

        int i = 3;
        if (inRage == false) {
            up1 = prepImg("/boss/skeletonlord_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = prepImg("/boss/skeletonlord_up_2", gp.tileSize * i, gp.tileSize * i);
            down1 = prepImg("/boss/skeletonlord_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = prepImg("/boss/skeletonlord_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = prepImg("/boss/skeletonlord_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = prepImg("/boss/skeletonlord_left_2", gp.tileSize * i, gp.tileSize * i);
            right1 = prepImg("/boss/skeletonlord_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = prepImg("/boss/skeletonlord_right_2", gp.tileSize * i, gp.tileSize * i);
        }
        else{
            up1 = prepImg("/boss/skeletonlord_phase2_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = prepImg("/boss/skeletonlord_phase2_up_2", gp.tileSize * i, gp.tileSize * i);
            down1 = prepImg("/boss/skeletonlord_phase2_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = prepImg("/boss/skeletonlord_phase2_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = prepImg("/boss/skeletonlord_phase2_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = prepImg("/boss/skeletonlord_phase2_left_2", gp.tileSize * i, gp.tileSize * i);
            right1 = prepImg("/boss/skeletonlord_phase2_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = prepImg("/boss/skeletonlord_phase2_right_2", gp.tileSize * i, gp.tileSize * i);
        }
    }
    public void getAttackImage() {

        int i = 3;
        if (inRage == false) {
            attackup1 = prepImg("/boss/skeletonlord_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackup2 = prepImg("/boss/skeletonlord_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackdown1 = prepImg("/boss/skeletonlord_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackdown2 = prepImg("/boss/skeletonlord_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackleft1 = prepImg("/boss/skeletonlord_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackleft2 = prepImg("/boss/skeletonlord_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
            attackright1 = prepImg("/boss/skeletonlord_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackright2 = prepImg("/boss/skeletonlord_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);
        }
        else {
            attackup1 = prepImg("/boss/skeletonlord_phase2_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackup2 = prepImg("/boss/skeletonlord_phase2_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackdown1 = prepImg("/boss/skeletonlord_phase2_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackdown2 = prepImg("/boss/skeletonlord_phase2_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackleft1 = prepImg("/boss/skeletonlord_phase2_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackleft2 = prepImg("/boss/skeletonlord_phase2_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
            attackright1 = prepImg("/boss/skeletonlord_phase2_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackright2 = prepImg("/boss/skeletonlord_phase2_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);
        }
    }

    public void setAction(){
        if(inRage == false && life < maxLife/2){
            inRage = true;
            getImage();
            getAttackImage();
            defaultSpeed++;
            speed = defaultSpeed;
            attack *=2;
        }
        moveToPlayer(60);
        if(attacking == false){
           checkAttack(60, gp.tileSize*3, gp.tileSize*5);
       }
    }



        public void change_sprite(){
            if (attacking == false){

                counter += 1;
                if (counter == 20) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else
                        this.spriteNum = 1;
                    counter = 0;
                }
            }
        }
        public void setDialogue(){
            dialogues[0] = "No one can steal my treasure!";
            dialogues[1] = "You will die here!";
        }

        public void move_to_player(){
            int posX = worldX + 5 * worldX / 100;
            int posY = worldY + 5 * worldY / 100;
            if (this.direction == "left")
            {
                posX = worldX + 10 * worldX / 100;
                posY = worldY + 5 * worldY / 100;
            }
            if (this.direction == "up")
            {
                posX = worldX + 5 * worldX / 100;
                posY = worldY + 10 * worldY / 100;
            }
            if(posX < gp.player.worldX){
                worldX += speed;
                this.direction = "right";
                change_sprite();
            }
            if(posX > gp.player.worldX){
                worldX -= speed;
                change_sprite();
                this.direction = "left";
            }
            if(posY < gp.player.worldY){
                worldY += speed;
                this.direction = "down";
                if (worldY > 1200) {
                    worldY = 1200;
                }
                else
                    change_sprite();
            }
            if(posY > gp.player.worldY){
                worldY -= speed;
                this.direction = "up";
                change_sprite();
            }
        }
    }
