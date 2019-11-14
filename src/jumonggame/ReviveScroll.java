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
public class ReviveScroll extends Item{

    public ReviveScroll() {
        super("revivescroll");
    }

    @Override
    public void Use(Map map, Tile current_tile, String name, Player player1) {
        int new_hitpoint;
       
        if (player1.getHitpoint()<= 0){ // only in lethal damages
        new_hitpoint=player1.getHitpoint()+50;
        this.isUsed=true;
        }
        else
        System.out.println("You can use this item only when you are dying ");
 
        
    }
}

