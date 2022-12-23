package Main;

import Entity.NPC_Priest;
import InteractiveTile.IT_Dry_Tree;
import MOBZ.Slime;
import Object.*;


public class entitySetter {
    gamePannel gp;

    public entitySetter(gamePannel gp){
        this.gp = gp;
    }
    public void setObject(){
        int mapNum = 0;

        gp.obj[mapNum][0] = new OBJ_Key(gp);
        gp.obj[mapNum][0].worldX = gp.tileSize *25;
        gp.obj[mapNum][0].worldY = gp.tileSize *23;

        gp.obj[mapNum][1] = new OBJ_Gold_Coin(gp);
        gp.obj[mapNum][1].worldX = gp.tileSize *21;
        gp.obj[mapNum][1].worldY = gp.tileSize *19;

        gp.obj[mapNum][2] = new OBJ_Gold_Coin(gp);
        gp.obj[mapNum][2].worldX = gp.tileSize *25;
        gp.obj[mapNum][2].worldY = gp.tileSize *21;

        gp.obj[mapNum][3] = new OBJ_Axe(gp);
        gp.obj[mapNum][3].worldX = gp.tileSize *33;
        gp.obj[mapNum][3].worldY = gp.tileSize *21;

        gp.obj[mapNum][4] = new OBJ_Shield_Blue(gp);
        gp.obj[mapNum][4].worldX = gp.tileSize *35;
        gp.obj[mapNum][4].worldY = gp.tileSize *21;

        gp.obj[mapNum][5] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][5].worldX = gp.tileSize *22;
        gp.obj[mapNum][5].worldY = gp.tileSize *20;

        gp.obj[mapNum][6] = new OBJ_Heart(gp);
        gp.obj[mapNum][6].worldX = gp.tileSize *23;
        gp.obj[mapNum][6].worldY = gp.tileSize *20;

        gp.obj[mapNum][7] = new OBJ_Mana(gp);
        gp.obj[mapNum][7].worldX = gp.tileSize *24;
        gp.obj[mapNum][7].worldY = gp.tileSize *20;

    }
    public void setNPC(){
        int mapNum = 0;
        gp.npc[mapNum][0] = new NPC_Priest(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize * 21;
        gp.npc[mapNum][0].worldY = gp.tileSize * 21;


        gp.npc[mapNum][1] = new NPC_Priest(gp);
        gp.npc[mapNum][1].worldX = gp.tileSize * 9;
        gp.npc[mapNum][1].worldY = gp.tileSize * 10;
    }
    public void setMobz(){
        int mapNum = 0;
        gp.mob[mapNum][0] = new Slime(gp);
        gp.mob[mapNum][0].worldX = gp.tileSize * 23;
        gp.mob[mapNum][0].worldY = gp.tileSize *36;

        gp.mob[mapNum][1] = new Slime(gp);
        gp.mob[mapNum][1].worldX = gp.tileSize * 23;
        gp.mob[mapNum][1].worldY = gp.tileSize *37;

        gp.mob[mapNum][2] = new Slime(gp);
        gp.mob[mapNum][2].worldX = gp.tileSize * 22;
        gp.mob[mapNum][2].worldY = gp.tileSize * 38;

        gp.mob[mapNum][3] = new Slime(gp);
        gp.mob[mapNum][3].worldX = gp.tileSize * 11;
        gp.mob[mapNum][3].worldY = gp.tileSize * 10;

        gp.mob[mapNum][4] = new Slime(gp);
        gp.mob[mapNum][4].worldX = gp.tileSize * 10;
        gp.mob[mapNum][4].worldY = gp.tileSize * 10;

    }
    public void setInteractiveTiles(){
        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new IT_Dry_Tree(gp,27,12);
        i++;
        gp.iTile[mapNum][i] = new IT_Dry_Tree(gp,28,12);
        i++;
        gp.iTile[mapNum][i] = new IT_Dry_Tree(gp,29,12);
        i++;
        gp.iTile[mapNum][i] = new IT_Dry_Tree(gp,30,12);
        i++;
        gp.iTile[mapNum][i] = new IT_Dry_Tree(gp,31,12);
        i++;
        gp.iTile[mapNum][i] = new IT_Dry_Tree(gp,32,12);
        i++;
        gp.iTile[mapNum][i] = new IT_Dry_Tree(gp,33,12);
        i++;
    }
}
