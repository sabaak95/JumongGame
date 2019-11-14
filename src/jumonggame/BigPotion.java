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
public class BigPotion extends Potion {

    public BigPotion() {
        super("bigpotion");
    }

    public void Use(Map map, Tile current_tile, String name, Player player1) {
        add(50,0,player1);
        this.isUsed=true;
    }

}
