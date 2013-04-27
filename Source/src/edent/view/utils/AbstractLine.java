/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Stepan Tesar
 */
public abstract class AbstractLine extends JPanel{
    protected JButton[] editButtons;
    
    public void showButtons(boolean show){
        for(JButton b:editButtons){
            b.setEnabled(show);
            b.setVisible(show);
        }
    }
    
}
