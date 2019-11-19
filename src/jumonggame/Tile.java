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
    public boolean isLocked=false;
    public boolean hasChest=false;
    public ArrayList<Item> items = new ArrayList<Item>();
    public ArrayList<Item> chestItems = new ArrayList<Item>();


    
    public ArrayList getItems(){
    return items;
    }
    public int getGold(){
    return amountOfGold;
    }
   
    
    public void updateGold(int new_amount_of_gold){
    amountOfGold=new_amount_of_gold;
    }
       public String graphicShowInfo(){
                //we make the counters 0 at first
        int smallArrow=0,bigArrow=0,fireArrow=0,shovel=0,hawk=0,key=0,bigBag=0,stoneBreaker=0;
        int energyPotion=0,reviveScroll=0,smallHealthPotion=0, bigHealthPotion =0;
        for (Item item : items) { // items in this tile
            
            switch(item.getClass().getSimpleName().toLowerCase()){
                case "smallarrow":
                    smallArrow++;
                    break;
                case "bigarrow":
                    bigArrow++;
                    break;
                case "firearrow":
                    fireArrow++;
                    break;
                case "bigbag":
                    bigBag++;
                    break;
                case "shovel":
                    shovel++;
                    break;
                case "hawk":
                    hawk++;
                    break;
                case "stonebreaker":
                    stoneBreaker++;
                    break;
                case "energypotion":
                    energyPotion++;
                    break;
                case "revivescroll":
                    reviveScroll++;
                    break;
                case "key":
                    key++;
                    break;
                case "smallpotion":
                    smallHealthPotion++;
                    break;
                case "bigpotion":
                      bigHealthPotion++;
                    break;
                }

             }
        
             String names="";
             
        
            return  ("Tile information:    \tSmallArrow:"+smallArrow
                    +"\tBigArrow:"+bigArrow
                    +"\tFireArrow:"+fireArrow
                    +"\tKey:"+key
                    +"\tEnergyPotion:"+energyPotion
                    +"\tReviveScroll:"+reviveScroll
                    +"\tSmallHealthPotion:"+smallHealthPotion
                    +"\tBigHealthPotion:"+bigHealthPotion
                    +"\tShovel:"+shovel
                    +"\tHawk:"+hawk
                    +"\tStoneBreaker:"+stoneBreaker
                    +"\tBigBag:"+bigBag
                    
                    +"\nGold In this Tile : "+getGold()
                    );
    }

}
