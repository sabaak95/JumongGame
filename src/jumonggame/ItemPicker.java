/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jumonggame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author felan
 */
public class ItemPicker extends JPanel {

    final JRadioButton revive, sArrow, bArrow, fArrow, bBag, bHealthP, sHealthP, hawk, energyP, key, shovel, stoneBreaker;
    private String selectedItem;
    private JButton ok;
    private Map map;
    private Hero hero;

    public ItemPicker( final Map map1, final Hero hero1) {
//        super("pick.....");
        this.map = map1;
        this.hero = hero1;
        JButton ok ;

        //JDialog this = new JDialog(parent, "pick......");
        this.setSize(200, 300);
        this.setLocation(500, 200);
        this.setLayout(new FlowLayout());
        final ButtonGroup b = new ButtonGroup();


        bBag = new JRadioButton("bigBag");

        bHealthP = new JRadioButton("bigPotion");
        sHealthP = new JRadioButton("smallPotion");
        hawk = new JRadioButton("hawk");
        shovel = new JRadioButton("shovel");
        stoneBreaker = new JRadioButton("stoneBreaker");
        energyP = new JRadioButton("energyPotion");
        key = new JRadioButton("key");
        sArrow = new JRadioButton("smallArrow");
        bArrow = new JRadioButton("bigArrow");
        fArrow = new JRadioButton("fireArrow");
        revive = new JRadioButton("reviveScroll");
        // pick.setFocusable(false);

        b.add(bBag);
        b.add(bHealthP);
        b.add(sHealthP);
        b.add(hawk);
        b.add(shovel);
        b.add(stoneBreaker);
        b.add(energyP);
        b.add(key);
        b.add(sArrow);
        b.add(bArrow);
        b.add(fArrow);
        b.add(revive);

        this.add(bBag);
        this.add(hawk);
        this.add(shovel);
        this.add(stoneBreaker);
        this.add(bHealthP);
        this.add(sHealthP);
        this.add(key);
        this.add(energyP);
        this.add(sArrow);
        this.add(bArrow);
        this.add(fArrow);
        this.add(revive);
        
        ok=new JButton("pick");
        this.add(ok);
        ok.addActionListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String itemNameToPick = getSelectedButtonText(b);
                Tile current_tile = map.tiles[hero1.getX()][hero1.getY()];
                hero1.Pick(current_tile,itemNameToPick);
                b.clearSelection();
            //    ItemPicker.this.setVisible(false);
//                ItemPicker.this.dispose();
            }
        });

    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}