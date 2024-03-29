package Main;

import Entity.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class keyHandler implements KeyListener {

    gamePannel gp;

    public keyHandler(gamePannel gp){
        this.gp = gp;
    }

    public boolean upPressed,downPressed,leftPressed,rightPressed,enterPressed,shotKeyPressed;
    public boolean checkDrawTime = false;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //TITLE STATE
        if(gp.gameState == gp.startState){
            startState(code);
        }
        else if(gp.gameState == gp.playState){
            playState(code);
        }
        else if(gp.gameState == gp.pauseState){
            pauseState(code);
        }
        else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
        else if(gp.gameState == gp.statusState){
            statusState(code);
        }
        else if(gp.gameState == gp.optionsState){
            optionsState(code);
        }
        else if(gp.gameState == gp.gameOverState){
            gameOverState(code);
        }
        else if(gp.gameState == gp.tradeState){
            tradeState(code);
        }
        else if(gp.gameState == gp.mapState){
            mapState(code);
        }

    }

    private void optionsState(int code) {

        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
            gp.UI.subState = 0;
        }

        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (gp.UI.subState){
            case 0: maxCommandNum = 4;break;
            case 3: maxCommandNum = 1; break;
        }

        if(code == KeyEvent.VK_UP){
            gp.UI.commandNum --;
            if(gp.UI.commandNum < 0){
                gp.UI.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_DOWN){
            gp.UI.commandNum ++;
            if(gp.UI.commandNum > maxCommandNum){
                gp.UI.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_LEFT){
            if(gp.UI.subState == 0){
                if(gp.UI.commandNum == 0 && gp.music.volumeScale > 0){
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                }
                if(gp.UI.commandNum == 1 && gp.sound.volumeScale > 0){
                    gp.sound.volumeScale--;
                }
            }
        }
        if(code == KeyEvent.VK_RIGHT){
            if(gp.UI.subState == 0){
                if(gp.UI.commandNum == 0 && gp.music.volumeScale < 5){
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                }
                if(gp.UI.commandNum == 1 && gp.sound.volumeScale < 5){
                    gp.sound.volumeScale++;
                }
            }
        }
    }


    public void startState(int code){
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            gp.UI.commandNum--;
            if(gp.UI.commandNum <0){
                gp.UI.commandNum = 2;
            }
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            gp.UI.commandNum++;
            if(gp.UI.commandNum >2){
                gp.UI.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.UI.commandNum == 0){
                gp.gameState = gp.playState;
                gp.playBgMusic(0);
            }
            if(gp.UI.commandNum ==1){

            }
            if(gp.UI.commandNum == 2){
                Main.login.saveConfigToDB(gp.music.volumeScale,gp.sound.volumeScale);
                System.exit(0);
            }
        }

    }




    public void playState(int code){
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed=true;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed=true;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_C || code == KeyEvent.VK_TAB || code == KeyEvent.VK_I){
            gp.gameState = gp.statusState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(code == KeyEvent.VK_F){
            shotKeyPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionsState;
        }
        if(code == KeyEvent.VK_M){
            gp.gameState = gp.mapState;
        }
        if(code == KeyEvent.VK_X){
            if(gp.map.minimapOn == false){
                gp.map.minimapOn = true;
            }
            else{
                gp.map.minimapOn = false;
            }
        }




        if(code == KeyEvent.VK_T){
            if(checkDrawTime == false){
                checkDrawTime = true;
            }
            else if(checkDrawTime == true){
                checkDrawTime = false;
            }
        }
    }



    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }



    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
        }
    }



    public void statusState(int code){
        if(code == KeyEvent.VK_C || code == KeyEvent.VK_I || code == KeyEvent.VK_TAB){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.UI.playerSlotRow >= 0 && gp.UI.playerSlotRow < 5)
                gp.player.selectItem();
        }
        playerInv(code);
    }
    public void gameOverState(int code){
        if(code == KeyEvent.VK_UP){
            gp.UI.commandNum --;
            if(gp.UI.commandNum < 0){
                gp.UI.commandNum = 2;
            }
        }
        if(code == KeyEvent.VK_DOWN){
            gp.UI.commandNum ++;
            if(gp.UI.commandNum > 2){
                gp.UI.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.UI.commandNum == 0){
                gp.gameState = gp.playState;
                gp.player.invincible = false;
                gp.retry();
            }
            else if(gp.UI.commandNum == 1){
                gp.restart();
                gp.player.invincible = false;
                gp.gameState = gp.playState;
            }
            else if(gp.UI.commandNum == 2){
                gp.UI.commandNum = 0;
                System.exit(0);
            }
        }
    }
    public void tradeState(int code){
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(gp.UI.subState == 0){
            if(code == KeyEvent.VK_UP){
                gp.UI.commandNum--;
                if(gp.UI.commandNum < 0){
                    gp.UI.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_DOWN){
                gp.UI.commandNum++;
                if(gp.UI.commandNum > 2){
                    gp.UI.commandNum = 0;
                }
            }
        }
        if(gp.UI.subState == 1){
            npcInv(code);
            if(code == KeyEvent.VK_ESCAPE)
                gp.UI.subState = 0;
        }
        if(gp.UI.subState == 2){
            playerInv(code);
            if(code == KeyEvent.VK_ESCAPE)
                gp.UI.subState = 0;
        }
    }
    public void playerInv(int code){
        if(code == KeyEvent.VK_UP){
            if(gp.UI.playerSlotRow != 0)
                gp.UI.playerSlotRow --;
        }
        if(code == KeyEvent.VK_DOWN){
            if(gp.UI.playerSlotRow != 3)
                gp.UI.playerSlotRow ++;
        }
        if(code == KeyEvent.VK_LEFT){
            if(gp.UI.playerSlotCol != 0)
                gp.UI.playerSlotCol --;
        }
        if(code == KeyEvent.VK_RIGHT){
            if(gp.UI.playerSlotCol != 4)
                gp.UI.playerSlotCol ++;
        }
    }

    public void npcInv(int code){
        if(code == KeyEvent.VK_UP){
            if(gp.UI.npcSlotRow != 0)
                gp.UI.npcSlotRow --;
        }
        if(code == KeyEvent.VK_DOWN){
            if(gp.UI.npcSlotRow != 3)
                gp.UI.npcSlotRow ++;
        }
        if(code == KeyEvent.VK_LEFT){
            if(gp.UI.npcSlotCol != 0)
                gp.UI.npcSlotCol --;
        }
        if(code == KeyEvent.VK_RIGHT){
            if(gp.UI.npcSlotCol != 4)
                gp.UI.npcSlotCol ++;
        }
    }
    public void mapState(int code){
        if(code == KeyEvent.VK_M){
            gp.gameState = gp.playState;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed=false;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed=false;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_F){
            shotKeyPressed = false;
        }

    }
}
