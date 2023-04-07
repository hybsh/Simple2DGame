package Main;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static JFrame window;
    public static GUI login;
    public static int validateLogin = 0;
    public static void main(String[] args) {
            login = new GUI();
            login.GUI(args[0],args[1]);
        }
        public static void startGame(String arg, String role,String level){
            window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("Yay");
            //window.setUndecorated(true);

            gamePannel gamePannel = new gamePannel(role,level);
            window.add(gamePannel);

            gamePannel.config.loadConfig();

            window.pack();

            window.setLocationRelativeTo(null);
            window.setVisible(true);
            gamePannel.initGame(arg,role);
            gamePannel.startThread();
        }
    }