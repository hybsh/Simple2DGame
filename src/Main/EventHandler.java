package Main;

import Entity.Player;

import java.awt.*;

public class EventHandler {
    gamePannel gp;
    EventRect eventRect[][];
    int prevEventX,prevEventY;
    boolean canTouchEvent = true;
    public EventHandler(gamePannel gp){
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col=0,row=0;
        while(col < gp.maxWorldCol && row< gp.maxWorldRow){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width =2;
            eventRect[col][row].height =2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
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
            if (hit(27, 16, "right") == true) {
                damagePit(27, 16, gp.dialogueState);
            }
            if (hit(23, 12, "up") == true) {
                healingPool(23, 12, gp.dialogueState);
            }
            if (hit(23, 40, "any") == true) {
                teleport(23, 40, gp.dialogueState);
            }
        }
    }
    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y= gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col *gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row])
                &&eventRect[col][row].eventDone == false){
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;

                prevEventX = gp.player.worldX;
                prevEventY = gp.player.worldY;


            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

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
            gp.eSetter.setMobz();
        }
    }
    public void teleport(int col, int row,int gameState){
        gp.gameState = gameState;
        gp.playSoundEffect(5);
        gp.UI.currentDialogue = "You have stepped foot \n where you were not supposed to";
        gp.player.worldX = gp.tileSize*37;
        gp.player.worldY = gp.tileSize*10;
        gp.player.life -= 1;
    }
}
