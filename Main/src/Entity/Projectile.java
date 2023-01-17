package Entity;

import Interfaces.Updateable;
import Main.gamePannel;

public class Projectile extends Entity implements Updateable{
    Entity user;

    public Projectile(gamePannel gp) {
        super(gp);

    }
    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }
    public void update(){

        if(user == gp.player){
            int mobIndex = gp.checker.checkEntity(this,gp.mob);
            if(mobIndex != 999){
                gp.player.damageMob(mobIndex, attack,knockbackPower);
                generateParticle(user.projectile, gp.mob[gp.currentMap][mobIndex]);
                alive = false;
            }
        }
        if(user != gp.player){
            boolean hitPlayer = gp.checker.checkPlayer(this);
            if(gp.player.invincible == false && hitPlayer == true){
                damagePlayer(attack);
                generateParticle(user.projectile, gp.player);
                alive = false;
            }
        }

        switch (direction){
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }

        life--;
        if(life <= 0){
            alive = false;
        }

        spriteCounter++;
        if(spriteCounter >12){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2)
                spriteNum = 1;
        }
        spriteCounter = 0;
    }
    public boolean hasMana(Entity user){
        boolean hasMana = false;
        return hasMana;
    }
    public void spendMana(Entity user){}
}
