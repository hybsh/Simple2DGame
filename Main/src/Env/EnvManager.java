package Env;
import Main.*;

import java.awt.*;

public class EnvManager {
    gamePannel gp;
    public Lighting lighting;

    public EnvManager(gamePannel gp){
        this.gp = gp;
    }
    public void update(){
        lighting.update();
    }
    public void setup(){
        lighting = new Lighting(gp);
    }
    public void draw(Graphics2D g2){
        lighting.draw(g2);
    }
}
