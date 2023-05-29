package Main;

import java.awt.*;

import Entity.playerDummy;
import Object.*;

public class cutsceneManager {

    gamePannel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;

    //Scene Number
    public final int NA= 0;
    public final int boss = 1;


    public cutsceneManager(gamePannel gp){
        this.gp = gp;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        switch (sceneNum){
            case boss: first(); break;
        }
    }

    public void first(){

        if(scenePhase == 0){
            gp.bossBattleOn = true;

            for(int i = 0; i<gp.obj[1].length; i++){
                if(gp.obj[gp.currentMap][i] == null){
                    gp.obj[gp.currentMap][i] = new OBJ_Door(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize * 25;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize * 28;
                    gp.obj[gp.currentMap][i].temp = true;
                    break;
                }
            }
            for(int i = 0; i<gp.npc[1].length;i++){
                if(gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new playerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }

            gp.player.drawing = false;

            scenePhase++;

        }
        if(scenePhase == 1){
            gp.player.worldY -=2;

            if(gp.player.worldY < gp.tileSize * 16){
                scenePhase++;
            }
        }
        if(scenePhase == 2){
            for(int i = 0; i<gp.mob[1].length;i++){
                if(gp.mob[gp.currentMap][i] != null &&
                        gp.mob[gp.currentMap][i].name.equals("Skeletor")){
                    gp.mob[gp.currentMap][i].sleep = false;
                    scenePhase++;
                    break;
                }
            }
        }
        if(scenePhase == 3){
            for(int i = 0; i < gp.npc[1].length; i++){
                if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name == playerDummy.npcName){
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }
            gp.player.drawing = true;
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;
        }

    }
}
