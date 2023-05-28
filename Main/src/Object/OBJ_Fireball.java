package Object;

import Entity.Entity;
import Entity.Projectile;
import Main.gamePannel;

import java.awt.*;

public class OBJ_Fireball extends Projectile {
    gamePannel gp;
    public OBJ_Fireball(gamePannel gp) {
        super(gp);
        this.gp =gp;

        name = "Fireball";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 1;
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

    public boolean hasMana(Entity user){
        boolean hasMana = false;
        if(user.mana >= useCost){
            hasMana = true;
        }
        return hasMana;
    }

    public void spendMana(Entity user){
        user.mana -= useCost;
    }

    public Color getParticleColor(){
        Color color = new Color(240,50,0);
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
