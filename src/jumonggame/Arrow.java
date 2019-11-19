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

    
 

    protected void openwalls(Map map, Tile current_tile, Hero player1){
        // check all the surronding tiles. if wall , destroy it
        for(int i=-1;i<2;i++){
            for(int j=-1;j<2;j++){
                if(player1.outOfMap(player1.getX()+i,player1.getY()+j)==0 ) { // if it was available
                map.tiles[player1.getX()+i][player1.getY()+j].isWall=false;
                }
            }   
        }
    }

 }

