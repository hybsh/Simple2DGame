package Object;

import Entity.Entity;
import Interfaces.Useable;
import Main.gamePannel;

public class OBJ_Gold_Coin extends Entity implements Useable {
    gamePannel gp;
    public OBJ_Gold_Coin(gamePannel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        name = "Gold Coin";
        value = 1;
        down1 = prepImg("/Objects/gold_coin", gp.tileSize , gp.tileSize);
    }
    public void use(Entity entity){
        gp.playSoundEffect(2);
        gp.UI.addMessage("Picked up coin");
        gp.player.money += value;
    }
}
