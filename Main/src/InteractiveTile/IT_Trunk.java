package InteractiveTile;

import Main.gamePannel;

public class IT_Trunk extends Interactive_Tile{
    gamePannel gp;
    public IT_Trunk(gamePannel gp, int col, int row) {
        super(gp, col, row);

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        solidArea.x =0;
        solidArea.y =0;
        solidArea.height = 0;
        solidArea.width = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        down1 = prepImg("/InteractiveTiles/trunk", gp.tileSize, gp.tileSize);
    }

}
