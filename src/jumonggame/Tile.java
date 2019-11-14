/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumonggame;

import java.util.*;

public class Tile {

    public boolean isWall = false;
    public boolean isVisited = false;
    public int amountOfGold = 0;
    public int numOfEnemies = 0;
    public boolean isLocked=false;
    public boolean hasChest=false;
    public ArrayList<Item> items = new ArrayList<Item>();
    public ArrayList<Item> chestItems = new ArrayList<Item>();


    public int getEnemy() {
        return numOfEnemies;
    }
    public ArrayList getItems(){
    return items;
    }
    public int getGold(){
    return amountOfGold;
    }
    public void updateEnemies(int new_num_of_enemies){
    numOfEnemies=new_num_of_enemies;
    }
    
    public void updateGold(int new_amount_of_gold){
    amountOfGold=new_amount_of_gold;
    }
    

}
