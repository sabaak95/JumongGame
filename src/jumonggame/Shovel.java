/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jumonggame;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author felan
 */
public class Shovel extends Item {

    public Shovel() {
        super("shovel");
    }

    @Override
    public void Use(Map map, Tile current_tile, String name, Hero player1) {
        int x_to_dig_into,y_to_dig_into,new_energy;
        /*System.out.println("Enter the X of the tile you wanna dig into" ) ;
        Scanner scanner = new Scanner(System.in);
        x_to_dig_into = scanner.nextInt();
        System.out.println("Enter the Y of the tile you wanna dig into" ) ;
        y_to_dig_into = scanner.nextInt();
        
        
        
        if(player1.outOfMap(x_to_dig_into, y_to_dig_into)==0 ) { // Only if it is not out of map
        // if the distance less than 6 (at last 5 ) and there is not a wall , dig a tunnel and reduce the energy 1 points
        if(map.tiles[x_to_dig_into][y_to_dig_into].isWall==false && Math.abs(player1.getX()-x_to_dig_into)<6 &&Math.abs(player1.getY()-y_to_dig_into )<6){
        player1.updateLocations(x_to_dig_into,y_to_dig_into);
       
        }
        else if(map.tiles[x_to_dig_into][y_to_dig_into].isWall==true)
            System.out.println("the digging place is a wall" ) ;
        else if (Math.abs(player1.getX()-x_to_dig_into)>6 || Math.abs(player1.getY()-y_to_dig_into )>6)
            System.out.println("The destination u try to reach is so far to dig into" ) ;
        
        map.tiles[x_to_dig_into][y_to_dig_into].isVisited=true;//whether the shovling point  is a wall or not , it should become visited
        }
        this.isUsed=true;
                */
        
        ///GUI
        
        String x = JOptionPane.showInputDialog("Enter x of thing u wanna dig into");
        String y = JOptionPane.showInputDialog("Enter y of thing u wanna dig into");
        int x_to_dig=Integer.parseInt(x);
        int y_to_dig=Integer.parseInt(y);
        
        if(player1.outOfMap(x_to_dig, y_to_dig)==0 ) { // Only if it is not out of map
        // if the distance less than 6 (at last 5 ) and there is not a wall , dig a tunnel and reduce the energy 1 points
        if(map.tiles[x_to_dig][y_to_dig].isWall==false && Math.abs(player1.getX()-x_to_dig)<6 &&Math.abs(player1.getY()-y_to_dig )<6){
        player1.updateLocations(x_to_dig,y_to_dig);
       
        }
        else if(map.tiles[x_to_dig][y_to_dig].isWall==true)
            JOptionPane.showMessageDialog(null,"the digging place is a wall" ,"Message",JOptionPane.PLAIN_MESSAGE) ;
        else if (Math.abs(player1.getX()-x_to_dig)>6 || Math.abs(player1.getY()-y_to_dig )>6)
            JOptionPane.showMessageDialog(null,"The destination u try to reach is so far to dig into\" " ,"Message",JOptionPane.PLAIN_MESSAGE)  ;
                
        map.tiles[x_to_dig][y_to_dig].isVisited=true;//whether the shovling point  is a wall or not , it should become visited
        }
        this.isUsed=true;
        
    }
}
