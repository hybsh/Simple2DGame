package Tile;

import Main.gamePannel;
import Main.utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    gamePannel gp;
    public Tile[] tile;
    public int mapTileNum[][][];

    public TileManager(gamePannel gp){
        this.gp =gp;

        tile = new Tile[50];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/Maps/DoneWorld.txt",0);
        loadMap("/Maps/Interior01.txt",1);
    }
    public void loadMap(String mapPath, int map){
        try{
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();

                while(col < gp.maxWorldCol){

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row ++;
                }
            }
            br.close();
        }catch (Exception e){

        }
    }

    public void getTileImage(){
            prepImg(0, "grass00", false);
            prepImg(1, "grass00", true);
            prepImg(2, "grass00", true);
            prepImg(3, "grass00", false);
            prepImg(4, "grass00", true);
            prepImg(5, "grass00", false);
            prepImg(6, "grass00", false);
            prepImg(7, "grass00", false);
            prepImg(8, "grass00", false);
            prepImg(9, "grass00", false);

            prepImg(10, "grass00", false);
            prepImg(11, "grass01", false);
            prepImg(12, "water00", true);
            prepImg(13, "water01", true);
            prepImg(14, "water02", true);
            prepImg(15, "water03", true);
            prepImg(16, "water04", true);
            prepImg(17, "water05", true);
            prepImg(18, "water06", true);
            prepImg(19, "water07", true);
            prepImg(20, "water08", true);
            prepImg(21, "water09", true);
            prepImg(22, "water10", true);
            prepImg(23, "water11", true);
            prepImg(24, "water12", true);
            prepImg(25, "water13", true);
            prepImg(26, "road00", false);
            prepImg(27, "road01", false);
            prepImg(28, "road02", false);
            prepImg(29, "road03", false);
            prepImg(30, "road04", false);
            prepImg(31, "road05", false);
            prepImg(32, "road06", false);
            prepImg(33, "road07", false);
            prepImg(34, "road08", false);
            prepImg(35, "road09", false);
            prepImg(36, "road10", false);
            prepImg(37, "road11", false);
            prepImg(38, "road12", false);
            prepImg(39, "earth", false);
            prepImg(40, "wall", true);
            prepImg(41, "tree", true);

            prepImg(42,"hut",false);
            prepImg(43,"floor01",false);
            prepImg(44,"table01",true);
            prepImg(49,"black",true);
    }

    public void prepImg(int index, String imageName, boolean colission){
        utilities scaler = new utilities();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
            tile[index].image = scaler.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = colission;

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
       int worldCol =0;
       int worldRow =0;



       while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

           int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

           int worldX = worldCol * gp.tileSize;
           int worldY = worldRow * gp.tileSize;
           int screenX = worldX - gp.player.worldX + gp.player.screenX;
           int screenY = worldY - gp.player.worldY + gp.player.screenY;


           if(      worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize< gp.player.worldY + gp.player.screenX
           )
           {
               g2.drawImage(tile[tileNum].image, screenX, screenY,null);
           }
           worldCol++;


           if(worldCol == gp.maxWorldCol){
               worldCol = 0;
               worldRow++;

           }
       }
    }
}
