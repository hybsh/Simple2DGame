package Object;

import Entity.Projectile;
import Main.gamePannel;
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
    }
