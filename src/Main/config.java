package Main;

import java.io.*;

public class config {
    gamePannel gp;

    public config(gamePannel gp){
        this.gp = gp;
    }
    public void saveConfig(){
    try {

        BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

        bw.write(String.valueOf(gp.music.volumeScale));
        bw.newLine();

        bw.write(String.valueOf(gp.sound.volumeScale));
        bw.newLine();

        bw.close();
    }
    catch (IOException e){
    e.printStackTrace();
}
    }
    public void loadConfig(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            s = br.readLine();
            gp.sound.volumeScale = Integer.parseInt(s);

            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
