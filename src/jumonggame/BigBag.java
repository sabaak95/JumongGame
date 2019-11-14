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
public class BigBag extends Item{

    public BigBag() {
        super("bigbag");
    }

    @Override
    public void Use(Map map, Tile current_tile, String name, Player player1) {
    int new_capacity;
    new_capacity=player1.getInventoryCapacity()+10;
    player1.updateInventoryCapacity(new_capacity);
    this.isUsed=true;
    }
}