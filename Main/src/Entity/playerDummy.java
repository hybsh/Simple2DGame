package Entity;
import Main.*;

public class playerDummy extends Entity{

    public static final String npcName = "Dummy";

    public playerDummy(gamePannel gp){
        super(gp);

        name = npcName;
        getPlayerImage();
    }

    public void getPlayerImage(){

        up1 = prepImg("/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = prepImg("/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = prepImg("/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = prepImg("/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = prepImg("/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = prepImg("/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = prepImg("/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = prepImg("/player/boy_right_2", gp.tileSize, gp.tileSize);

    }
}
