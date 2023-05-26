package MOBZ;

import Entity.Entity;
import Main.*;

import java.util.Random;

public class Boss extends Entity {
    gamePannel gp;

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

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        getAttackImage();
    }

    public void getImage(){
        
        int i = 5;

        up1 = prepImg("/boss/skeletonlord_down_1", gp.tileSize*i, gp.tileSize*i);
        up2 = prepImg("/boss/skeletonlord_down_2", gp.tileSize*i, gp.tileSize*i);
        down1 = prepImg("/boss/skeletonlord_down_1", gp.tileSize*i, gp.tileSize*i);
        down2 = prepImg("/boss/skeletonlord_down_2", gp.tileSize*i, gp.tileSize*i);
        left1 = prepImg("/boss/skeletonlord_down_1", gp.tileSize*i, gp.tileSize*i);
        left2 = prepImg("/boss/skeletonlord_down_2", gp.tileSize*i, gp.tileSize*i);
        right1 = prepImg("/boss/skeletonlord_down_1", gp.tileSize*i, gp.tileSize*i);
        right2 = prepImg("/boss/skeletonlord_down_2", gp.tileSize*i, gp.tileSize*i);
    }
    public void getAttackImage(){

        int i = 5;
        
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
//        int i = new Random().nextInt(100) +1;
//        if(i > 99 && projectile.alive == false && shotAvailableCounter == 30){
//            projectile.set(worldX,worldY,direction,true,this);
//            for(int j = 0; j<gp.projectile[1].length; j++){
//                if(gp.projectile[gp.currentMap][j] == null){
//                    gp.projectile[gp.currentMap][j] = projectile;
//                    break;
//                }
//            }
//            shotAvailableCounter = 0;
        }
    }
