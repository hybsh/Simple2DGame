package InteractiveTile;

import Entity.Entity;
import Main.gamePannel;

import java.awt.*;

public class IT_Dry_Tree extends Interactive_Tile{

    gamePannel gp;

    public IT_Dry_Tree(gamePannel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = prepImg("/InteractiveTiles/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;
    }

    public boolean useCorrectItem(Entity entity){
        boolean isCorrect = false;

        if(entity.currentWeapon.type == type_axe){
            isCorrect = true;
        }
        return isCorrect;
    }

    public void playSoundEffect(){
        gp.playSoundEffect(3);
    }
    public Interactive_Tile getDestroyedForm(){
        Interactive_Tile tile  = new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }
    public Color getParticleColor(){
        Color color = new Color(65,50,30);
        return color;
    }
    public int getParticleSize(){
        int size = 6;
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
