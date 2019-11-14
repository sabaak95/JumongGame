/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumonggame;

import java.util.ArrayList;

public class Player {

    public int energy = 100;
    public int hitPoints = 100;
    public int gainedGold = 0;
    public int player_location_x = 0, player_location_y = 0;
    public ArrayList<Item> inventories = new ArrayList<Item>();
    public int inventory_capacity=50;

    public int getX() {
        return player_location_x;
    }

    public int getY() {
        return player_location_y;
    }

    public void updateLocations(int new_x, int new_y) {
        player_location_x = new_x;
        player_location_y = new_y;
    }

    public int getEnergy() {
        return energy;
    }

    public void updateEnergy(int new_energy) {
        energy = new_energy;
    }

    public int getGold() {
        return gainedGold;
    }

    public int getHitpoint() {
        return hitPoints;
    }

    public void updateHitpoint(int new_hitpoint) {
        hitPoints = new_hitpoint;
    }
    
    public int getGainedGold() {
        return gainedGold;
    }

    public void updateScore(int added_score) {
         gainedGold+=added_score ;
    }

    public ArrayList getInventory() {
        return inventories;
    }
   
    public int getInventoryCapacity() {
        return energy;
    }

    public void updateInventoryCapacity(int new_capacity) {
        inventory_capacity= new_capacity;
    }

    public void Drop(Tile current_tile, String name) {
        for (Item item_for_dropping : this.inventories) {
            if (item_for_dropping.name.equals(name)) {
                this.inventories.remove(item_for_dropping); // remove that item from the inventory
                current_tile.items.add(item_for_dropping);// add the item to the tiles items
                break;
            }
        }
        System.out.println("Item :"+name+" was not found in inventory!"); 
        
    }

public void Use(Map map,String name) { 
        Tile current_tile = map.tiles[getX()][getY()]; 
        boolean found=false; 
        for (Item item_for_using : this.inventories) { 
            if (item_for_using.name.equals(name)) { // that item is available in inventory 
                item_for_using.Use(map,current_tile ,name,this); //item.use method 
                    if(item_for_using.isUsed ==true){
                    this.inventories.remove(item_for_using); //if it is used,remove it from directory 
                    System.out.println("Item :"+name+" is used !");
                    }
                    else if (item_for_using.isUsed == false)
                    System.out.println("Item :"+name+" is not useful here !");    
                found=true; 
                break; 
            } 
        } 
        if(!found) 
            System.out.println("Item :"+name+" was not found in inventory!"); 
    }

    public void Pick(Tile current_tile, String name) {
        
        for (Item item_for_picking : current_tile.items) {
            if (item_for_picking.name.equals(name) && this.inventories.size()+1 <=this.inventory_capacity) { // after adding the Item , it should not go far that the inventory_size
                current_tile.getItems().remove(item_for_picking); // remove that item from the tile
                this.inventories.add(item_for_picking);// add the item to the players inventory
                break;
            }
        }
        System.out.println("Item :"+name+" was not found in the tile!"); 
    }



}
