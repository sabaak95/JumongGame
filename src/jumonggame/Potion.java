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
public abstract class Potion extends Item {
    public abstract void Use(Map map, Tile current_tile, String name, Player player1);

    public Potion(String name) {
        super(name);
    }

    protected void add(int hit,int energy,Player player1) {
        int new_hitpoint = player1.getHitpoint() + hit; // with the SmallPotion , hitpoint is added 20 points
        int new_energy= player1.getEnergy()+energy;
        player1.updateEnergy(new_energy);
        player1.updateHitpoint(new_hitpoint);
    }

}
