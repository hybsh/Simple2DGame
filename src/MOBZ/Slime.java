package MOBZ;

import Entity.Entity;
import Main.gamePannel;
import Object.*;

import java.util.Random;

public class Slime extends Entity {
    gamePannel gp;
    public Slime(gamePannel gp){
        super(gp);
        this.gp = gp;
        type = type_mob;
        name = "Slime";
        speed = 1;
        maxLife = 3;
        life = maxLife;
        attack = 5;
        defense = 0;
        XP = 2;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        up1 = prepImg("/MOBZ/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = prepImg("/MOBZ/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = prepImg("/MOBZ/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = prepImg("/MOBZ/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = prepImg("/MOBZ/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = prepImg("/MOBZ/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = prepImg("/MOBZ/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = prepImg("/MOBZ/greenslime_down_2", gp.tileSize, gp.tileSize);
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
        int i = new Random().nextInt(100) +1;
        if(i > 99 && projectile.alive == false && shotAvailableCounter == 30){
            projectile.set(worldX,worldY,direction,true,this);
            for(int j = 0; j<gp.projectile[1].length; j++){
                if(gp.projectile[gp.currentMap][j] == null){
                    gp.projectile[gp.currentMap][j] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }
    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;
    }

    public void checkDrop(){

        int i = new Random().nextInt(100) + 1;

        if(i < 50){
            dropItem(new OBJ_Gold_Coin(gp));
        }
        if(i >= 50 && i < 75){
            dropItem(new OBJ_Heart(gp));
        }

        if(i >= 75 && i < 100){
            dropItem(new OBJ_Mana(gp));
        }
    }
}
