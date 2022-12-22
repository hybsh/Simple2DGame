package Main;


import Entity.Entity;
import Entity.Player;
import InteractiveTile.Interactive_Tile;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class gamePannel extends JPanel implements Runnable{
    int FPS = 60;
    final int originalTileSize = 16;
    final int scale=3;

    public final int tileSize = originalTileSize * scale;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    TileManager tileM = new TileManager(this);

    public keyHandler keyH = new keyHandler(this);
    Sound sound = new Sound();
    Sound music = new Sound();
    public userInterface UI = new userInterface(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;
    public collisionCheck checker = new collisionCheck(this);
    public entitySetter eSetter = new entitySetter(this);
    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[20];
    public Entity npc[] = new Entity[10];
    public Entity mob[] = new Entity[20];
    public Interactive_Tile iTile[] = new Interactive_Tile[50];
    ArrayList<Entity> entities = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();

    public int gameState;
    public final int startState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int statusState = 4;

    //Player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed=4;
    public gamePannel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void initGame(){
        eSetter.setObject();
        eSetter.setNPC();
        eSetter.setMobz();
        eSetter.setInteractiveTiles();
        gameState = startState;
    }
    public void startThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

    double drawInterval = 1000000000/FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    int drawCount =0;

        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime -lastTime) / drawInterval;
            timer +=(currentTime - lastTime);
            lastTime = currentTime;

            if(delta >=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer =0;
            }
        }
    }

    public void update(){


        if(gameState == playState) {
            player.update();
            for(int i = 0; i< npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
            for(int i=0; i< mob.length; i++){
                if(mob[i] != null){
                    if(mob[i].alive == true && mob[i].dying == false) {
                        mob[i].update();
                    }
                    if(mob[i].alive == false){
                        mob[i].checkDrop();
                        mob[i] = null;
                    }
                }
            }
            for(int i=0; i< projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    if(projectileList.get(i).alive == true) {
                        projectileList.get(i).update();
                    }
                    if(projectileList.get(i).alive == false){
                        projectileList.remove(i);
                    }
                }
            }
            for(int i=0; i< particleList.size(); i++){
                if(particleList.get(i) != null){
                    if(particleList.get(i).alive == true) {
                        particleList.get(i).update();
                    }
                    if(particleList.get(i).alive == false){
                        particleList.remove(i);
                    }
                }
            }
           for(int i = 0; i< iTile.length; i++){
               if(iTile[i] != null){
                   iTile[i].update();
               }
           }
        }
        if(gameState == pauseState){
           
        }

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        long drawStart = 0;
        if(keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        if(gameState == startState){
            UI.draw(g2);
        }
        else{
            tileM.draw(g2);

            for(int i = 0; i < iTile.length; i++){
                if(iTile[i] != null){
                    iTile[i].draw(g2);
                }
            }

            entities.add(player);
            for(int i=0; i< npc.length;i++){
                if(npc[i] != null){
                    entities.add(npc[i]);
                }
            }
            for(int i=0; i<obj.length; i++){
                if(obj[i] != null){
                    entities.add(obj[i]);
                }
            }
            for(int i=0; i<mob.length; i++){
                if(mob[i] != null){
                    entities.add(mob[i]);
                }
            }
            for(int i=0; i<projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    entities.add(projectileList.get(i));
                }
            }
            for(int i=0; i<particleList.size(); i++){
                if(particleList.get(i) != null){
                    entities.add(particleList.get(i));
                }
            }
            Collections.sort(entities, new Comparator<Entity>() {
                @Override
                public int compare(Entity o1, Entity o2) {

                    int result= Integer.compare(o1.worldY,o2.worldY);
                    return result;
                }
            });
            for(int i=0; i<entities.size(); i++){
                entities.get(i).draw(g2);
            }
            entities.clear();


            UI.draw(g2);
        }



        if(keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw time: " + passed, 10, 400);
            System.out.println("Draw time:" + passed);
        }
        g2.dispose();

    }

    public void playBgMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopBgMusic(){
        music.stop();
    }
    public void playSoundEffect(int i){
        sound.setFile(i);
        sound.play();
    }
}
