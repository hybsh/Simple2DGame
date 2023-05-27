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
        name = "Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 3;
        life = maxLife;
        attack = 5;
        defense = 0;
        XP = 2;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        getAttackImage();
    }

    public void getImage(){
        
        int i = 3;

        up1 = prepImg("/boss/skeletonlord_up_1", gp.tileSize*i, gp.tileSize*i);
        up2 = prepImg("/boss/skeletonlord_up_2", gp.tileSize*i, gp.tileSize*i);
        down1 = prepImg("/boss/skeletonlord_down_1", gp.tileSize*i, gp.tileSize*i);
        down2 = prepImg("/boss/skeletonlord_down_2", gp.tileSize*i, gp.tileSize*i);
        left1 = prepImg("/boss/skeletonlord_left_1", gp.tileSize*i, gp.tileSize*i);
        left2 = prepImg("/boss/skeletonlord_left_2", gp.tileSize*i, gp.tileSize*i);
        right1 = prepImg("/boss/skeletonlord_right_1", gp.tileSize*i, gp.tileSize*i);
        right2 = prepImg("/boss/skeletonlord_right_2", gp.tileSize*i, gp.tileSize*i);
    }
    public void getAttackImage(){

        int i = 3;
        
        attackup1 = prepImg("/boss/skeletonlord_attack_up_1", gp.tileSize*i, gp.tileSize*i * 2);
        attackup2 = prepImg("/boss/skeletonlord_attack_up_2", gp.tileSize*i, gp.tileSize*i * 2);
        attackdown1 = prepImg("/boss/skeletonlord_attack_down_1", gp.tileSize*i, gp.tileSize*i * 2);
        attackdown2 = prepImg("/boss/skeletonlord_attack_down_2", gp.tileSize*i, gp.tileSize*i * 2);
        attackleft1 = prepImg("/boss/skeletonlord_attack_left_1", gp.tileSize*i * 2, gp.tileSize*i);
        attackleft2 = prepImg("/boss/skeletonlord_attack_left_2", gp.tileSize*i * 2, gp.tileSize*i);
        attackright1 = prepImg("/boss/skeletonlord_attack_right_1", gp.tileSize*i * 2, gp.tileSize*i);
        attackright2 = prepImg("/boss/skeletonlord_attack_right_2", gp.tileSize*i * 2, gp.tileSize*i);
        }

    public void setAction(){
       move_to_player();
       if(attacking == false){
           checkAttack(30, gp.tileSize*4, gp.tileSize);
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
