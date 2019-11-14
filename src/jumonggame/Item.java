/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumonggame;

public abstract class Item {

    public String name;
    boolean isUsed=false;
    

    public Item(String name) {
        this.name = name;
    }
    public abstract void Use(Map map, Tile current_tile, String name,Player player1);

}
