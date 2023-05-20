package Env;
import Main.*;

import java.awt.*;

public class EnvManager {
    gamePannel gp;
    Lighting lighting;

    public EnvManager(gamePannel gp){
        this.gp = gp;
    }
    public void setup(){
        lighting = new Lighting(gp, 350);
    }
    public void draw(Graphics2D g2){
        lighting.draw(g2);
    }
}
