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
public class SmallPotion extends Potion {

    public SmallPotion() {
        super("smallpotion");
    }

    public void Use(Map map, Tile current_tile, String name, Hero player1) {
        add(20,0,player1);
        this.isUsed=true;
    }

}

