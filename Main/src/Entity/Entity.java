package Entity;

import Interfaces.Drawable;
import Interfaces.Updateable;
import Main.gamePannel;
import Main.utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Entity implements Updateable, Drawable {
    gamePannel gp;

    public boolean boss_attack = false;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public  BufferedImage attackup1,attackup2,attackdown1,attackdown2,attackleft1,attackleft2,attackright1,attackright2;
    public BufferedImage image,image2,image3;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogues[] = new String[20];



    //ENTITY STATE
    public int worldX,worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisonOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean knockback = false;


    //COUNTERS
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int shotAvailableCounter =0;
    public int knockbackCounter = 0;



    //ENTITY ATTRIBUTES

    public String name;
    public int defaultSpeed;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int XP;
    public int nextLvlXP;
    public int money;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentLight;
    public Projectile projectile;


    //ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInvSize = 20;
    public int attackVal;
    public int defenseVal;
    public String description = "";
    public int useCost;
    public int value;
    public int price = 20;
    public int knockbackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;


    //TYPES
    public int type; //0 = player, 1=NPC, 2=mob,
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_mob = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickUpOnly = 7;
    public final int type_obstacle = 8;
    public final int type_light = 9;




    public Entity(gamePannel gp){
        this.gp = gp;
    }
    public int getLeftX(){
        return worldX + solidArea.x;
    }
    public int getRightX(){
        return worldX + solidArea.x + solidArea.width;
    }
    public int getTopY(){
        return worldY + solidArea.y;
    }
    public int getBottomY(){
        return worldY + solidArea.y + solidArea.height;
    }
    public int getCol(){
        return (worldX + solidArea.x) / gp.tileSize;
    }
    public int getRow(){
        return (worldY + solidArea.y) / gp.tileSize;
    }
    public BufferedImage prepImg(String imagePath, int width, int height){
        utilities scaler = new utilities();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream( imagePath + ".png"));
            image = scaler.scaleImage(image , width, height);

        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
    public boolean use(Entity entity){return false;}

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        int tempScreenX = screenX;
        int tempScreenY = screenY;


        if(     worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize< gp.player.worldY + gp.player.screenX
        )
        {
            switch(direction){
                case "up":
                    if(attacking == false) {
                        if (spriteNum == 1) {
                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }
                    }
                    if(attacking == true){
                        tempScreenY = screenY - gp.tileSize;
                        if (spriteNum == 1) {
                            image = attackup1;
                        }
                        if (spriteNum == 2) {
                            image = attackup2;
                        }

                    }
                    break;

                case "down":
                    if(attacking == false) {
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                    }
                    if(attacking == true){
                        if (spriteNum == 1) {
                            image = attackdown1;
                        }
                        if (spriteNum == 2) {
                            image = attackdown2;
                        }
                    }
                    break;
                case "left":
                    if(attacking == false) {
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                    }
                    if(attacking == true){
                        tempScreenX = tempScreenX - gp.tileSize;
                        if (spriteNum == 1) {
                            image = attackleft1;
                        }
                        if (spriteNum == 2) {
                            image = attackleft2;
                        }
                    }
                    break;
                case "right":
                    if(attacking == false) {
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                    }
                    if(attacking == true){
                        if (spriteNum == 1) {
                            image = attackright1;
                        }
                        if (spriteNum == 2) {
                            image = attackright2;
                        }
                    }
                    break;
            }

            if(type == 2 && hpBarOn == true) {
                double oneScale = (double)gp.tileSize / maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-1,screenY-16, gp.tileSize +2,12);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

                hpBarCounter++;

                if(hpBarCounter >600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }

            }


            if(invincible == true){
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2,0.5f);
            }
            if(dying == true){
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY,null);

            changeAlpha(g2,1f);
        }
    }

    public void setAction(){}
    public void damageReaction(){}
    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.UI.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    public void interact(){

    }
    public void update(){

        if(knockback == true){

            gp.checker.checkTile(this);
            gp.checker.checkObject(this, false);
            gp.checker.checkEntity(this, gp.npc);
            gp.checker.checkEntity(this, gp.mob);
            gp.checker.checkEntity(this, gp.iTile);

            if(collisonOn == true){
                knockbackCounter = 0;
                knockback = false;
                speed = defaultSpeed;
            }
            else if(collisonOn == false){
                switch(gp.player.direction){
                    case "up": {
                        worldY -= speed;
                        break;
                    }
                    case "down": {
                        worldY += speed;
                        break;
                    }
                    case "left": {
                        worldX -= speed;
                        break;
                    }
                    case "right": {
                        worldX += speed;
                        break;
                    }
                }
            }
            knockbackCounter++;
            if(knockbackCounter == 10){
                knockback = false;
                knockbackCounter = 0;
                speed = defaultSpeed;
            }
        }
        else if(attacking == true){
            attacking();
        }
        else {
            setAction();

            collisonOn = false;
            gp.checker.checkTile(this);
            gp.checker.checkObject(this, false);
            gp.checker.checkEntity(this, gp.npc);
            gp.checker.checkEntity(this, gp.mob);
            gp.checker.checkEntity(this, gp.iTile);
            boolean contactPlayer = gp.checker.checkPlayer(this);

            if (this.type == type_mob && contactPlayer == true) {
                damagePlayer(attack);
            }


            if (collisonOn == false) {
                switch (direction) {
                    case "up": {
                        worldY -= speed;
                        break;
                    }
                    case "down": {
                        worldY += speed;
                        break;
                    }
                    case "left": {
                        worldX -= speed;
                        break;
                    }
                    case "right": {
                        worldX += speed;
                        break;
                    }
                }
            }
            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum =2;
                }
                else if(spriteNum == 2){
                    spriteNum =1;
                }
                spriteCounter = 0;
            }
        }


        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30){
            shotAvailableCounter ++;
        }
    }
    public void attacking(){

        spriteCounter++;
        if(spriteCounter <= 5){
            spriteNum=1;
        }
        if(spriteCounter >5 && spriteCounter <= 25){
            spriteNum=2;


            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction){
                case "up": worldY -= attackArea.height;break;
                case "down": worldY +=attackArea.height;break;
                case "left": worldX -= attackArea.width;break;
                case "right": worldX += attackArea.width;break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if(type == type_mob){
                if(gp.checker.checkPlayer(this) == true){
                    damagePlayer(attack);
                }
            }
            else{
                int mobIndex = gp.checker.checkEntity(this, gp.mob);
                gp.player.damageMob(mobIndex,attack, currentWeapon.knockbackPower);

                int iTileIndex = gp.checker.checkEntity(this,gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.checker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);

            }



            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;



        }
        if(spriteCounter >25){
            spriteNum=1;
            spriteCounter=0;
            attacking = false;
        }
    }
    public void checkAttack(int rate, int vertical, int horizontal){
        boolean targetInRange = false;
        int xDis = getXDistance(gp.player);
        int yDis = getYDistance(gp.player);

        switch (direction){
            case "up":
                if(gp.player.worldY < worldY && yDis < vertical && xDis < horizontal)
                    targetInRange = true;
                break;
            case "down":
                if(gp.player.worldY > worldY && yDis < vertical && xDis < horizontal)
                    targetInRange = true;
                break;
            case "left":
                if(gp.player.worldX < worldX && xDis < vertical && yDis < horizontal)
                    targetInRange = true;
                break;
            case "right":
                if(gp.player.worldX > worldX && xDis < vertical && yDis < horizontal)
                    targetInRange = true;
                break;
        }

        if(targetInRange == true){
           int i = new Random().nextInt(rate);
           if(i == 0){
               attacking = true;
               spriteNum = 1;
               spriteCounter = 0;
               shotAvailableCounter = 0;
           }
        }
    }
    public int getXDistance(Entity target){
        int xDistance = Math.abs(worldX - target.worldX);
        return xDistance;
    }
    public int getYDistance(Entity target){
        int yDistance = Math.abs(worldY - target.worldY);
        return yDistance;
    }

    public void damagePlayer(int attack){
        if(gp.player.invincible == false){
            gp.playSoundEffect(5);

            int damage = attack - gp.player.defense;
            if(damage < 0){
                damage = 0;
            }


            gp.player.life -= damage;
            System.out.println(gp.player.life);
            gp.player.invincible = true;
        }
    }
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        int i =5;

        if(dyingCounter <= i){
            changeAlpha(g2,0f);
        }
        if(dyingCounter > i && dyingCounter <=i*2){
            changeAlpha(g2,1f);
        }
        if(dyingCounter > i*2 && dyingCounter <=i*3){
            changeAlpha(g2,0f);
        }
        if(dyingCounter > i*3 && dyingCounter <=i*4){
            changeAlpha(g2,1f);
        }
        if(dyingCounter > i*4 && dyingCounter <=i*5){
            changeAlpha(g2,0f);
        }
        if(dyingCounter > i*5 && dyingCounter <=i*6){
            changeAlpha(g2,1f);
        }
        if(dyingCounter > i*6 && dyingCounter <=i*7){
            changeAlpha(g2,0f);
        }
        if(dyingCounter > i*7 && dyingCounter <=i*8){
            changeAlpha(g2,1f);

        }
        if(dyingCounter > i*8){
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public void checkDrop(){}
    public void dropItem(Entity droppedItem){
        for(int i = 0; i <gp.obj[1].length; i++){
            if(gp.obj[gp.currentMap][i] == null){
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }
    public Color getParticleColor(){
        Color color = null;
        return color;
    }
    public int getParticleSize(){
        int size = 0;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 0;
        return speed;
    }
    public int getMaxLife(){
        int maxLife = 0;
        return maxLife;
    }
    public void generateParticle(Entity generator, Entity target){
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getMaxLife();

        Particle p1 = new Particle(gp,target,color,size,speed,maxLife,-2,-1);
        Particle p2 = new Particle(gp,target,color,size,speed,maxLife,2,-1);
        Particle p3 = new Particle(gp,target,color,size,speed,maxLife,-2,1);
        Particle p4 = new Particle(gp,target,color,size,speed,maxLife,2,1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);

    }
    public int getDetected(Entity user, Entity target[][], String targetName){
        int index = 999;
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction){
            case "up": nextWorldY = user.getTopY() -1; break;
            case "down": nextWorldY = user.getBottomY() +1; break;
            case "left": nextWorldX = user.getLeftX() -1; break;
            case "right": nextWorldX = user.getRightX() +1; break;
        }

        int col = nextWorldX / gp.tileSize;
        int row = nextWorldY / gp.tileSize;

        for(int i = 0; i < target[1].length; i++){
            if(target[gp.currentMap][i] != null){
                if(target[gp.currentMap][i].getCol() == col &&
                        target[gp.currentMap][i].getRow() == row &&
                        target[gp.currentMap][i].name.equals(targetName)){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
}

