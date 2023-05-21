package Main;

import Entity.Entity;
import Entity.Player;

import java.awt.*;

public class EventHandler {
    gamePannel gp;
    EventRect eventRect[][][];
    int prevEventX,prevEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;
    public EventHandler(gamePannel gp){
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col=0,row=0;
        while(col < gp.maxWorldCol && row< gp.maxWorldRow && map < gp.maxMap){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width =2;
            eventRect[map][col][row].height =2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;

                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }


    }

    public void checkEvent(){

        int xDistance = Math.abs(gp.player.worldX - prevEventX);
        int yDistance = Math.abs(gp.player.worldY - prevEventY);
        int distance = Math.max(xDistance,yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }
        if(canTouchEvent == true) {
            if (hit(0,27, 16, "right") == true) {
                damagePit(27, 16, gp.dialogueState);
            }
            else if (hit(0,23, 12, "up") == true) {
                healingPool(23, 12, gp.dialogueState);
            }
            else if(hit(0,10,39,"any") == true){
                teleport(1,12,13,gp.indoor);
            }
            else if(hit(1,12,13,"any") == true){
                teleport(0,10,39,gp.outside);
            }
            else if(hit(1,12,9,"up") == true){
                speak(gp.npc[1][0]);
            }
            else if(hit(0,12,10,"any") == true){
                teleport(2,9,41,gp.dungeon);
            }
            else if(hit(2,9,41,"any") == true){
                teleport(0,12,10,gp.outside);
            }
        }
    }
    public boolean hit(int map,int col, int row, String reqDirection){
        boolean hit = false;

        if(map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row])
                    && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    prevEventX = gp.player.worldX;
                    prevEventY = gp.player.worldY;


                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;

        }
            return hit;
    }
    public void damagePit(int col,int row,int gameState){
        gp.gameState = gameState;
        gp.UI.currentDialogue = "OUCH!";
        gp.player.life -=1;
        //one time event
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }
    public void healingPool(int col, int row,int gameState){
        if(gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            gp.player.attacKCanceled = true;
            gp.playSoundEffect(7);
            gp.UI.currentDialogue = "You have been healed\n by the grace of the gods!";
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.eSetter.setMobz();
        }
    }
    public void teleport(int map, int col, int row, int area){

        gp.gameState = gp.transitionState;
        gp.nextArea = area;
        tempMap = map;
        tempRow = row;
        tempCol = col;
        canTouchEvent = false;
    }

    public void speak(Entity entity){
        if(gp.keyH.enterPressed == true){
            gp.gameState  = gp.dialogueState;
            gp.player.attacKCanceled = true;
            entity.speak();
        }
    }


}
