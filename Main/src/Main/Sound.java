package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    public int volumeScale = 3;
    public float volume;

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
        soundURL[9] = getClass().getResource("/SFX/Spell1.wav");
        soundURL[10] = getClass().getResource("/SFX/Spell2.wav");
        soundURL[11] = getClass().getResource("/SFX/Spell3.wav");
        soundURL[12] = getClass().getResource("/SFX/Dungeon.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
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
    public void checkVolume(){
        switch (volumeScale){
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);
    }


}