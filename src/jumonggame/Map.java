/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jumonggame;

import java.util.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Map {

    public Tile[][] tiles;
    int dst_x, dst_y;

    public Map() {
        tiles = new Tile[25][25];// for getting the memory 
        int i, j;

        for (i = 0; i < 25; i++) {
            for (j = 0; j < 25; j++) {
                tiles[i][j] = new Tile();
            }
        }
        genMaze();
        shareGold();
        placeEnemies();
        placeItems();
    }

    private void genMaze() {

        ArrayList<Integer> order = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++)
            order.add(i);

        int[] dir_x={-1,+1,0,0};
        int[] dir_y={0,0,-1,+1};


        Stack<Integer> cellStack
                = new Stack<Integer>();

        int currentX = 0, currentY = 0;

        //make all tiles wall
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                tiles[i][j].isWall = true;
            }
        }

        //shape a maze !
        while (true) {
            //find all neighbors of CurrentCell with all walls intact
            Collections.shuffle(order); // to have a different delta in each wallDestroying.

            boolean found = false;
            for (int i=0;i<4;i++) {  // for each member of direction there's a delta that every time is different

                int curr_order=order.get(i);
                int curr_dir_x=dir_x[curr_order];
                int curr_dir_y=dir_y[curr_order];

                int x = currentX + 2 * curr_dir_x;
                int y = currentY + 2 * curr_dir_y;
                if (x < 0 || y < 0 || x >= 25 || y >= 25) {
                    continue;//outside!
                }
                if (tiles[x][y].isWall == true) {
                    //knock down the wall between it and CurrentCell
                    tiles[x][y].isWall = false;
                    tiles[x - curr_dir_x][y - curr_dir_y].isWall = false;
                    //push CurrentCell location on the CellStack . pushes the last tile before moving to the cellstack
                    cellStack.push(currentX);
                    cellStack.push(currentY);
                    //make the new cell CurrentCell
                    currentX = x;
                    currentY = y;
                    found = true;
                    break;//found and selected , so stop searching
                }
            }
            if (!found) {
                //if all the neighbours are wall 
                //pop the most recent cell entry off the CellStack and make it CurrentCell
                if (!cellStack.isEmpty()) {
                    currentY = cellStack.pop();
                    currentX = cellStack.pop();
                } else {
                    break;
                }
            }
        }

        //random dst . we dont wanna put the dst point near the starting tile 
        Random r = new Random();
        do {
            dst_x = 12 +r.nextInt(12);
            dst_y = r.nextInt(12);
        } while (tiles[dst_x][dst_y].isWall);
        
    }

    private void shareGold() {
        Random r = new Random();
        int distance_to_dst_x, distance_to_dst_y;
        int tiles_containing_gold = 0;

        while (tiles_containing_gold < 100) {

            int i = r.nextInt(25) ;
            int j = r.nextInt(25) ;

            distance_to_dst_x = Math.abs(i - dst_x);
            distance_to_dst_y = Math.abs(j - dst_y);

            if (tiles[i][j].amountOfGold == 0 && tiles[i][j].isWall == false) {
                tiles[i][j].amountOfGold = 26 - ((distance_to_dst_x + distance_to_dst_y)/2);
                tiles_containing_gold++;
            }

        }

    }


    private void placeEnemies() {
        Random r = new Random();
        int tiles_enemied = 0;
        while (tiles_enemied < 50) {
            int i = r.nextInt(24) + 1; // we dont wanna place enemies near the start point
            int j = r.nextInt(24) + 1;
            if (tiles[i][j].numOfEnemies == 0 && tiles[i][j].isWall == false) {
                /*other tiles which are not generated here will be containing 0 enemies 
                 because in tile's constructor it is initialized */
                tiles[i][j].numOfEnemies = 1+r.nextInt(3);
                tiles_enemied++;
            }
        }
    }


    private void placeItems() {
        Random r = new Random();
        int all_items = 80;
        int all_chests=10;
        int all_items_for_chests=30;
        int i, j;
        tiles[2][3].isWall=false;
        tiles[2][3].hasChest=true;
        tiles[2][3].isLocked=true;
   //     tiles[2][3].chestItems.add(new BigPotion());

        while (all_items > 0 ) {

            i = 1+r.nextInt(24);
            j = 1+r.nextInt(24);
            if (tiles[i][j].items.isEmpty() && !tiles[i][j].isWall) {

                int items_for_each_tile = 1+r.nextInt(3); // We want to place between 1-3 items in each tile
                all_items -= items_for_each_tile;
                
                //randomly locks some items of the tiles
                if( all_chests>0 && tiles[i][j].hasChest == false) { // not containing a chest before
                    
                    tiles[i][j].hasChest=true;
                    tiles[i][j].isLocked=true;
                    all_chests--;
                    
                    int items_for_each_chest = 1+r.nextInt(3); // We want to place between 1-3 items in each chest
                    all_items_for_chests -=items_for_each_chest;
               

                for (int c = 0; c < items_for_each_chest; c++) {

                    Item item_to_add=null;
                    switch (r.nextInt(11)) {
                        case 0:
                            item_to_add = new SmallArrow();
                            break;
                        case 1:
                            item_to_add = new FireArrow();
                            break;
                        case 2:
                            item_to_add = new BigArrow();
                            break;
                        case 3:
                            item_to_add = new StoneBreaker();
                            break;
                        case 4:
                            item_to_add = new Key();
                            break;
                        case 5:
                            item_to_add = new SmallPotion();
                            break;
                        case 6:
                            item_to_add = new BigPotion();
                            break;
                        case 7:
                            item_to_add = new EnergyPotion();
                            break;
                        case 8:
                            item_to_add = new ReviveScroll();
                            break;
                        case 9:
                            item_to_add = new Hawk();
                            break;
                        case 10:
                            item_to_add = new Shovel();
                            break;
                        case 11:
                            item_to_add = new BigBag();
                            break;
                    }
                    //add this item  to the arraylist
                    tiles[i][j].chestItems.add(item_to_add);
                }
               }
                
                for (int IC = 0; IC < items_for_each_tile; IC++) {

                    Item item_to_add=null;
                    switch (r.nextInt(11)) {
                        case 0:
                            item_to_add = new SmallArrow();
                            break;
                        case 1:
                            item_to_add = new FireArrow();
                            break;
                        case 2:
                            item_to_add = new BigArrow();
                            break;
                        case 3:
                            item_to_add = new StoneBreaker();
                            break;
                        case 4:
                            item_to_add = new Key();
                            break;
                        case 5:
                            item_to_add = new SmallPotion();
                            break;
                        case 6:
                            item_to_add = new BigPotion();
                            break;
                        case 7:
                            item_to_add = new EnergyPotion();
                            break;
                        case 8:
                            item_to_add = new ReviveScroll();
                            break;
                        case 9:
                            item_to_add = new Hawk();
                            break;
                        case 10:
                            item_to_add = new Shovel();
                            break;
                        case 11:
                            item_to_add = new BigBag();
                            break;
                    }
                    //add this item  to the arraylist
                    tiles[i][j].items.add(item_to_add);
                }
                
                
            }
        }
    }
}