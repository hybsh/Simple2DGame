package Entity;

import Main.gamePannel;
import Main.keyHandler;
import Object.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Player extends Entity{
    keyHandler keyH;

    public final int screenX;
    public final int screenY;
    public boolean attacKCanceled = false;

    public Player(gamePannel gp, keyHandler keyH){

        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width =32;
        solidArea.height = 32;


        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    public void setDefaultPositions(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        direction = "down";
    }
    public void restoreLifeAndMana(){
        life = maxLife;
        mana = maxMana;
        invincible = false;
    }
    public void setDefaultValues(){
//        worldX = gp.tileSize * 10;
//        worldY = gp.tileSize * 13;

//        worldX = gp.tileSize * 12;
//        worldY = gp.tileSize * 13;

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down";

        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        strength =1;
        dexterity =1;
        XP = 0;
        nextLvlXP = 10;
        money = 500;
        currentWeapon = new OBJ_SWORD_NORMAL(gp);
        currentShield = new OBJ_SHIELD_NORMAL(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defense = getDefense();
    }

    public void setItems(){

        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));

    }

    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackVal;

    }
    public int getDefense(){
        return defense = dexterity * currentShield.defenseVal;
    }
    public void getPlayerImage(){

        up1 = prepImg("/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = prepImg("/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = prepImg("/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = prepImg("/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = prepImg("/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = prepImg("/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = prepImg("/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = prepImg("/player/boy_right_2", gp.tileSize, gp.tileSize);

    }
    public void getPlayerAttackImage(){
        if(currentWeapon.type == type_sword) {
            attackup1 = prepImg("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
            attackup2 = prepImg("/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
            attackdown1 = prepImg("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
            attackdown2 = prepImg("/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
            attackleft1 = prepImg("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
            attackleft2 = prepImg("/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
            attackright1 = prepImg("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
            attackright2 = prepImg("/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
        }
        if(currentWeapon.type == type_axe){
            attackup1 = prepImg("/player/boy_axe_up_1", gp.tileSize, gp.tileSize * 2);
            attackup2 = prepImg("/player/boy_axe_up_2", gp.tileSize, gp.tileSize * 2);
            attackdown1 = prepImg("/player/boy_axe_down_1", gp.tileSize, gp.tileSize * 2);
            attackdown2 = prepImg("/player/boy_axe_down_2", gp.tileSize, gp.tileSize * 2);
            attackleft1 = prepImg("/player/boy_axe_left_1", gp.tileSize * 2, gp.tileSize);
            attackleft2 = prepImg("/player/boy_axe_left_2", gp.tileSize * 2, gp.tileSize);
            attackright1 = prepImg("/player/boy_axe_right_1", gp.tileSize * 2, gp.tileSize);
            attackright2 = prepImg("/player/boy_axe_right_2", gp.tileSize * 2, gp.tileSize);

        }
    }

    public void update(){
        if(attacking == true){
            attacking();
        }
        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true ||
                keyH.enterPressed == true){
            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }

            collisonOn = false;
            gp.checker.checkTile(this);
            int objIndex = gp.checker.checkObject(this,true);
            pickUpObject(objIndex);

            int npcIndex = gp.checker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            int mobIndex = gp.checker.checkEntity(this, gp.mob);
            mobHits(mobIndex);

            int iTileIndex = gp.checker.checkEntity(this, gp.iTile);


            gp.eHandler.checkEvent();


            if(collisonOn == false && keyH.enterPressed == false){
                switch(direction){
                    case "up":{
                        worldY-=speed;
                        break;
                    }
                    case "down":{
                        worldY+=speed;
                        break;
                    }
                    case "left":{
                        worldX -= speed;
                        break;
                    }
                    case "right":{
                        worldX += speed;
                        break;
                    }
                }
            }

            if(keyH.enterPressed == true && attacKCanceled == false){
                gp.playSoundEffect(4);
                attacking = true;
                spriteCounter = 0;
            }

            attacKCanceled = false;
            gp.keyH.enterPressed = false;

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
        if(gp.keyH.shotKeyPressed == true
                && projectile.alive == false
                && shotAvailableCounter == 30
                && projectile.hasMana(this) == true){

            projectile.set(worldX,worldY,direction,true,this);

            projectile.spendMana(this);


            for(int i=0; i<gp.projectile[1].length; i++){
                if(gp.projectile[gp.currentMap][i] == null){
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }


            shotAvailableCounter = 0;

            int randomSpellSound = new Random().nextInt(4)+8;
            gp.playSoundEffect(randomSpellSound);

        }


        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter >60){
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if(shotAvailableCounter < 30){
            shotAvailableCounter ++;
        }
        if(life > maxLife){
            life = maxLife;
        }
        if(mana > maxMana){
            mana = maxMana;
        }
        if(life <= 0){
            gp.gameState = gp.gameOverState;
            gp.UI.commandNum = -1;
        }
    }
    public void pickUpObject(int index){

        if( index != 999) {

            //COINS
            if (gp.obj[gp.currentMap][index].type == type_pickUpOnly) {
                 gp.obj[gp.currentMap][index].use(this);
                 gp.obj[gp.currentMap][index] = null;
            }

            else {
                //SHOW IN INV
                String text;

                if (inventory.size() != maxInvSize) {
                    inventory.add(gp.obj[gp.currentMap][index]);
                    gp.playSoundEffect(2);
                    text = "You got a " + gp.obj[gp.currentMap][index].name;
                } else {
                    text = "Inventory full!";
                }

                gp.UI.addMessage(text);
                gp.obj[gp.currentMap][index] = null;

            }
        }

    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

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

        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY,null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //DEBUG TEST
//        g2.setFont(new Font("Arial", Font.PLAIN, 25));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible: " + invincibleCounter, 10,400);

    }

    public void interactNPC(int i){
        if(gp.keyH.enterPressed == true){
            if(i != 999){
                attacKCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }

        }
    }

    public void mobHits(int i){
        if (i != 999 && gp.mob[gp.currentMap][i].dying == false){
            if(invincible == false){
                gp.playSoundEffect(5);
                int damage = gp.mob[gp.currentMap][i].attack - defense;
                if(damage < 0){
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
        }
    }
    public void damageMob(int i, int attack,int knockbackPower){
        if(i != 999){
           if(gp.mob[gp.currentMap][i].invincible == false){
               gp.playSoundEffect(3);

               if(knockbackPower > 0) {
                   knockback(gp.mob[gp.currentMap][i], knockbackPower);
               }

               int damage = attack - gp.mob[gp.currentMap][i].defense;
               if(damage < 0){
                   damage = 0;
               }

               gp.mob[gp.currentMap][i].life -= damage;
               gp.UI.addMessage(damage + "damage!");
               gp.mob[gp.currentMap][i].invincible = true;
               gp.mob[gp.currentMap][i].damageReaction();

               if(gp.mob[gp.currentMap][i].life < 0){
                   gp.mob[gp.currentMap][i].dying = true;
                   gp.UI.addMessage("Killed a " + gp.mob[gp.currentMap][i].name + "!");
                   gp.UI.addMessage("You recieved " + gp.mob[gp.currentMap][i].XP + " XP!" );
                   XP += gp.mob[gp.currentMap][i].XP;
                   checkLvlUp();
               }
           }
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

            int mobIndex = gp.checker.checkEntity(this, gp.mob);
            damageMob(mobIndex,attack, currentWeapon.knockbackPower);

            int iTileIndex = gp.checker.checkEntity(this,gp.iTile);
            damageInteractiveTile(iTileIndex);

            int projectileIndex = gp.checker.checkEntity(this, gp.projectile);
            damageProjectile(projectileIndex);


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
    public void damageProjectile(int i){
        if(i != 999){
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile,projectile);
        }
    }

    public void checkLvlUp(){
        if(XP >= nextLvlXP){
            level++;
            nextLvlXP = nextLvlXP * 2;
            maxLife +=2;
            strength ++;
            dexterity ++;
            attack = getAttack();
            defense = getDefense();
            life = maxLife;
            mana = maxMana;

            gp.playSoundEffect(6);
            gp.gameState = gp.dialogueState;

            gp.UI.currentDialogue = "You have achieved level " + level + " now! \n The gods have rewarded you! \n You feel better than ever!";

        }
    }
    public void selectItem(){
        int itemIndex = gp.UI.getItemIndexFromInv(gp.UI.playerSlotCol,gp.UI.playerSlotRow);
        System.out.println("Item index : " + itemIndex);
        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if(selectedItem.type == type_sword || selectedItem.type == type_axe){
                currentWeapon = selectedItem;
                attack=getAttack();
                getPlayerAttackImage();
            }
            if(selectedItem.type == type_shield){
                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_consumable){
                selectedItem.use(this);
                inventory.remove(itemIndex);
                //later
            }

        }
    }
    public void damageInteractiveTile(int i){
        if(i != 999 && gp.iTile[gp.currentMap][i].destructible == true
                && gp.iTile[gp.currentMap][i].useCorrectItem(this) == true
                && gp.iTile[gp.currentMap][i].invincible == false){
            gp.iTile[gp.currentMap][i].life --;
            gp.iTile[gp.currentMap][i].invincible = true;
            generateParticle(gp.iTile[gp.currentMap][i],gp.iTile[gp.currentMap][i]);


            if(gp.iTile[gp.currentMap][i].life == 0) {
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
            }
        }
    }
    public void knockback(Entity entity, int knockbackPower){
        entity.direction = direction;
        entity.speed += knockbackPower;
        entity.knockback = true;
    }
}
