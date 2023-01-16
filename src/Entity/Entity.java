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

public class Entity implements Updateable, Drawable {
    gamePannel gp;
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




    public Entity(gamePannel gp){
        this.gp = gp;
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
    public void use(Entity entity){};

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;


        if(     worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize< gp.player.worldY + gp.player.screenX
        )
        {
            switch(direction){
                case "up":
                    if(spriteNum == 1){
                        image = up1;
                    }
                    if(spriteNum == 2){
                        image = up2;
                    }

                    break;
                case "down":
                    if(spriteNum == 1){
                        image = down1;
                    }
                    if(spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1){
                        image = left1;
                    }
                    if(spriteNum == 2){
                        image = left2;
                    }
                    break;
                case "right":
                    if(spriteNum == 1){
                        image = right1;
                    }
                    if(spriteNum == 2){
                        image = right2;
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
    public void damagePlayer(int attack){
        if(gp.player.invincible == false){
            gp.playSoundEffect(5);

            int damage = attack - gp.player.defense;
            if(damage < 0){
                damage = 0;
            }

            gp.player.life -= damage;
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
}

