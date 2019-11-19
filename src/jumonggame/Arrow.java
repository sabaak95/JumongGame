    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumonggame;
public abstract class Arrow extends Item {

    public abstract void Use(Map map, Tile current_tile, String name, Hero player1);

    public Arrow(String name) {
        super(name);
    }

    protected void kill(Map map, Tile current_tile,int max_kill) {
        int new_num_of_enemies;
        int killed=0;
        while (current_tile.getEnemy() > 0 && killed<max_kill) {
            new_num_of_enemies = current_tile.getEnemy() - 1; // with the SmallArrow,one enemy can be died
            current_tile.updateEnemies(new_num_of_enemies);
            killed++;
        }
    }
 

    protected void openwalls(Map map, Tile current_tile, Hero player1){
        // check all the surronding tiles. if wall , destroy it
        for(int i=-1;i<2;i++){
            for(int j=-1;j<2;j++){
                map.tiles[player1.getX()+i][player1.getY()+j].isWall=false;
            }
        }
    }

 }

