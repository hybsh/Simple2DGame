package Main;

import Entity.NPC_Merchant;
import Entity.NPC_Priest;
import InteractiveTile.IT_Dry_Tree;
import MOBZ.Boss;
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

        gp.obj[mapNum][5] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][5].worldX = gp.tileSize *22;
        gp.obj[mapNum][5].worldY = gp.tileSize *20;

        gp.obj[mapNum][6] = new OBJ_Heart(gp);
        gp.obj[mapNum][6].worldX = gp.tileSize *23;
        gp.obj[mapNum][6].worldY = gp.tileSize *20;

        gp.obj[mapNum][7] = new OBJ_Mana(gp);
        gp.obj[mapNum][7].worldX = gp.tileSize *24;
        gp.obj[mapNum][7].worldY = gp.tileSize *20;

        if(gp.level.equals("Easy") || gp.level.equals("Medium")){
            gp.obj[mapNum][3] = new OBJ_Axe(gp);
            gp.obj[mapNum][3].worldX = gp.tileSize *33;
            gp.obj[mapNum][3].worldY = gp.tileSize *21;

            gp.obj[mapNum][4] = new OBJ_Shield_Blue(gp);
            gp.obj[mapNum][4].worldX = gp.tileSize *35;
            gp.obj[mapNum][4].worldY = gp.tileSize *21;
        }
        gp.obj[mapNum][8] = new OBJ_Door(gp);
        gp.obj[mapNum][8].worldX = gp.tileSize *14;
        gp.obj[mapNum][8].worldY = gp.tileSize *28;

        gp.obj[mapNum][9] = new OBJ_Door(gp);
        gp.obj[mapNum][9].worldX = gp.tileSize *12;
        gp.obj[mapNum][9].worldY = gp.tileSize *12;

        gp.obj[mapNum][10] = new OBJ_Chest(gp, new OBJ_Key(gp));
        gp.obj[mapNum][10].worldX = gp.tileSize *12;
        gp.obj[mapNum][10].worldY = gp.tileSize * 8;

        gp.obj[mapNum][11] = new OBJ_Key(gp);
        gp.obj[mapNum][11].worldX = gp.tileSize *12;
        gp.obj[mapNum][11].worldY = gp.tileSize *15;

        gp.obj[mapNum][12] = new OBJ_Lantern(gp);
        gp.obj[mapNum][12].worldX = gp.tileSize *18;
        gp.obj[mapNum][12].worldY = gp.tileSize *20;

        gp.obj[mapNum][13] = new OBJ_Tent(gp);
        gp.obj[mapNum][13].worldX = gp.tileSize *18;
        gp.obj[mapNum][13].worldY = gp.tileSize *21;

        mapNum = 3;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 25;
        gp.obj[mapNum][i].worldY = gp.tileSize * 15;
        i++;

    }
    public void setNPC(){
        int mapNum = 0;
        gp.npc[mapNum][0] = new NPC_Priest(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize * 21;
        gp.npc[mapNum][0].worldY = gp.tileSize * 21;


        mapNum = 1;
        gp.npc[mapNum][0] = new NPC_Merchant(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize * 12;
        gp.npc[mapNum][0].worldY = gp.tileSize * 7;
    }

    public void setMobz(){
        if(gp.level.equals("Easy")) {
            int mapNum = 0;
            gp.mob[mapNum][0] = new Slime(gp);
            gp.mob[mapNum][0].worldX = gp.tileSize * 23;
            gp.mob[mapNum][0].worldY = gp.tileSize * 36;

            gp.mob[mapNum][1] = new Slime(gp);
            gp.mob[mapNum][1].worldX = gp.tileSize * 23;
            gp.mob[mapNum][1].worldY = gp.tileSize * 37;

            gp.mob[mapNum][2] = new Slime(gp);
            gp.mob[mapNum][2].worldX = gp.tileSize * 22;
            gp.mob[mapNum][2].worldY = gp.tileSize * 38;

            gp.mob[mapNum][3] = new Slime(gp);
            gp.mob[mapNum][3].worldX = gp.tileSize * 11;
            gp.mob[mapNum][3].worldY = gp.tileSize * 10;

            gp.mob[mapNum][4] = new Slime(gp);
            gp.mob[mapNum][4].worldX = gp.tileSize * 13;
            gp.mob[mapNum][4].worldY = gp.tileSize * 10;

            gp.mob[3][5] = new Boss(gp);
            gp.mob[3][5].worldX = gp.tileSize * 24;
            gp.mob[3][5].worldY = gp.tileSize * 17;
        }
        else if(gp.level.equals("Medium")){
            int mapNum = 0;
            gp.mob[mapNum][0] = new Slime(gp);
            gp.mob[mapNum][0].worldX = gp.tileSize * 23;
            gp.mob[mapNum][0].worldY = gp.tileSize * 36;

            gp.mob[mapNum][1] = new Slime(gp);
            gp.mob[mapNum][1].worldX = gp.tileSize * 23;
            gp.mob[mapNum][1].worldY = gp.tileSize * 37;

            gp.mob[mapNum][2] = new Slime(gp);
            gp.mob[mapNum][2].worldX = gp.tileSize * 22;
            gp.mob[mapNum][2].worldY = gp.tileSize * 38;

            gp.mob[mapNum][3] = new Slime(gp);
            gp.mob[mapNum][3].worldX = gp.tileSize * 11;
            gp.mob[mapNum][3].worldY = gp.tileSize * 10;

            gp.mob[mapNum][4] = new Slime(gp);
            gp.mob[mapNum][4].worldX = gp.tileSize * 13;
            gp.mob[mapNum][4].worldY = gp.tileSize * 10;

            gp.mob[mapNum][5] = new Slime(gp);
            gp.mob[mapNum][5].worldX = gp.tileSize * 39;
            gp.mob[mapNum][5].worldY = gp.tileSize * 10;

            gp.mob[mapNum][6] = new Slime(gp);
            gp.mob[mapNum][6].worldX = gp.tileSize * 39;
            gp.mob[mapNum][6].worldY = gp.tileSize * 14;

            gp.mob[mapNum][7] = new Slime(gp);
            gp.mob[mapNum][7].worldX = gp.tileSize * 34;
            gp.mob[mapNum][7].worldY = gp.tileSize * 12;
        }
        else if(gp.level.equals("Hard")){
            int mapNum = 0;
            gp.mob[mapNum][0] = new Slime(gp);
            gp.mob[mapNum][0].worldX = gp.tileSize * 23;
            gp.mob[mapNum][0].worldY = gp.tileSize * 36;

            gp.mob[mapNum][1] = new Slime(gp);
            gp.mob[mapNum][1].worldX = gp.tileSize * 23;
            gp.mob[mapNum][1].worldY = gp.tileSize * 37;

            gp.mob[mapNum][2] = new Slime(gp);
            gp.mob[mapNum][2].worldX = gp.tileSize * 22;
            gp.mob[mapNum][2].worldY = gp.tileSize * 38;

            gp.mob[mapNum][3] = new Slime(gp);
            gp.mob[mapNum][3].worldX = gp.tileSize * 11;
            gp.mob[mapNum][3].worldY = gp.tileSize * 10;

            gp.mob[mapNum][4] = new Slime(gp);
            gp.mob[mapNum][4].worldX = gp.tileSize * 13;
            gp.mob[mapNum][4].worldY = gp.tileSize * 10;

            gp.mob[mapNum][5] = new Slime(gp);
            gp.mob[mapNum][5].worldX = gp.tileSize * 39;
            gp.mob[mapNum][5].worldY = gp.tileSize * 10;

            gp.mob[mapNum][6] = new Slime(gp);
            gp.mob[mapNum][6].worldX = gp.tileSize * 39;
            gp.mob[mapNum][6].worldY = gp.tileSize * 14;

            gp.mob[mapNum][7] = new Slime(gp);
            gp.mob[mapNum][7].worldX = gp.tileSize * 34;
            gp.mob[mapNum][7].worldY = gp.tileSize * 12;

            gp.mob[mapNum][8] = new Slime(gp);
            gp.mob[mapNum][8].worldX = gp.tileSize * 10;
            gp.mob[mapNum][8].worldY = gp.tileSize * 24;

            gp.mob[mapNum][9] = new Slime(gp);
            gp.mob[mapNum][9].worldX = gp.tileSize * 12;
            gp.mob[mapNum][9].worldY = gp.tileSize * 28;

            gp.mob[mapNum][10] = new Slime(gp);
            gp.mob[mapNum][10].worldX = gp.tileSize * 14;
            gp.mob[mapNum][10].worldY = gp.tileSize * 25;

            gp.mob[mapNum][11] = new Slime(gp);
            gp.mob[mapNum][11].worldX = gp.tileSize * 25;
            gp.mob[mapNum][11].worldY = gp.tileSize * 40;

        }

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
