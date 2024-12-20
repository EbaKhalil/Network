/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class CustomListCellRenderer extends DefaultListCellRenderer {
    
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        String text = (String) value; // get the text of the item
        String []part=text.split(" : ",2);
        // check the text and change the foreground color accordingly
        if (part[0].equals("ME")) {
            c.setForeground(Color.RED);
        }
        else if (part[0].equals("rem")) {
            c.setForeground(Color.BLUE);
        }
        
         else if (part[0].equals("Ali")) {
            c.setForeground(Color.GREEN);
        }
        else if (part[0].equals("Saly")) {
            c.setForeground(Color.orange);
        }
         else if (part[0].equals("Aws")) {
            c.setForeground(Color.darkGray);
        }
         else if (part[0].equals("Adam")) {
            c.setForeground(Color.PINK);
        }
        
        return c;
    }
    
}
