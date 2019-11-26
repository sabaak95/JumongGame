/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumonggame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 *
 * @author felan
 */
public class Graphic extends JFrame implements KeyListener {

    public JPanel map_panel, left_panel, main_panel, status_panel, command_panel, tile_info_panel;
    public JButton unlock;
    public static JLabel[][] map_icons,tile_info_label; // map with the grid layout
    public BorderLayout main_panel_border, left_panel_border;
    public static Map map;
    public static Hero hero;
    public static JProgressBar hitpoint_progress_bar;
    public static JProgressBar energy_progress_bar;
    public static Dimension dimension, main_panel_dimension, left_panel_dimension, status_panel_dimension, command_panel_dimension, tile_info_panel_dimension, hitpoint_panel_dimension;
    public static ImageIcon hero_icon, wall, empty;
    private JTextField jt;

    public Graphic(final Map map1, final Hero hero1) {
        //getting the main map and main hero and setting them
        this.map = map1;
        this.hero = hero1;

        //making the panels
        main_panel = new JPanel(); // we make the main panel of the game
        map_panel = new JPanel(); // we make the map panel to put on the right part of this main panel
        left_panel = new JPanel(); // we make the left panel to put on the left part of this main panel and put the command panel and tile info and progress on it
        status_panel = new JPanel();//put the energy and hitpoint progress bar on it
        command_panel = new JPanel(); // we make this to put on the left panel
        tile_info_panel = new JPanel(){ // we make this to put the left panel
        
        
        protected void paintComponent(Graphics g){
        super.paintComponents(g);
        g.clearRect(0, 0,200,300);
        String info_graphics=map.tiles[hero.getX()][hero.getY()].graphicShowInfo().replace("\t", "\n");
        int y =0;
        for(String line:info_graphics.split("\n")){
            g.drawString(line, 10, y);
            y+=20;
        }
        
        }
        
        };
        
        
        
        
        //we make the progress bars
        energy_progress_bar = new JProgressBar(); // make this to put on the status panel
        hitpoint_progress_bar = new JProgressBar(); // make this to put on the status panel

        //setting the frame 
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dimension); // to the screen

        //Panels dimensions
        main_panel_dimension = new Dimension(200, 200);
        main_panel.setPreferredSize(main_panel_dimension);

    //Dimension map_panel_dimension=new Dimension();
        //Dimension left_panel_dimension=new Dimension(200,40);
        status_panel_dimension = new Dimension(200, 80);
        status_panel.setPreferredSize(status_panel_dimension);

        command_panel_dimension = new Dimension(200, 200);
        command_panel.setPreferredSize(command_panel_dimension);

        tile_info_panel_dimension = new Dimension(200, 300);
        tile_info_panel.setPreferredSize(tile_info_panel_dimension);
        

   // hitpoint_panel_dimension=new Dimension(200,40);
        // hitpoint_progress_bar.setPreferredSize(hitpoint_panel_dimension);
        //Borders
        main_panel_border = new BorderLayout(0, 0);
        this.setLayout(main_panel_border); // add this main border to the frame

        left_panel_border = new BorderLayout(0, 0);
        map_panel.setFocusable(true);
        jt = new JTextField();
        getContentPane().add(jt);

        jt.addKeyListener(this);
        
        map_panel.requestFocus();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                tile_info_panel.repaint();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Graphic.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
             
            }
        }).start();

    //Panels to frame , other parts on panel
        this.add(main_panel);
        add(main_panel, BorderLayout.CENTER);
        add(left_panel, BorderLayout.WEST);
        add(map_panel, BorderLayout.EAST);
        GridLayout g = new GridLayout(25, 25, 0, 0);

        left_panel.setLayout(left_panel_border);
        left_panel.add(status_panel, BorderLayout.NORTH);
        left_panel.add(command_panel, BorderLayout.CENTER);
        left_panel.add(tile_info_panel, BorderLayout.SOUTH);
        map_panel.setLayout(g);

        status_panel.add(hitpoint_progress_bar);
        status_panel.add(energy_progress_bar);
        

        main_panel.setBackground(Color.orange);
        //hit.setBackground(Color.MAGENTA);
        command_panel.setBackground(Color.CYAN);
        tile_info_panel.setBackground(Color.GREEN);
        hitpoint_progress_bar.setValue(100);
        hitpoint_progress_bar.setStringPainted(true);
        energy_progress_bar.setValue(100);
        energy_progress_bar.setStringPainted(true);

        ImageIcon phand = new ImageIcon("pick.png");
        JButton pick = new JButton(phand);
        pick.setToolTipText("pick");
        command_panel.add(pick);
        //icone pick be panele command ezafe shod.
      /*  pick.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
         new Item1er(map.tiles[hero.getX()][hero.getY()].items,map,hero)
         .setVisible(true);

         }
         });
     */
        
        pick.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
 
                JOptionPane.showMessageDialog(null,new ItemPicker(map,hero),"dt",0);
                
                jt.grabFocus();
            
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
             //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
             //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
         
        
        
        ImageIcon pdrop = new ImageIcon("drop.png");
        JButton drop = new JButton(pdrop);
        drop.setToolTipText("drop");
        command_panel.add(drop);
        //icone drop be pannel command ezafe shod
             drop.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
 
            JOptionPane.showMessageDialog(null,new ItemDropper(map,hero),"dt",0);
                
                jt.grabFocus();
            
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
             //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
             //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        

        ImageIcon puse = new ImageIcon("use.jpg");
        JButton use = new JButton(puse);
        use.setToolTipText("use");
        command_panel.add(use);
        //icone use be pannel command ezafe shod
         use.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
 
            JOptionPane.showMessageDialog(null,new ItemUser(map,hero),"dt",0);
                
                jt.grabFocus();
                update();
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
             //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
             //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        

        ImageIcon pinventory = new ImageIcon("inventory.jpg");
        JButton inventory = new JButton(pinventory);
        inventory.setToolTipText("inventory");
        command_panel.add(inventory);
        inventory.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null,hero.showInventory() , "inventory",3);
                jt.grabFocus();
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
           //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        

        ImageIcon punlock = new ImageIcon("unlock.jpg");
        unlock = new JButton(punlock); //jbutton
        unlock.setToolTipText("unlock");
        command_panel.add(unlock);
            unlock.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                hero.Use(map,"key");
                jt.grabFocus();
                update();
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
           //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
 
        
        

        hero_icon = new ImageIcon("hero.jpg");
        wall = new ImageIcon("wall.jpg");
        empty = new ImageIcon("path.jpg");
        map_icons = new JLabel[25][25];

        Dimension each_tile_dimension = new Dimension((dimension.width - 200) / 25, dimension.height / 25);
        for (int f = 0; f <= 24; f++) {
            for (int l = 0; l <= 24; l++) {
                if (f == hero.getX() && l == hero.getY()) {

                    map_icons[f][l] = new JLabel(hero_icon);
                    map_icons[f][l].setPreferredSize(each_tile_dimension);
                    map_panel.add(map_icons[f][l]);

                } else {
                    if (map.tiles[f][l].isWall) {
                        map_icons[f][l] = new JLabel(wall);
                        map_icons[f][l].setPreferredSize(each_tile_dimension);

                        map_panel.add(map_icons[f][l]);
                    } else {

                        map_icons[f][l] = new JLabel(empty);

                        map_icons[f][l].setPreferredSize(each_tile_dimension);

                        map_panel.add(map_icons[f][l]);

                    }
                }

            }
        }

    }

    public void update() {

        main_panel_dimension = new Dimension((dimension.width - 200) / 25, dimension.height / 25);
        for (int f = 0; f <= 24; f++) {
            for (int l = 0; l <= 24; l++) {
                if (f == hero.getX() && l == hero.getY()) {

                    map_icons[f][l].setIcon(hero_icon);

                } else {
                    if (map.tiles[f][l].isWall) {
                        map_icons[f][l].setIcon(wall);
                    } else {

                        map_icons[f][l].setIcon(empty);

                    }
                }

            }

        }
        
                   
                Tile current_tile = map.tiles[hero.getX()][hero.getY()];
                
                if (current_tile.hasChest == true)  // it sees if it has got a chest or not
                unlock.setEnabled(true);
        
                else if(current_tile.hasChest == false)
                unlock.setEnabled(false);
                
                    
    
        
    }
        
        

    

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("ok");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (hero.hasDied==false && hero.hasWon==false) {
                    hero.Move(this, map,"up");
                    update();// not for location , cause move do that . just for graphic view
                } else {
                    if (hero.hasDied==true) {
                        
                        JOptionPane.showMessageDialog(null, "You are DEAD :(  ", "WARNING", JOptionPane.WARNING_MESSAGE);
                        System.exit(0);
                    } else {
                        if (hero.hasWon==true) {

                            JOptionPane.showMessageDialog(null, "You win!!! :) ", "WARNING", JOptionPane.WARNING_MESSAGE);
                       System.exit(0);
                        }
                    }
                }
                break;
                
            case KeyEvent.VK_DOWN:
                    if (hero.hasDied==false && hero.hasWon==false) {
                    hero.Move(this, map,"down");
                    update();// not for location , cause move do that . just for graphic view
                } else {
                    if (hero.hasDied==true) {
                        
                        JOptionPane.showMessageDialog(null, "You are DEAD :( ", "WARNING", JOptionPane.WARNING_MESSAGE);
                       System.exit(0);
                    } else {
                        if (hero.hasWon==true) {

                            JOptionPane.showMessageDialog(null, "You win", "WARNING", JOptionPane.WARNING_MESSAGE);
                       System.exit(0);
                        }
                    }
                }
                break;

            case KeyEvent.VK_RIGHT:
             if (hero.hasDied==false && hero.hasWon==false) {
                   
                    
                    hero.Move(this, map,"right");
                  
                    
                    
                    update();// not for location , cause move do that . just for graphic view
                } else {
                    if (hero.hasDied==true) {
                        
                        JOptionPane.showMessageDialog(null, "You are DEAD :( ", "WARNING", JOptionPane.WARNING_MESSAGE);
                        System.exit(0);
                    } else {
                        if (hero.hasWon==true) {

                            JOptionPane.showMessageDialog(null, "You win ", "WARNING", JOptionPane.WARNING_MESSAGE);
                        System.exit(0);
                        }
                    }
                }
                break;
             

            case KeyEvent.VK_LEFT:
                if (hero.hasDied==false && hero.hasWon==false) {
                    hero.Move(this, map,"left");
                    update();// not for location , cause move do that . just for graphic view
                } else {
                    if (hero.hasDied==true) {
                        
                        JOptionPane.showMessageDialog(null, "You are DEAD :( ", "WARNING", JOptionPane.WARNING_MESSAGE);
                        System.exit(0);
                    } else {
                        if (hero.hasWon==true) {

                        JOptionPane.showMessageDialog(null, "You win :)", "WARNING", JOptionPane.WARNING_MESSAGE);
                        System.exit(0);
                        }
                    }
                }
                break;

        }
    }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    @Override
    public void keyReleased(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    

}
