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
public class Key extends Item {

    public Key() {
        super("key");
    }

    @Override
    public void Use(Map map, Tile current_tile, String name, Player player1) {
    // before here,in the method Use from class player , it has been checked that if player has it in his inventory , then we can use .
    if (current_tile.hasChest==true){
    current_tile.hasChest=false; 
    this.isUsed=true; // maybe it doesnt have a locked chest . so key should not be harum :|
    }
    }
}