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
public class SmallArrow extends Arrow {

    public SmallArrow() {
        super("smallarrow");
    }

    public void Use(Map map, Tile current_tile, String name, Player player1) {
        if(current_tile.getEnemy()>0){
        kill(map, current_tile, 1);
        this.isUsed = true;
    }
}
}