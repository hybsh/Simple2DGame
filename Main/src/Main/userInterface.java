package Main;

import Entity.Entity;
import Interfaces.Drawable;
import Object.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class userInterface implements Drawable {
    gamePannel gp;
    Graphics2D g2;
    Font purisaB;
    BufferedImage heart_full,heart_half,heart_empty,crystal_full,crystal_blank,coin;
    public boolean messageOn = false;
    public boolean finished = false;
    public ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public String currentDialogue = "";
    public int commandNum = 0;
    public int playerSlotCol=0, playerSlotRow=0;
    public int npcSlotCol = 0, npcSlotRow = 0;
    int subState = 0;
    int counter = 0;
    public Entity merchant;


    public userInterface(gamePannel gp){
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
        }catch (FontFormatException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;
        Entity crystal = new OBJ_Mana(gp);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;
        Entity goldCoin = new OBJ_Gold_Coin(gp);
        coin = goldCoin.down1;
    }
    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;



        if(gp.gameState == gp.startState){
            drawTitleScreen();
        }

        if(gp.gameState == gp.playState){
            drawPlayerLife();
            drawMessage();
        }
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();


        }
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
        if(gp.gameState == gp.statusState){
            drawPlayerLife();
            drawCharacterScreen();
            drawInventory(gp.player,true);
        }
        if(gp.gameState == gp.optionsState){
            drawOptionsScreen();
        }

        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }

        if(gp.gameState == gp.transitionState){
            drawTransition();
        }

        if(gp.gameState == gp.tradeState){
            drawTradeScreen();
        }


    }
    private void drawOptionsScreen() {

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(24F));

        int frameX = gp.tileSize*6,frameY = gp.tileSize,frameWidth = gp.tileSize *8,frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        switch(subState){
            case 0: options_top(frameX,frameY);break;
            case 1: break;
            case 2: options_controls(frameX,frameY);break;
            case 3: options_MainMenuConfirm(frameX,frameY); break;
        }
        gp.keyH.enterPressed = false;
    }
    public void options_top(int frameX, int frameY){
        int textX,textY;

        String text = "Options";
        textX = getXforCenterText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text,textX,textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;

        g2.drawString("Music",textX,textY);
        if(commandNum == 0){
            g2.drawString(">",textX - 25,textY);
        }

        textY += gp.tileSize;
        g2.drawString("SFX",textX,textY);
        if(commandNum == 1){
            g2.drawString(">",textX - 25,textY);
        }

        textY += gp.tileSize;
        g2.drawString("Controls",textX,textY);
        if(commandNum == 2){
            g2.drawString(">",textX - 25,textY);
            if(gp.keyH.enterPressed == true) {
                subState = 2;
                commandNum = 0;
            }
        }

        textY += gp.tileSize;
        g2.drawString("Main menu",textX,textY);
        if(commandNum == 3){
            g2.drawString(">",textX - 25,textY);
            if(gp.keyH.enterPressed == true){
                subState = 3;
                commandNum = 0;
            }
        }

        textY += gp.tileSize*2;
        g2.drawString("Resume",textX,textY);
        if(commandNum == 4){
            g2.drawString(">",textX - 25,textY);
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        textX = frameX + (int)(gp.tileSize * 4.5);
        textY = frameY + gp.tileSize * 2 + 24;
        g2.drawRect(textX,textY,120,24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX,textY,volumeWidth,24);

        textY += gp.tileSize;
        g2.drawRect(textX,textY,120,24);
        int soundWidth = 24 * gp.sound.volumeScale;
        g2.fillRect(textX,textY,soundWidth,24);


        gp.config.saveConfig();
    }
    public void options_controls(int frameX, int frameY){
        int textX,textY;

        String text = "Controls";
        textX = getXforCenterText(text);
        textY = frameY + gp.tileSize;

        g2.drawString(text,textX,textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;

        g2.drawString("Move" , textX,textY); textY += gp.tileSize;
        g2.drawString("Interact/Attack" , textX,textY); textY += gp.tileSize;
        g2.drawString("Spell" , textX,textY); textY += gp.tileSize;
        g2.drawString("Inventory" , textX,textY); textY += gp.tileSize;
        g2.drawString("Pause" , textX,textY); textY += gp.tileSize;
        g2.drawString("Options" , textX,textY); textY += gp.tileSize;


        textX = frameX + gp.tileSize *6 - 24;
        textY = frameY + gp.tileSize *2;

        g2.drawString("WASD",textX,textY); textY += gp.tileSize;
        g2.drawString("ENTER",textX,textY); textY += gp.tileSize;
        g2.drawString("F",textX,textY); textY += gp.tileSize;
        g2.drawString("C/I",textX,textY); textY += gp.tileSize;
        g2.drawString("P",textX,textY); textY += gp.tileSize;
        g2.drawString("ESCAPE",textX,textY); textY += gp.tileSize;
    }
    public void options_MainMenuConfirm(int frameX, int frameY){

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialogue = "You sure you want to go back \nto the main menu?";

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,textX,textY);
            textY += 40;
        }
        //YES
        String text = "YES!";
        textX = getXforCenterText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text,textX,textY);
        if(commandNum == 0){
            g2.drawString(">",textX - 25,textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                gp.gameState = gp.startState;
                gp.stopBgMusic();
            }
        }


        //NO
        text = "NO!";
        textX = getXforCenterText(text);
        textY += gp.tileSize;
        g2.drawString(text,textX,textY);
        if(commandNum == 1){
            g2.drawString(">",textX - 25,textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNum = 3;
            }
        }

    }

    public void drawPlayerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        //BLANK HEARTS
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += gp.tileSize;
        }

        x= gp.tileSize/2;
        y= gp.tileSize/2;
        i =0;

        while(i < gp.player.life){
            g2.drawImage(heart_half,x,y,null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full,x,y,null);
            }
            i++;
            x+=gp.tileSize;
        }

        x = (gp.tileSize/2) - 5;
        y = (int) (gp.tileSize*1.5);
        i = 0;

        while(i < gp.player.maxMana){
            g2.drawImage(crystal_blank,x,y,null);
            i++;
            x += 35;
        }

        x = (gp.tileSize/2) - 5;
        y = (int) (gp.tileSize*1.5);
        i = 0;

        while (i < gp.player.mana){
            g2.drawImage(crystal_full,x,y,null);
            i++;
            x+= 35;
        }


    }
    public void drawPauseScreen(){


        String text = "PAUSED";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,76F));
        Color c= new Color(255,255,255);
        g2.setColor(c);
        int x =(gp.screenWidth/2)- ((int) g2.getFontMetrics().getStringBounds(text,g2).getWidth()/2);

        int y = gp.screenHeight/2;

        g2.drawString(text,x,y);
    }
    public void drawDialogueScreen(){
        int x = gp.tileSize * 3;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 4;

        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,18F));
        x+= gp.tileSize;
        y+= gp.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y+=40;
        }
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c= new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);

    }

    public int getXforCenterText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public void drawTitleScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,64F));
        String text = "Project Let's goo";
        int x = getXforCenterText(text);
        int y = gp.tileSize * 3;

        g2.setColor(Color.black);
        g2.fillRect(0,0,gp.screenWidth2,gp.screenHeight2);

        g2.setColor(Color.GRAY);
        g2.drawString(text,x+5,y+5);

        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);

        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1,x,y,gp.tileSize*2,gp.tileSize*2,null);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 38F));
        text = "NEW GAME";
        x = getXforCenterText(text);
        y += gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.tileSize,y);
        }

        text = "LOAD GAME";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">", x-gp.tileSize,y);
        }

        text = "QUIT";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 2){
            g2.drawString(">", x-gp.tileSize,y);
        }
    }
    public void drawCharacterScreen(){
        final int frameX = gp.tileSize * 2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(24F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        g2.drawString("Level",textX,textY);
        textY += lineHeight;
        g2.drawString("HP",textX,textY);
        textY +=lineHeight;
        g2.drawString("Mana",textX,textY);
        textY +=lineHeight;
        g2.drawString("Strength",textX,textY);
        textY +=lineHeight;
        g2.drawString("Dexterity",textX,textY);
        textY +=lineHeight;
        g2.drawString("Attack",textX,textY);
        textY +=lineHeight;
        g2.drawString("Defense",textX,textY);
        textY +=lineHeight;
        g2.drawString("XP",textX,textY);
        textY +=lineHeight;
        g2.drawString("Next Level",textX,textY);
        textY +=lineHeight;
        g2.drawString("Money",textX,textY);
        textY +=lineHeight + 10;
        g2.drawString("Weapon",textX,textY);
        textY +=lineHeight + 15;
        g2.drawString("Shield",textX,textY);
        textY +=lineHeight;


        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAllignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAllignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXforAllignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.strength);
        textX = getXforAllignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.dexterity);
        textX = getXforAllignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.attack);
        textX = getXforAllignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.defense);
        textX = getXforAllignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.XP);
        textX = getXforAllignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.nextLvlXP);
        textX = getXforAllignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.money);
        textX = getXforAllignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize,textY - 25,null);
        textY += lineHeight;
        g2.drawImage(gp.player.currentShield.down1,tailX - gp.tileSize,textY - 10,null);

    }

    public int getXforAllignRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = tailX - length;
        return x;
    }

    public void drawMessage(){
        int messageX = gp.tileSize, messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));

        for(int i=0; i<message.size();i++){
            if(message.get(i) != null){
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX +2 , messageY +2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX,messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;
                if(messageCounter.get(i) > 120){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }

    }
    public void drawInventory(Entity entity, boolean cursor){

        int frameX=0, frameY=0, frameWidth=0, frameHeight=0;
        int slotCol=0, slotRow=0;

        if(entity == gp.player){
            frameX = gp.tileSize * 12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        }
        else{
            frameX = gp.tileSize * 2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        final int slotXstart = frameX + 20;
        final int slotYstart = frameY +20;
        int slotX = slotXstart,slotY = slotYstart;

        //ITEMS
        for(int i =0 ; i<entity.inventory.size();i++){


            if(entity.inventory.get(i) == entity.currentWeapon ||
                    entity.inventory.get(i) == entity.currentShield){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX,slotY,gp.tileSize-2,gp.tileSize-2,10,10);
            }

                g2.drawImage(entity.inventory.get(i).down1, slotX,slotY,null);
                if(entity == gp.player && entity.inventory.get(i).amount > 1){
                    g2.setFont(g2.getFont().deriveFont(32f));
                    int amountX,amountY;
                    String s = "" + entity.inventory.get(i).amount;
                    amountX = getXforAllignRightText(s, slotX + 44);
                    amountY = slotY + gp.tileSize;

                    g2.setColor(new Color(60,60,60));
                    g2.drawString(s,amountX,amountY);

                    g2.setColor(Color.white);
                    g2.drawString(s,amountX-3,amountY-3);

                }

                slotX += gp.tileSize;
                if(i == 4 || i==9 || i == 14){
                    slotX = slotXstart;
                    slotY += gp.tileSize;
                }
        }


        //ACTIVE SLOT
        if(cursor == true) {
            int cursorX = slotXstart + (gp.tileSize * slotCol),
                    cursorY = slotYstart + (gp.tileSize * slotRow),
                    cursorWidth = gp.tileSize,
                    cursorHeight = gp.tileSize;

            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);


            //DESCRIPTION WINDOW
            int dFrameX = frameX, dFrameY = frameY + frameHeight, dFrameWidth = frameWidth, dFrameHeight = gp.tileSize * 3;

            //DESCRIPTION TEXT
            int textX = dFrameX + 20, textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(20F));

            int itemIndex = getItemIndexFromInv(slotCol,slotRow);

            if (itemIndex < entity.inventory.size()) {
                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
                for (String line : entity.inventory.get(itemIndex).description.split("\n")) {
                    g2.drawString(line, textX, textY);
                    textY += 24;
                }
            }
        }
    }
    public int getItemIndexFromInv(int slotCol, int slotRow){
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }

    private void drawGameOverScreen() {
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        int x,y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100f));

        text = "Game OVER!";
        g2.setColor(Color.black);
        x = getXforCenterText(text);
        y = gp.tileSize * 4;
        g2.drawString(text,x,y);

        g2.setColor(Color.white);
        g2.drawString(text,x - 4,y - 4);


        g2.setFont(g2.getFont().deriveFont(Font.PLAIN , 50f));
        text = "Retry!";
        x = getXforCenterText(text);
        y += gp.tileSize * 4;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">", x-40,y);
        }

        text = "Restart!";
        x = getXforCenterText(text);
        y += 55;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">", x-40,y);
        }
        text = "Quit";
        x = getXforCenterText(text);
        y += 55;
        g2.drawString(text,x,y);
        if(commandNum == 2){
            g2.drawString(">", x-40,y);
        }


    }

    public void drawTransition(){
        counter++;
        g2.setColor(new Color(0,0,0, counter*5));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        if(counter == 50){
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.eHandler.tempCol * gp.tileSize;
            gp.player.worldY = gp.eHandler.tempRow * gp.tileSize;
            gp.eHandler.prevEventX = gp.player.worldX;
            gp.eHandler.prevEventY = gp.player.worldY;
        }
    }

    public void drawTradeScreen(){
        switch (subState){
            case 0 : tradeSelect(); break;
            case 1 : tradeBuy(); break;
            case 2 : tradeSell(); break;
        }
        gp.keyH.enterPressed = false;
    }
    public void tradeSelect(){

        drawDialogueScreen();
        int x = gp.tileSize * 15;
        int y = gp.tileSize * 4;
        int width = gp.tileSize *3;
        int height = (int)(gp.tileSize * 3.5);
        drawSubWindow(x,y,width,height);

        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("BUY",x,y);
        if(commandNum == 0){
            g2.drawString(">", x-24,y);
            if(gp.keyH.enterPressed == true){
                subState = 1;
            }
        }

        y+= gp.tileSize;
        g2.drawString("SELL",x,y);
        if(commandNum == 1){
            g2.drawString(">", x-24,y);
            if(gp.keyH.enterPressed == true){
                subState = 2;
            }
        }

        y+= gp.tileSize;
        g2.drawString("Leave",x,y);
        if(commandNum == 2){
            g2.drawString(">", x-24,y);
            if(gp.keyH.enterPressed == true){
                commandNum = 0;
                gp.gameState = gp.dialogueState;
                currentDialogue = "Mess with again fam?";
            }
        }

    }
    public void tradeBuy(){

        drawInventory(gp.player, false);
        drawInventory(gp.npc[1][0], true);

        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 2;
        drawSubWindow(x,y,width,height);
        g2.drawString("[ESC] Back", x+24, y+60);


        x = gp.tileSize*12;
        y = gp.tileSize*9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Your balance : " + gp.player.money, x+24, y+60);

        int itemIndex = getItemIndexFromInv(npcSlotCol,npcSlotRow);
        if(itemIndex < merchant.inventory.size()){
            x = (int) (gp.tileSize * 5.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10 , y+8,32,32,null );

            int price = merchant.inventory.get(itemIndex).price;
            String text = ""+price;
            x = getXforAllignRightText(text, gp.tileSize*8 - 20);
            g2.drawString(text,x,y + 32);

            if(gp.keyH.enterPressed == true){
                if(price > gp.player.money){
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You ain't scammin me!";
                    drawDialogueScreen();
                }
                else {
                    if(gp.player.canObtainItem(merchant.inventory.get(itemIndex)) == true){
                        gp.player.money -=price;
                    }
                    else {
                        subState = 0;
                        gp.gameState = gp.dialogueState;
                        currentDialogue = "You cannot carry any more items";
                    }
                }
            }
        }

    }
    public void tradeSell(){
        drawInventory(gp.player, true);
        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 2;
        drawSubWindow(x,y,width,height);
        g2.drawString("[ESC] Back", x+24, y+60);


        x = gp.tileSize*12;
        y = gp.tileSize*9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Your balance : " + gp.player.money, x+24, y+60);

        int itemIndex = getItemIndexFromInv(playerSlotCol,playerSlotRow);
        if(itemIndex < gp.player.inventory.size()){
            x = (int) (gp.tileSize * 15.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10 , y+8,32,32,null );

            int price = gp.player.inventory.get(itemIndex).price/2;
            String text = ""+price;
            x = getXforAllignRightText(text, gp.tileSize*18 - 20);
            g2.drawString(text,x,y + 32);

            if(gp.keyH.enterPressed == true) {
                if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
                        gp.player.inventory.get(itemIndex) == gp.player.currentShield){
                    commandNum = 0;
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "I don't buy used items fam";
                }
                else{
                    if(gp.player.inventory.get(itemIndex).amount > 1){
                        gp.player.inventory.get(itemIndex).amount --;
                    }
                    else {
                        gp.player.inventory.remove(itemIndex);
                    }
                    gp.player.money += price;
                }
            }
        }
    }
}
