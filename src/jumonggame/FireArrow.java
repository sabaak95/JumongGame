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
public class FireArrow extends Arrow {

    public FireArrow() {
        super("firearrow");
    }

    public void Use(Map map, Tile current_tile, String name, Hero player1) {
        kill(map,current_tile,1);
        openwalls(map,current_tile,player1);
        this.isUsed=true; // at least opening walls is needed anywheres
    }
}

