/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils;

import edent.model.OrganState;
import edent.view.MainFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author Stepan Tesar
 */
public class OrganStateLine extends AbstractLine {
    private EdentButton buttonIcon;
    private EdentButton deleteButton;
    private JSeparator sep1;
    private JSeparator sep2;
    private JPanel panelInfo;
    private JTextField textMark;
    private JTextField textName;
    
    private long organStateId;
    //private JFileChooser picChooser;

    /**
     * Creates new form OrganStateLine
     */
    public OrganStateLine(String name, String icon, String mark, long organStateId) {
        initComponents();
        myInit();
        this.textName.setText(name);
        this.textMark.setText(mark);
        
        //TODO set icon of button
        
        this.organStateId = organStateId;
    }
    
    private void myInit(){
        buttonIcon = new EdentButton("", new Dimension(25,25), 17, EdentButtonColor.white, BorderLayout.CENTER);
        deleteButton = new EdentButton("X", new Dimension(25,25), 17, EdentButtonColor.red, BorderLayout.CENTER);
        sep1 = new JSeparator();
        sep2 = new JSeparator();
        panelInfo = new JPanel();
        textMark = new JTextField();
        textName = new JTextField();
        
        textMark.setHorizontalAlignment(JTextField.CENTER);
        textMark.setBorder(null);
        
        panelWrapper.removeAll();
        
        textName.setBorder(null);
        textName.setMargin(new java.awt.Insets(0, 0, 0, 0));
        textName.setPreferredSize(new java.awt.Dimension(170, 25));
        panelWrapper.add(textName);

        sep1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        sep1.setPreferredSize(new java.awt.Dimension(1, 25));
        panelWrapper.add(sep1);

        buttonIcon.setBorder(null);
        buttonIcon.setMargin(null);
        buttonIcon.setPreferredSize(new java.awt.Dimension(25, 25));
        panelWrapper.add(buttonIcon);

        textMark.setMargin(new java.awt.Insets(0, 0, 0, 0));
        textMark.setPreferredSize(new java.awt.Dimension(25, 25));
        panelWrapper.add(textMark);

        sep2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        sep2.setPreferredSize(new java.awt.Dimension(15, 25));
        panelWrapper.add(sep2);
        
        deleteButton.setMargin(null);
        deleteButton.setPreferredSize(new java.awt.Dimension(25, 25));
        panelWrapper.add(deleteButton);

        panelInfo.setPreferredSize(new java.awt.Dimension(25, 25));
        panelInfo.setBackground(MainFrame.FOREGROUND);
        panelInfo.setLayout(new java.awt.BorderLayout());
        panelWrapper.add(panelInfo);
        panelWrapper.validate();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        picChooser = new javax.swing.JFileChooser();
        panelWrapper = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(390, 54));
        setPreferredSize(new java.awt.Dimension(390, 54));

        panelWrapper.setBackground(new java.awt.Color(255, 255, 255));
        panelWrapper.setMinimumSize(new java.awt.Dimension(370, 32));
        panelWrapper.setPreferredSize(new java.awt.Dimension(370, 32));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelWrapper;
    private javax.swing.JFileChooser picChooser;
    // End of variables declaration//GEN-END:variables
}
