package Main;

import Entity.Entity;
import Object.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class userInterface {
    gamePannel gp;
    Graphics2D g2;
    Font purisaB;
    BufferedImage heart_full,heart_half,heart_empty;
    public boolean messageOn = false;
    public boolean finished = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public String currentDialogue = "";
    public int commandNum = 0;
    public int slotCol=0, slotRow=0;


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
            drawInventory();
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
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
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
        final int frameX = gp.tileSize;
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
        textY +=lineHeight +20;
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

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize,textY - 15,null);
        textY += lineHeight;
        g2.drawImage(gp.player.currentShield.down1,tailX - gp.tileSize,textY,null);

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
    public void drawInventory(){

        int frameX = gp.tileSize * 9,frameY = gp.tileSize,frameWidth = gp.tileSize *6,frameHeight =gp.tileSize * 5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        final int slotXstart = frameX + 20;
        final int slotYstart = frameY +20;
        int slotX = slotXstart,slotY = slotYstart;

        //ITEMS
        for(int i =0 ; i<gp.player.inventory.size();i++){


            if(gp.player.inventory.get(i) == gp.player.currentWeapon ||
            gp.player.inventory.get(i) == gp.player.currentShield){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX,slotY,gp.tileSize-2,gp.tileSize-2,10,10);
            }

                g2.drawImage(gp.player.inventory.get(i).down1, slotX,slotY,null);
                slotX += gp.tileSize;
                if(i == 4 || i==9 || i == 14){
                    slotX = slotXstart;
                    slotY += gp.tileSize;
                }
        }


        //ACTIVE SLOT
        int cursorX=slotXstart + (gp.tileSize*slotCol),
                cursorY = slotYstart + (gp.tileSize*slotRow),
                cursorWidth = gp.tileSize,
                cursorHeight = gp.tileSize;

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,10,10);



        //DESCRIPTION WINDOW
        int dFrameX = frameX,dFrameY = frameY + frameHeight,dFrameWidth = frameWidth,dFrameHeight=gp.tileSize*3;

        //DESCRIPTION TEXT
        int textX = dFrameX + 20, textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(20F));

        int itemIndex = getItemIndexFromInv();

        if(itemIndex < gp.player.inventory.size()) {
            drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);
                for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
                    g2.drawString(line, textX, textY);
                    textY += 24;
                }
            }
    }
    public int getItemIndexFromInv(){
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }
}
