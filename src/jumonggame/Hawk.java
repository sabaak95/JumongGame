/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumonggame;

import javax.swing.JOptionPane;
import static jumonggame.Graphic.hero;
import static jumonggame.Graphic.map;

/**
 *
 * @author felan
 */
public class Hawk extends Item {

    public Hawk() {
        super("hawk");
    }

    @Override
    public void Use(Map map, Tile current_tile, String name, Hero player1) {
        
      
        String surronding_tiles_info_graphics =
                ("\n up:"+map.tiles[player1.getX()-1][player1.getY()].graphicShowInfo()
                //in 4 ta harkudum tabdil be ye tabe string deh ke toosh check kone 
                //age divar nabud va out of map nabud , ini ke injas . age out bud print out , age wall wall
                +"\n down" +map.tiles[player1.getX()+1][player1.getY()].graphicShowInfo()
                +"\n right : " +map.tiles[player1.getX()][player1.getY()+1].graphicShowInfo()
                +"\n left : "+ map.tiles[player1.getX()][player1.getY()-1].graphicShowInfo());
        
       JOptionPane.showMessageDialog(null,surronding_tiles_info_graphics,"message",JOptionPane.PLAIN_MESSAGE);

                
        
        this.isUsed=true;
}
        
       
        
}