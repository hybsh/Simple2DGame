package Object;

import Entity.Projectile;
import Main.gamePannel;

import java.awt.*;

public class OBJ_Rock extends Projectile {
        gamePannel gp;
        public OBJ_Rock(gamePannel gp) {
            super(gp);
            this.gp =gp;

            name = "Rock";
            speed = 8;
            maxLife = 40;
            life = maxLife;
            attack = 2;
            useCost = 1;
            alive = false;
            getImage();
        }
        public void getImage(){
            up1 = prepImg("/Projectile/rock_down_1", gp.tileSize, gp.tileSize);
            up2 = prepImg("/Projectile/rock_down_1", gp.tileSize, gp.tileSize);
            down1 = prepImg("/Projectile/rock_down_1", gp.tileSize, gp.tileSize);
            down2 = prepImg("/Projectile/rock_down_1", gp.tileSize, gp.tileSize);
            left1 = prepImg("/Projectile/rock_down_1", gp.tileSize, gp.tileSize);
            left2 = prepImg("/Projectile/rock_down_1", gp.tileSize, gp.tileSize);
            right1 = prepImg("/Projectile/rock_down_1", gp.tileSize, gp.tileSize);
            right2 = prepImg("/Projectile/rock_down_1", gp.tileSize, gp.tileSize);

        }

    public Color getParticleColor(){
        Color color = new Color(50,50,0);
        return color;
    }
    public int getParticleSize(){
        int size = 10;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }
    public int getMaxLife(){
        int maxLife = 20;
        return maxLife;
    }
    }
