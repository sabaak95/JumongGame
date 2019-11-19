/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jumonggame;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import static jumonggame.Audio.values;

/**
 *
 * @author felan
 
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public enum Audio {   
   GAMEON("game-on.wav"),      
   ENTER("enter.wav"),
   OUTOFMAP("outofmap.wav"),
   WIN("win.wav"),
   BATTLE("battle.wav"),
   BOMB("bomb.wav");
   
   public static enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }

   public static Volume volume = Volume.LOW;

   private Clip clip;

   Audio(String name) {
      try {
           AudioInputStream audioInputStream = null;
            File in = new File(name);
            audioInputStream = AudioSystem.getAudioInputStream(in);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(4.0f); // Reduce volume by 10 decibels.
            
      } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        
          e.printStackTrace();
         
      }
   }

   public void play() {
      if (volume != Volume.MUTE) {
         if (clip.isRunning()){
             clip.stop();
         }
               
         clip.setFramePosition(0); 
         clip.start();     
      }
   }
   public void stop() {
             clip.stop();
         
       
   }
   static void init() {
      values();  }
}

