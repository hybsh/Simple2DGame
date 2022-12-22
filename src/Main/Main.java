package Main;

import javax.swing.*;

public class Main {

    public static JFrame window;
    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Yay");
        //window.setUndecorated(true);

        gamePannel gamePannel = new gamePannel();
        window.add(gamePannel);

        gamePannel.config.loadConfig();

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePannel.initGame(args[0]);
        gamePannel.startThread();

    }
}