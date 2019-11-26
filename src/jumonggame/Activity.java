/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumonggame;

import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import javax.swing.*;

/**
 *
 * @author saba
 */
public class Activity extends JFrame {

    static String PhoneNumber = "";
    JLabel Result;
    private boolean booleanToChange = false;

    public Activity(Map map, int x, int y, Hero player1) {
        PhoneNumber = "";
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(2, 1));

// Upper panel - responses
        JPanel upper = new JPanel();
        upper.setLayout(new GridLayout(3, 1));

        upper.add(new JLabel(" Click the numbers in the right order"));
        upper.add(Result = new JLabel(""));
        JButton OK;
        upper.add(OK = new JButton("OK"));
        OK.addActionListener(
                new ActionListener() // Definition through an inner class ============
        {
            public void actionPerformed(ActionEvent e) {
                if (PhoneNumber.equals("123456789")) {
                    JOptionPane.showMessageDialog(null,"Chest Unlocked","Message",JOptionPane.PLAIN_MESSAGE) ;
                }
                else {
                    try{
                        throw new WrongPattern();
                    }
                    catch(WrongPattern ex)
                    {
                        JOptionPane.showMessageDialog(null,ex.getMessage(),"Message",JOptionPane.PLAIN_MESSAGE) ;
                    }
                    PhoneNumber = "";
                }
            }
        }
        // End of inner class definition ================

        );
        map.tiles[x][y].isLocked = false;
// Lower panel - keypad
        JPanel lower = new JPanel();
        lower.setLayout(new GridLayout(3, 3));

        String[] Phone = {"7", "8", "9", "4", "5", "6",
            "1", "2", "3"};
        for (int i = 1; i < Phone.length; i++) {
            int j = (int) (Math.random() * i);
            String temp = Phone[i];
            Phone[i] = Phone[j];
            Phone[j] = temp;
        }

        for (int i = 0; i < Phone.length; i++) {
            JButton current;
            lower.add(current = new JButton(Phone[i]));
            current.addActionListener(
                    new ActionListener() // Definition through an inner class =============================
            {
                public void actionPerformed(ActionEvent e) {
                    PhoneNumber = PhoneNumber + e.getActionCommand();
                    Result.setText(PhoneNumber);
                }
            }
            // End of inner class definition =================================
            );
        }
        cp.add(upper);
        cp.add(lower);
        this.pack();

    }
}

class WrongPattern extends RuntimeException
{
    @Override
    public String getMessage()
    {
        return "Patterns do not match.";
    }
}