package Main;

import Entity.NPC_Priest;
import MOBZ.Slime;
import Object.*;


public class entitySetter {
    gamePannel gp;

    public entitySetter(gamePannel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = gp.tileSize *25;
        gp.obj[0].worldY = gp.tileSize *23;

        gp.obj[1] = new OBJ_Boots(gp);
        gp.obj[1].worldX = gp.tileSize *21;
        gp.obj[1].worldY = gp.tileSize *19;

        gp.obj[2] = new OBJ_Key(gp);
        gp.obj[2].worldX = gp.tileSize *25;
        gp.obj[2].worldY = gp.tileSize *21;

        gp.obj[3] = new OBJ_Axe(gp);
        gp.obj[3].worldX = gp.tileSize *33;
        gp.obj[3].worldY = gp.tileSize *21;

        gp.obj[4] = new OBJ_Shield_Blue(gp);
        gp.obj[4].worldX = gp.tileSize *35;
        gp.obj[4].worldY = gp.tileSize *21;

        gp.obj[5] = new OBJ_Potion_Red(gp);
        gp.obj[5].worldX = gp.tileSize *22;
        gp.obj[5].worldY = gp.tileSize *20;

    }
    public void setNPC(){
        gp.npc[0] = new NPC_Priest(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;


        gp.npc[1] = new NPC_Priest(gp);
        gp.npc[1].worldX = gp.tileSize * 9;
        gp.npc[1].worldY = gp.tileSize * 10;
    }
    public void setMobz(){
        gp.mob[0] = new Slime(gp);
        gp.mob[0].worldX = gp.tileSize * 23;
        gp.mob[0].worldY = gp.tileSize *36;

        gp.mob[1] = new Slime(gp);
        gp.mob[1].worldX = gp.tileSize * 23;
        gp.mob[1].worldY = gp.tileSize *37;

        gp.mob[2] = new Slime(gp);
        gp.mob[2].worldX = gp.tileSize * 22;
        gp.mob[2].worldY = gp.tileSize * 38;

        gp.mob[3] = new Slime(gp);
        gp.mob[3].worldX = gp.tileSize * 11;
        gp.mob[3].worldY = gp.tileSize * 10;

        gp.mob[4] = new Slime(gp);
        gp.mob[4].worldX = gp.tileSize * 10;
        gp.mob[4].worldY = gp.tileSize * 10;

    }
}
