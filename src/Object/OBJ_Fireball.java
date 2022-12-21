package Object;

import Entity.Projectile;
import Main.gamePannel;

public class OBJ_Fireball extends Projectile {
    gamePannel gp;
    public OBJ_Fireball(gamePannel gp) {
        super(gp);
        this.gp =gp;

        name = "Fireball";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage(){
        up1 = prepImg("/Projectile/fireball_up_1", gp.tileSize, gp.tileSize);
        up2 = prepImg("/Projectile/fireball_up_2", gp.tileSize, gp.tileSize);
        down1 = prepImg("/Projectile/fireball_down_1", gp.tileSize, gp.tileSize);
        down2 = prepImg("/Projectile/fireball_down_2", gp.tileSize, gp.tileSize);
        left1 = prepImg("/Projectile/fireball_left_1", gp.tileSize, gp.tileSize);
        left2 = prepImg("/Projectile/fireball_left_2", gp.tileSize, gp.tileSize);
        right1 = prepImg("/Projectile/fireball_right_1", gp.tileSize, gp.tileSize);
        right2 = prepImg("/Projectile/fireball_right_2", gp.tileSize, gp.tileSize);

    }
}
