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
public class StoneBreaker extends Item {

    public StoneBreaker() {
        super("stonebreaker");
    }

    @Override
    public void Use(Map map, Tile current_tile, String name, Player player1) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                map.tiles[player1.getX() + i][player1.getY() + j].isWall = false;
            }
        }
    this.isUsed=true;
    }
}
