/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.forms;

import edent.view.MainFrame;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Stepan Tesar
 */
public class UnloggedForm extends EdentForm {
    private static final String BACKGROUND = "images/logo_gray.png";

    /**
     * Creates new form Unlogged
     */
    public UnloggedForm() {
        super();
        this.setOpaque(true);
        initComponents();
    }
    
    @Override
    public void setEditing(Object o){
        System.out.println("FORM UnloggedForm has no editing state");
    }
    
    @Override
    public void setCreating(){
        System.out.println("FORM UnloggedForm has no creating state");
    }
    
    @Override
    public void resetForm(){}
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(MainFrame.BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        Image background = new ImageIcon(BACKGROUND).getImage();
        int x = getWidth()/2 - background.getWidth(this)/2;
        int y = (getHeight()/2 - background.getHeight(this)/2)-MainFrame.TOPPANEL_HEIGHT;
        g.drawImage(background, x, y, null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 723, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
