/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jumonggame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author felan
 */
public class ItemUser extends JPanel {
    
    final JRadioButton revive,  bBag, bHealthP, sHealthP, hawk, energyP, shovel, stoneBreaker;
    private String selectedItem;
    private JButton ok;
    private Map map;
    private Hero hero;

    public ItemUser( final Map map1, final Hero hero1) {
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
        revive = new JRadioButton("reviveScroll");
        // pick.setFocusable(false);

        b.add(bBag);
        b.add(bHealthP);
        b.add(sHealthP);
        b.add(hawk);
        b.add(shovel);
        b.add(stoneBreaker);
        b.add(energyP);
        b.add(revive);

        this.add(bBag);
        this.add(hawk);
        this.add(shovel);
        this.add(stoneBreaker);
        this.add(bHealthP);
        this.add(sHealthP);
        this.add(energyP);
        this.add(revive);
        
        ok=new JButton("use");
        this.add(ok);
        ok.addActionListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String itemNameToUse = getSelectedButtonText(b);
                hero1.Use(map,itemNameToUse);
             //   b.clearSelection();
               // ItemUser.this.setVisible(false);
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
