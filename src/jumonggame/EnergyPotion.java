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
public class EnergyPotion extends Potion {

    public EnergyPotion() {
        super("energypotion");
    }

    public void Use(Map map, Tile current_tile, String name, Player player1) {
        add(0,10,player1);
        this.isUsed=true;
    }

}
