package InteractiveTile;

import Entity.Entity;
import Main.gamePannel;

public class Interactive_Tile extends Entity {
    gamePannel gp;
    public boolean destructible = false;
    public Interactive_Tile(gamePannel gp , int col, int row) {
        super(gp);
        this.gp = gp;
    }
    public void update(){

        if(invincible == true){
            invincibleCounter ++;
        }
        if(invincibleCounter > 20){
            invincible = false;
            invincibleCounter = 0;
        }
    }
    public boolean useCorrectItem(Entity entity){
        boolean isCorrect = false;
        return isCorrect;
    }

    public void playSoundEffect(){

    }
    public Interactive_Tile getDestroyedForm(){
        Interactive_Tile tile  = null;
        return tile;
    }
}
