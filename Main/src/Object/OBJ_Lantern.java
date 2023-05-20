package Object;
import Entity.Entity;
import Main.*;

public class OBJ_Lantern extends Entity {
    public OBJ_Lantern(gamePannel gp){
        super(gp);

        type = type_light;
        name = "Lantern";
        down1 = prepImg("/objects/lantern",gp.tileSize,gp.tileSize);
        description = "Illuminates your \nsurroundings";
        price = 200;
        lightRadius = 250;
    }
}
