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

import java.util.Scanner;

public class JumongGame {

    public Map map;
    public Player player1;

    public static void main(String[] args) {
        JumongGame g = new JumongGame();
        g.newGame();
        g.startGame();
    }

    public void newGame() {
        map = new Map();
        player1 = new Player();
        map.tiles[0][0].isVisited=true;
        // get command . goes to different methods
        //if == Up || down || right || left
    }

    private void startGame() {
        boolean game_is_on = true;
        boolean gameWon=false;
        boolean lostGame = false;
        while (game_is_on == true) {
            // if show map was a independent command , we can make it a method itself
            showMap();
            goldAndEnemyPrinter();
            // printItems check having a chest before printing , if there is a chest , doesnt print items
            printItems();
            System.out.println("Enter the Command ( Up/down/left/right to move - Inventory to show items inside - Gold to see amount of gold - Hitpoint/energy to view  ");
            Scanner scanner = new Scanner(System.in);
            String command_order = scanner.nextLine();
            if (command_order.matches("up|down|left|right")) {
                Move(command_order, map, player1);
            } 
            else if (command_order.equals("hitpoint")) {
                System.out.println("Hitpoints: " + player1.getHitpoint());
            } 
            
            else if (command_order.equals("energy")) {
                System.out.println("Energy: " + player1.getEnergy());
            }
            
             else if (command_order.equals("gold")) {
                System.out.println("Gained gold: " + player1.getGainedGold());
            }
            
            else if (command_order.equals("inventory")) {
                System.out.println("Inventory :  ");
                for (Item item_for_printing : player1.inventories) {
                    System.out.println("" + item_for_printing.name);
                }
            } 
            else if (command_order.matches("pick .+")) {
                String[] parts = command_order.split(" ");//part 0 is the command "pick " , part 1 is item name

                Tile current_tile = map.tiles[player1.getX()][player1.getY()];
                if (current_tile.hasChest == false) { // if it is not locked, u can pick it
                    player1.Pick(current_tile, parts[1]);
                }
                else if ( current_tile.hasChest==true){ // if the chest is not opened , dont let it pick !!! 
                    System.out.println("You cannot pick an item form a Chest which is still unlocked . Use a key to unlock it  ");
                }
                
            } 
            else if (command_order.matches("drop .+")) {
                String[] parts = command_order.split(" ");

                Tile current_tile = map.tiles[player1.getX()][player1.getY()];
                player1.Drop(current_tile, parts[1]);//part 0 is the command "Drop " , part 1 is item name
            } 
            else if (command_order.matches("use .+")) {
                String[] parts = command_order.split(" ");

                player1.Use(map, parts[1]);
                
                if (parts[1].equals("shovel")) // if the shovling point is not a wall, x is updated and everything ok . if not , at least reducer should work
                    reducers();// whether the x to dig is wall or not , reducers should work .
                
                if( parts[1].equals("key") ){  // if only it is unlocked
                    Tile current_tile = map.tiles[player1.getX()][player1.getY()];
                    if (current_tile.hasChest ==false ){ // if it has been unlocked by using the key
                    System.out.println("Chest Unlocked :) This chest contains ");
                    printItems();
                     }
                    
                    else if( current_tile.hasChest==true)
                    System.out.println("Chest Still locked ");
                    
                     }
                
            }
            else if (command_order.matches("killEnemy .+")) {
                String[] parts = command_order.split(" ");

                if (parts[1].matches("smallarrow|firearrow|bigarrow")) {
                    player1.Use(map, parts[1]);
                } 
                else {
                    System.out.println("Enemies cannot be killed"); // because that item is not used for killing
                }

            } 
            else if (command_order.matches("unlock")) {
                Tile current_tile = map.tiles[player1.getX()][player1.getY()];
                if (current_tile.hasChest == true) {
                    player1.Use(map, "key"); // having a key is checked in the use method of class player
                    if (current_tile.hasChest == false ) {// it means it is unlocked
                    System.out.println("Chest Unlocked :) This chest contains ");
                    printItems();
                    } 
                    else if( current_tile.hasChest==true)
                    System.out.println("Chest Still locked ");
                }
                if(current_tile.hasChest == false)
                System.out.println("There is no chest here for unlocking : ");   
            }
            // we see if the game should go on or finish . if the player entered a room which is the end , 
            // we say congrats and print gold and hitpoint and energy and we break out of the while
            
            if (player1.getX()== map.dst_x && player1.getY()== map.dst_y){
                System.out.println("YOOOOHOOOO YOU WON THE GAME !!! ");
                System.out.println("Amount of gold gathered is :" + player1.getGold());
                System.out.println("HitPoints :" + player1.getHitpoint());
                System.out.println("Energy:" + player1.getEnergy());
                gameWon=true;
                break;
                
            }
            if (player1.getEnergy() == 0) {
                lostGame = true;
            }
            
            if (player1.getHitpoint() <= 0) {
                System.out.println("You can use your reviveScroll if you have got it in your inventory . enter <revivescroll>");
                String reviving_command = scanner.next();
                
                if(reviving_command.equals("revivescroll"))
		player1.Use(map, "revivescroll");
                
                if(player1.getHitpoint() <= 0) { // it means the we didnt have revive scroll
		lostGame=true;
                }
                
            }
            
            if (player1.getEnergy() == 0) {
            lostGame = true;
            }
            
        if (lostGame == true){
        game_is_on=false;
        System.out.println("YOU LOST ! ");
        }
        }
    }

    public void Move(String command_order, Map map, Player player1) {

        int dx = 0, dy = 0;

        if (command_order.equals("up")) {
            dx = -1;
        }
        if (command_order.equals("down")) {
            dx = +1;
        }
        if (command_order.equals("left")) {
            dy = -1;
        }
        if (command_order.equals("right")) {
            dy = +1;
        }

        int new_x = player1.getX() + dx;
        int new_y = player1.getY() + dy;

        if (new_x < 0 || new_y < 0 || new_x > 24 || new_y > 24) {
            System.out.println("Out of map !");
            return;
        }
        // age wall nabud jadide , update kone
        if(map.tiles[new_x][new_y].isWall == false ){
        player1.updateLocations(new_x,new_y);
        reducers();
        }
        //whether is wall or not , it become visited
            map.tiles[new_x][new_y].isVisited = true;
    }

      
    
        public void reducers(){
        
        Tile new_tile = map.tiles[player1.getX()][player1.getY()]; // because we also want to update the tiles array in map
        if (new_tile.isWall == false) {// if it is not a wall             
            // reducing the energy and hit point
            int new_energy=player1.getEnergy();
            if (player1.inventories.size()< 0.75* player1.inventory_capacity)
            new_energy = player1.getEnergy() - 1;
            else if (player1.inventories.size()>= 0.75* player1.inventory_capacity)
            new_energy = player1.getEnergy()-2;
            player1.updateEnergy(new_energy);

            int new_hitpoint = player1.getHitpoint();
            new_hitpoint -= 10 * new_tile.getEnemy();

            player1.updateHitpoint(new_hitpoint);

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
                        if(row==player1.getX() && column==player1.getY()){
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
        Tile new_tile = map.tiles[player1.getX()][player1.getY()]; // because we also want to update the tiles array in map
        if (new_tile.isWall == false) {// if it is not a wall 
            if (new_tile.hasChest == false) {
                System.out.println("Items in this tile :  "); 
                // print the items
                for (Item item_to_print : new_tile.items) {
                    System.out.println("" + item_to_print.name);
                }
            } 
            else if (new_tile.hasChest == true) {
                System.out.println("This tile contains chest .if you have a key, Use command <Use key/unlock> to open it ");
            }
    }
    }
    
    public void goldAndEnemyPrinter(){
        Tile new_tile = map.tiles[player1.getX()][player1.getY()]; // because we also want to update the tiles array in map
        if (new_tile.isWall == false) {// if it is not a wall 
            System.out.println("Number of enemies :  " + new_tile.getEnemy());
            //player1.updateLocations(new_x, new_y);
            
            System.out.println("Amount of gold :  " + new_tile.getGold());
            int added_score=new_tile.getGold();
            player1.updateScore(added_score);
            new_tile.updateGold(0); // make the gold in that tile 0 and add it to score
    }
    }

}






