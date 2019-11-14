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
public class BigArrow extends Arrow {

    public BigArrow() {
        super("bigarrow"); // tells the name to Arrow
    }

    public void Use(Map map,Tile current_tile,String name,Player player1) {
        if(current_tile.getEnemy()>0){ // if it contains enemy , we use it and kill . if not , we dont use it.
        // we want to search through the enemies in that tile , if found , kill em !
        // we need searching cause if not , we cannot delet the OBJECT
        kill(map,current_tile,10);
        this.isUsed=true;
    }
    }
}

