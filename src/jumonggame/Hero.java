/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumonggame;

import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import static jumonggame.Graphic.hero;
import static jumonggame.Graphic.map;

public class Hero {

    public int energy ;
    public int hitPoints ;
    public int gainedGold ;
    public int hero_location_x , hero_location_y ;
    public ArrayList<Item> inventories = new ArrayList<Item>();
    public int inventory_capacity=50;
    public String name;
    public boolean hasDied;
    public boolean hasWon;
   // public Graphic g;
    
    public Hero(int first_x,int first_y){
    energy = 100;
    hitPoints = 100;
    gainedGold = 0;
    hero_location_x = first_x;
    hero_location_y = first_y;
    ArrayList<Item> inventories = new ArrayList<Item>();
    inventory_capacity=50;
    hasDied=false;
    hasWon=false;
 
      for(int n=0;n<10;n++){
        SmallArrow SA=new SmallArrow();
        this.inventories.add(SA);
        
        }
      Hawk hw=new Hawk();
      this.inventories.add(hw);
      
      Shovel sh=new Shovel();
      this.inventories.add(sh);
      
      StoneBreaker sb=new StoneBreaker();
      this.inventories.add(sb);
      
      BigPotion bp=new BigPotion();
      this.inventories.add(bp);
      
      Key k=new Key();
      this.inventories.add(k);
      
      ReviveScroll rs=new ReviveScroll();
      this.inventories.add(rs);
      
      BigArrow ba= new BigArrow();
      this.inventories.add(ba);
      
      FireArrow fa=new FireArrow();
      this.inventories.add(fa);
      
    }
      
      
    
    public int getX() {
        return hero_location_x;
    }

    public int getY() {
        return hero_location_y;
    }

    public void updateLocations(int new_x, int new_y) {
        hero_location_x = new_x;
        hero_location_y = new_y;
    }

    public int getEnergy() {
        return energy;
    }

    public void updateEnergy(int new_energy) {
        energy = new_energy;
        Graphic.energy_progress_bar.setValue(this.getEnergy());
    }

    public int getGold() {
        return gainedGold;
    }

    public int getHitpoint() {
        return hitPoints;
    }

    public void updateHitpoint(int new_hitpoint) {
        hitPoints = new_hitpoint;
           Graphic.hitpoint_progress_bar.setValue(this.getHitpoint());
    }
    
    public int getGainedGold() {
        return gainedGold;
    }

    public void updateScore(int added_score) {
    gainedGold+=added_score ;
 
    }

    public ArrayList getInventory() {
        return inventories;
    }
    
   
    public int getInventoryCapacity() {
        return energy;
    }

    public void updateInventoryCapacity(int new_capacity) {
        inventory_capacity= new_capacity;
    }

    
        public void Move(Graphic g,Map map,String command_order) {
        

        int dx = 0, dy = 0;



        if (command_order.equals("up")) {
            dx = -1;
          }
        if (command_order.equals("down")) {
            dx = +1;
        }
        if (command_order.equals("left")) {
            dy = -1;
        }
        if (command_order.equals("right")) {
            dy = +1;
        }
        int new_x = this.getX() + dx;
        int new_y = this.getY() + dy;
        if (outOfMap(new_x,new_y)==1  || map.tiles[new_x][new_y].isWall==true)
            return;


        // age wall nabud jadide , update kone
        if(map.tiles[new_x][new_y].isWall == false ){
        reducers(map);//ghable vurud be tile new
        gainedGoldUpdator(map);
        if(this.getHitpoint()<=0){ // bad az khuruj az har tile
        checkHitPoint();
        }
        if(this.getX()== map.dst_x && this.getY()== map.dst_y){
        this.hasWon=true;
        }
        
        this.updateLocations(new_x,new_y);
        if (this.getEnergy() == 0) {
        hasDied = true;
        }
        g.update();
 
        }
        //whether is wall or not , it become visited
            map.tiles[new_x][new_y].isVisited = true;
    }
        
        public int outOfMap(int new_x,int new_y){
        if (new_x < 0 || new_y < 0 || new_x > 24 || new_y > 24) {
        Audio.init();
        Audio.OUTOFMAP.play();
        System.out.println("Out of map !");
        JOptionPane.showMessageDialog(null,"Out of map !","Message",JOptionPane.PLAIN_MESSAGE) ;

        
        
                
                
        return 1;
        }
        else
            return 0;
        }
        
        public void reducers(Map map){
           
        
        Tile current_tile = map.tiles[this.getX()][this.getY()]; // because we also want to update the tiles array in map
        if (current_tile.isWall == false) {// if it is not a wall             
            // reducing the energy and hit point
            int new_energy=this.getEnergy();
            if (this.inventories.size()< 0.75* this.inventory_capacity)
            new_energy = this.getEnergy() - 1;
            else if (this.inventories.size()>= 0.75* this.inventory_capacity)
            new_energy = this.getEnergy()-2;
            this.updateEnergy(new_energy);
            Graphic.energy_progress_bar.setValue(this.getEnergy());
            
            int new_hitpoint = this.getHitpoint();
           // new_hitpoint -= 10 * current_tile.getEnemy();

            this.updateHitpoint(new_hitpoint);
            Graphic.hitpoint_progress_bar.setValue(this.getHitpoint());


        }
    }
        public void gainedGoldUpdator(Map map){
        Tile current_tile = map.tiles[this.getX()][this.getY()]; // because we also want to update the tiles array in map
        if (current_tile.isWall == false) {// if it is not a wall             
            int added_score=current_tile.getGold();
            this.updateScore(added_score);
            current_tile.updateGold(0);
        }
        }
    
    
    public void Drop(Tile current_tile, String name) {
        boolean found_for_drop=false;
        for (Item item_for_dropping : this.inventories) {
            if (item_for_dropping.name.equalsIgnoreCase(name)) {
                this.inventories.remove(item_for_dropping); // remove that item from the inventory
                current_tile.items.add(item_for_dropping);// add the item to the tiles items
                found_for_drop=true;
                break;
            }
        }
        if(!found_for_drop){
     //   System.out.println("Item :"+name+" was not found in inventory!"); 
        JOptionPane.showMessageDialog(null,"item" +name+ "was not found in inventory","Message",JOptionPane.PLAIN_MESSAGE) ;
        
        }
    }
    

    public void Use(Map map,String name){ // when called 2 vurudi , miad inja , va sevomish null o edame az pain
        Use(map,name,null);
    }

public void Use(Map map,String name,String EName) { 
        Tile current_tile = map.tiles[getX()][getY()]; 
        boolean found=false; 
        for (Item item_for_using : this.inventories) { 
            if (item_for_using.name.equalsIgnoreCase(name)) { // that item is available in inventory 
                if(EName==null) //halate addi
                    item_for_using.Use(map,current_tile ,name,this); //item.use method 
                
                    if(item_for_using.isUsed ==true){
                    this.inventories.remove(item_for_using); //if it is used,remove it from directory 
                    JOptionPane.showMessageDialog(null,"item:"+name+"is used !" ,"Message",JOptionPane.PLAIN_MESSAGE) ;
                  //  System.out.println("Item :"+name+" is used !");
                    }
                    else if (item_for_using.isUsed == false){
                     JOptionPane.showMessageDialog(null,"item:"+name+"is not useful here !" ,"Message",JOptionPane.PLAIN_MESSAGE) ;
                //    System.out.println("Item :"+name+" is not useful here !");   
                    }
                found=true; 
                break; 
            } 
        } 
        if(!found) {// too inventory nabashe , dar har soorat miad ino print mikoone .  age bood ham print migereft chon az for miumad birun
        //    System.out.println("Item :"+name+" was not found in inventory!"); 
            JOptionPane.showMessageDialog(null,"item" +name+ "was not found in inventory","Message",JOptionPane.PLAIN_MESSAGE) ;
    }
}

    public void Pick(Tile current_tile, String name) {
        boolean hasPick=false;
        for (Item item_for_picking : current_tile.items) {
            if (item_for_picking.name.equalsIgnoreCase(name) && this.inventories.size()+1 <=this.inventory_capacity) { // after adding the Item , it should not go far that the inventory_size
                this.inventories.add(item_for_picking);// add the item to the players inventory
                current_tile.getItems().remove(item_for_picking); // remove that item from the tile
				hasPick=true;
                break;
            }
        }
		if(!hasPick){ // age in bool nabashe , age peida nakard ke mizane peida nakarde k mizane nakarde vali age peida ham kone az for miad birun va ino baz chap mikone :|
			//System.out.println("Item :"+name+" was not found in the tile!"); 
                        JOptionPane.showMessageDialog(null,"Item :"+name+" was not found in the tile!" ,"Message",JOptionPane.PLAIN_MESSAGE) ;
    }
    }
    
    
        public String showInventory(){
        //we make the counters 0 at first
        int smallArrow=0,bigArrow=0,fireArrow=0,shovel=0,hawk=0,key=0,bigBag=0,stoneBreaker=0;
        int energyPotion=0,reviveScroll=0,smallHealthPotion=0, bigHealthPotion =0;
        for (Item item :inventories) {
            
            switch(item.getClass().getSimpleName().toLowerCase()){
                case "smallarrow": // consider the difference between here and tile 
                    smallArrow++;
                    break;
                case "bigarrow":
                    bigArrow++;
                    break;
                case "firearrow":
                    fireArrow++;
                    break;
                case "bigbag":
                    bigBag++;
                    break;
                case "shovel":
                    shovel++;
                    break;
                case "hawk":
                    hawk++;
                    break;
                case "stonebreaker":
                    stoneBreaker++;
                    break;
                case "energypotion":
                    energyPotion++;
                    break;
                case "revivescroll":
                    reviveScroll++;
                    break;
                case "key":
                    key++;
                    break;
                case "smallpotion":
                    smallHealthPotion++;
                    break;
                case "bigpotion":
                      bigHealthPotion++;
                    break;
             
                    
                }

             }
            return  ("Inventory:    \nSmallArrow:"+smallArrow
                    +"\nBigArrow:"+bigArrow
                    +"\nFireArrow:"+fireArrow
                    +"\nKey:"+key
                    +"\nEnergyPotion:"+energyPotion
                    +"\nReviveScroll:"+reviveScroll
                    +"\nSmallHealthPotion:"+smallHealthPotion
                    +"\nBigHealthPotion:"+bigHealthPotion
                    +"\nShovel:"+shovel
                    +"\nHawk:"+hawk
                    +"\nStoneBreaker:"+stoneBreaker
                    +"\ngainedGold :" +gainedGold);
    }
    private void checkHitPoint() {
        boolean hasRevive = false;
        ReviveScroll rs = new ReviveScroll();
        for (Item i : this.inventories) {
            if (i.name.equalsIgnoreCase("Revivescroll")) {
                hasRevive = true;
                JOptionPane.showMessageDialog(null,"We have revive.Use it if u want","Message",JOptionPane.PLAIN_MESSAGE);
                break;
                
            }
        }
    
    if (!hasRevive){
    hasDied=true;
}
    }
   
}




