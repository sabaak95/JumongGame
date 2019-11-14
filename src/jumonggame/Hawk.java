/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumonggame;

/**
 *
 * @author felan
 */
public class Hawk extends Item {

    public Hawk() {
        super("hawk");
    }

    @Override
    public void Use(Map map, Tile current_tile, String name, Player player1) {
        //upper tile
        Tile upper_tile = map.tiles[player1.getX()-1][player1.getY()]; // because we also want to update the tiles array in map
        if (upper_tile.isWall == false) {// if it is not a wall 
            if (upper_tile.hasChest == false) {
                System.out.println("Items in the upper tile :  "); 
                // print the items
                for (Item item_to_print : upper_tile.items) {
                    System.out.println("" + item_to_print.name);
                }
            }
            else if (upper_tile.hasChest == true)
                System.out.println("A chest is in the upper tile  ");
                
            System.out.println("Number of enemies in the upper tile  :  " + upper_tile.getEnemy());
        }
        else if (upper_tile.isWall == true)
            System.out.println("The upper tile is a wall ");

        //lower tile
        Tile lower_tile = map.tiles[player1.getX()+1][player1.getY()]; // because we also want to update the tiles array in map
        if (lower_tile.isWall == false) {// if it is not a wall 
            if (lower_tile.hasChest == false) {
                System.out.println("Items in the lower tile :  "); 
                // print the items
                for (Item item_to_print : lower_tile.items) {
                    System.out.println("" + item_to_print.name);
                }
            }
            else if (lower_tile.hasChest == true)
                System.out.println("A chest is in the lower tile  ");
                
            System.out.println("Number of enemies in the lower tile  :  " + lower_tile.getEnemy());
        }
                else if (lower_tile.isWall == true)
            System.out.println("The lower tile is a wall ");
        //right tile
         Tile right_tile = map.tiles[player1.getX()][player1.getY()+1]; // because we also want to update the tiles array in map
        if (right_tile.isWall == false) {// if it is not a wall 
            if (right_tile.hasChest == false) {
                System.out.println("Items in the right tile :  "); 
                // print the items
                for (Item item_to_print : right_tile.items) {
                    System.out.println("" + item_to_print.name);
                }
            }
            else if (right_tile.hasChest == true)
                System.out.println("A chest is in the right tile  ");
                
            System.out.println("Number of enemies in the right tile  :  " + right_tile.getEnemy());
        }
                else if (right_tile.isWall == true)
            System.out.println("The right tile is a wall ");
        //left tile
        Tile left_tile = map.tiles[player1.getX()][player1.getY()-1]; // because we also want to update the tiles array in map
        if (left_tile.isWall == false) {// if it is not a wall 
            if (left_tile.hasChest == false) {
                System.out.println("Items in the left tile :  "); 
                // print the items
                for (Item item_to_print : left_tile.items) {
                    System.out.println("" + item_to_print.name);
                }
            }
            else if (left_tile.hasChest == true)
                System.out.println("A chest is in the left tile  ");
                
            System.out.println("Number of enemies in the left tile  :  " + left_tile.getEnemy());
        }
                else if (left_tile.isWall == true)
            System.out.println("The left tile is a wall ");
        
        this.isUsed=true;
}
}