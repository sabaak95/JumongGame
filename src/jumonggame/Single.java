package jumonggame;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import jumonggame.Hero;
import jumonggame.Item;
import jumonggame.Map;
import jumonggame.Tile;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author felan
 */
public class Single {
    public Map map;
    public Hero hero1;
    
    
    
        public void RunGame() {
        map = new Map(); // by doing this , maze is generated and gold and enemies are in their places
        hero1 = new Hero(0,0);
        map.tiles[0][0].isVisited=true;
        // get command . goes to different methods
        //if == Up || down || right || left
        Graphic graphics=new Graphic(map,hero1);
        graphics.setUndecorated(true);
        graphics.setResizable(false);
        graphics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        graphics.setUndecorated(true);
        graphics.setVisible(true);
        
        
        boolean game_is_on = true;
        boolean gameWon=false;
        boolean lostGame = false;
        while (game_is_on == true) {
            // if show map was a independent command , we can make it a method itself
            showMap();
            goldAndEnemyPrinter();
            // printItems check having a chest before printing , if there is a chest , doesnt print items
            printItems();
            System.out.println("Enter the Command ");
            Scanner scanner = new Scanner(System.in);
            String command_order = scanner.nextLine();
            if (command_order.matches("up|down|left|right")) {
                hero1.Move(graphics,map,command_order);
            } 
            else if (command_order.equals("status")) {
                System.out.println("Hitpoints: " + hero1.getHitpoint());
            } 
            else if (command_order.equals("inventory")) {
                System.out.println("Inventory :  ");
                for (Item item_for_printing : hero1.inventories) {
                    System.out.println("" + item_for_printing.name);
                }
            } 
            else if (command_order.matches("pick .+")) {
                String[] parts = command_order.split(" ");//part 0 is the command "pick " , part 1 is item name

                Tile current_tile = map.tiles[hero1.getX()][hero1.getY()];
                 // Even it has an locked chest,there is NOTHING in the items array list to pick.
                // and the items are still in the chests array list ( u can pick them when the chest is unlocked
                // and it's items will be added to the tiles array list

                    hero1.Pick(current_tile, parts[1]);
            } 
            
            else if (command_order.matches("drop .+")) {
                String[] parts = command_order.split(" ");

                Tile current_tile = map.tiles[hero1.getX()][hero1.getY()];
                hero1.Drop(current_tile, parts[1]);//part 0 is the command "Drop " , part 1 is item name
            } 
            
            else if (command_order.matches("use .+")) {
                String[] parts = command_order.split(" ");
                hero1.Use(map, parts[1]);
                
                if (parts[1].equals("shovel")) // if the shovling point is not a wall, x is updated and everything ok . if not , at least reducer should work
                    hero1.reducers(map);// whether the x to dig is wall or not , reducers should work .
                
                if( parts[1].equals("key") ){  // if only player has got a key the chest is unlocked 
                    Tile current_tile = map.tiles[hero1.getX()][hero1.getY()];
                    if (current_tile.hasChest ==true && current_tile.isLocked==false ){ // if it has been unlocked by using the key and the items updated
                    System.out.println("Items Are :  ");
                    printItems();
                    }
                    
                    else if( current_tile.hasChest==true && current_tile.isLocked==true )
                    System.out.println("Chest Still locked ");
                    
                    else 
                    System.out.println("There is no chests Here for unlocking");

                     }
                
            }
            else if (command_order.matches("killEnemy .+")) {
                String[] parts = command_order.split(" ");

                if (parts[1].matches("smallarrow|firearrow|bigarrow")) {
                    hero1.Use(map, parts[1]);
                } 
                else {
                    System.out.println("Enemies cannot be killed by this weapon"); // because that item is not used for killing
                }

            } 
            else if (command_order.matches("unlock")) {
                Tile current_tile = map.tiles[hero1.getX()][hero1.getY()];
                if (current_tile.hasChest == true) { // it sees if it has got a chest or not
                    hero1.Use(map, "key"); // having a key is checked in the use method of class player
                    if (current_tile.isLocked == false ) {// it means it is unlocked
                    System.out.println("Chest Unlocked :) This chest contains ");
                    printItems();
                    } 
                    else if( current_tile.isLocked==true)
                    System.out.println("Chest Still locked ");
                }
                if(current_tile.hasChest == false)
                System.out.println("There is no chest here for unlocking : ");   
            }
            
            
            
            // we see if the game should go on or finish . if the player entered a room which is the end , 
            // we say congrats and print gold and hitpoint and energy and we break out of the while
            
            if (hero1.getX()== map.dst_x && hero1.getY()== map.dst_y){
                System.out.println("YOOOOHOOOO YOU WON THE GAME !!! ");
                System.out.println("Amount of gold gathered is :" + hero1.getGold());
                System.out.println("HitPoints :" + hero1.getHitpoint());
                System.out.println("Energy:" + hero1.getEnergy());
                gameWon=true;
                break;
                
            }
            if (hero1.getEnergy() == 0) {
                lostGame = true;
            }
            
            if (hero1.getHitpoint() <= 0) {
                System.out.println("You can use your reviveScroll if you have got it in your inventory . enter <revivescroll>");
                String reviving_command = scanner.next();
                
                if(reviving_command.equals("revivescroll"))
		hero1.Use(map, "revivescroll");
                
                if(hero1.getHitpoint() <= 0) { // it means the we didnt have revive scroll
		lostGame=true;
                }
                
            }
            
            if (hero1.getEnergy() == 0) {
            lostGame = true;
            }
            
        if (lostGame == true){
        game_is_on=false;
        System.out.println("YOU LOST ! ");
        }
        }
    }
        
        public void showMap() {
        
        System.out.println("\t  0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 "); 
        System.out.println(); 
 
        for (int row = 0; row < 25; row++) { 
            System.out.print((row ) + " \t"); 
            for (int column = 0; column < 25; column++) { 
            //    if (map.tiles[row][column].isVisited == true) {
                    if(map.tiles[row][column].isWall==true){
                        System.out.print("  " + "#"); 
                           
                    }
                    else if(map.tiles[row][column].isWall==false){
                        if(row==hero1.getX() && column==hero1.getY()){
                             System.out.print("  " + "p");
                        }
                        else if(map.tiles[row][column].items.size()>0){
                            System.out.print("  "+ map.tiles[row][column].items.size());}
                        else
                            System.out.print("  " + ".");  
                        }
                    }
               // }
               // else if (map.tiles[row][column].isVisited == false) { 
                 //       System.out.print("  " + "?"); 
               // }
             //  } 
            System.out.println();
            } 
        }
    
    public void printItems(){
        Tile new_tile = map.tiles[hero1.getX()][hero1.getY()]; // because we also want to update the tiles array in map
        if (new_tile.isWall == false) {// if it is not a wall 
            if (new_tile.isLocked == false) {
                System.out.println("Items in this tile :  "); 
                // print the items
                for (Item item_to_print : new_tile.items) {
                    System.out.println("" + item_to_print.name);
                }
            } 
            else if (new_tile.isLocked == true) {
                System.out.println("This tile contains chest .if you have a key, Use command <Use key/unlock> to open it ");
            }
    }
    }
    // upon entering a tile ,golds and enemies get printed
    public void goldAndEnemyPrinter(){
        Tile new_tile = map.tiles[hero1.getX()][hero1.getY()]; // because we also want to update the tiles array in map
        if (new_tile.isWall == false) {// if it is not a wall 
            //hero1.updateLocations(new_x, new_y);
            
            System.out.println("Amount of gold :  " + new_tile.getGold());
            int added_score=new_tile.getGold();
            hero1.updateScore(added_score);
            new_tile.updateGold(0); // make the gold in that tile 0 and add it to score
    }
    }

    
 
}
