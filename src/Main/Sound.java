package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/SFX/Hudson_Mohawke_Cbat.wav");
        soundURL[1] = getClass().getResource("/SFX/Roblox_Death_Sound_Sound_Effect_HD_.wav");
        soundURL[2] = getClass().getResource("/SFX/Minecraft_Item_Drop_Sound_Effect_.wav");
        soundURL[3] = getClass().getResource("/SFX/DeathSound.wav");
        soundURL[4] = getClass().getResource("/SFX/SwordSwingSound.wav");
        soundURL[5] = getClass().getResource("/SFX/TakeDamageSound.wav");
        soundURL[6] = getClass().getResource("/SFX/LVL-UP.wav");
        soundURL[7] = getClass().getResource("/SFX/DrinkFrom.wav");
        soundURL[8] = getClass().getResource("/SFX/Spell.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e){

        }
    }

    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }


}