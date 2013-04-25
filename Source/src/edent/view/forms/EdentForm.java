/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.forms;

import edent.view.MainFrame;

/**
 *
 * @author Stepan Tesar
 */
public abstract class EdentForm extends javax.swing.JPanel {
    protected EdentForm previous;

    public EdentForm() {
        this(null);
    }
    
    public EdentForm(EdentForm previous){
        super();
        this.previous = previous;
        this.setBackground(MainFrame.BACKGROUND);
    }
    
    public abstract void setEditing(Object o);
    
    public abstract void setCreating();
    
    public void displayPrevious(){
        if(this.previous!=null){
            previous.setVisible(true);
        }
    }

    public void setPrevious(EdentForm previous) {
        this.previous = previous;
    }
                    
}
