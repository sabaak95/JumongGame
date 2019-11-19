/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumonggame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class JumongGame {
static int multi_or_single;

    public static void main(String[] args) {
    
        
    //System.out.println("Enter 1 for single 2 for multi");
    
    //Scanner inpt = new Scanner(System.in);
   // multi_or_single=inpt.nextInt();
    
   // if(multi_or_single==1){ // action listener button1
    Single singlePlayer = new Single(); 
    singlePlayer.RunGame();
   
    

}
    

}


