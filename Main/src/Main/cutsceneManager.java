package Main;

import java.awt.*;

import Entity.playerDummy;
import Object.*;

public class cutsceneManager {

    gamePannel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;


    //Scene Number
    public final int NA= 0;
    public final int boss = 1;
    public final int end = 2;


    public cutsceneManager(gamePannel gp){
        this.gp = gp;
        endCredit = "Many thanks for playing our game!\n" + "The dev team:\n" + "Backend: Birla Alexandru, Lugojan Emanuel\n" +
                "Frontend/Art: Fofiu George, Porumb Remus\n" + "We hope you enjoyed it!\n";
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        switch (sceneNum){
            case boss: first(); break;
            case end: ending(); break;
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
    public void ending(){
            if(scenePhase == 0){
                gp.stopBgMusic();
                scenePhase ++;
            }
            if(scenePhase == 1){
                gp.UI.currentDialogue = "This looks shiny!";
                gp.UI.drawDialogueScreen();
                if(counterReached(60) == true) {
                    scenePhase++;
                }
            }
            if(scenePhase == 2){
                if(counterReached(30) == true){
                    scenePhase++;
                }
            }
            if(scenePhase == 3){
                System.out.println("reach phase 2");
                alpha += 0.005f;
                if(alpha > 1f){
                    alpha = 1f;
                }
                drawBlackBackground(alpha);

                if(alpha == 1f){
                    alpha = 0;
                    gp.playBgMusic(13);
                    scenePhase++;
                }
            }
            if(scenePhase == 4){
                drawBlackBackground(1f);
                alpha += 0.005f;
                if(alpha > 1f){
                    alpha = 1f;
                }

                String text = "After a long battle with Skeletor, \n" +
                        "our hero finally found the tresaure.\n" +
                        "Now he may leave the island in peace.";
                drawString(alpha,38f,200,text,70);
                if(counterReached(600) == true){
                    scenePhase ++;
                }

            }
            if(scenePhase == 5){
                drawBlackBackground(1f);
                drawString(1f,80f, gp.screenHeight/2,"Sigma Dungeons",40);
                if(counterReached(400) == true){
                    scenePhase ++;
                }
            }
            if(scenePhase == 6){
                drawBlackBackground(1f);

                y = gp.screenHeight/2;
                drawString(1f, 36f,y,endCredit,40);
                if(counterReached(480) == true){
                    scenePhase++;
                }
            }
            if (scenePhase == 7) {
                drawBlackBackground(1f);
                y--;
                drawString(1f,36f,y,endCredit,40);
            }
    }
    public boolean counterReached(int target){
        boolean counterReached = false;
        counter ++;
        if(counter > target){
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }
    public void drawBlackBackground(float alpha){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
        g2.setColor(Color.black);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for(String line : text.split("\n")){
            int x = gp.UI.getXforCenterText(line);
            g2.drawString(line,x,y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
