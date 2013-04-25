/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.forms;

import edent.view.MainFrame;
import edent.view.utils.SettingsButton;
import edent.view.utils.SettingsButtonApplication;
import edent.view.utils.SettingsButtonPatients;
import edent.view.utils.SettingsButtonUsers;
import edent.view.utils.SettingsMainPanel;
import edent.view.utils.SettingsPanelApplication;
import edent.view.utils.SettingsPanelPatients;
import edent.view.utils.SettingsPanelUsers;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Stepan Tesar
 */
public class SettingsForm extends EdentForm{
    private boolean activeUsers = false;
    private boolean activePatients = false;
    private boolean activeApplication = false;
    private SettingsButton[] buttons = new SettingsButton[3];

    /**
     * Creates new form Settings
     */
    public SettingsForm() {
        initComponents();
        this.menuPanel.setBackground(MainFrame.BACKGROUND);
        this.menuButtonsPanel.setBackground(MainFrame.BACKGROUND);
        
        this.setBackground(MainFrame.FOREGROUND);
        this.setSize();
        
        SettingsButton b;
        
        b = new SettingsButtonUsers(this);
        buttons[0] = b;
        this.menuButtonsPanel.add(b);
        b = new SettingsButtonPatients(this);
        buttons[1] = b;
        this.menuButtonsPanel.add(b);
        b = new SettingsButtonApplication(this);
        buttons[2] = b;
        this.menuButtonsPanel.add(b);
        
    }
    
    private void deselectButtons(){
        for(SettingsButton b : buttons){
            b.setSelected(false);
        }
    }
    
    private void display(SettingsMainPanel panel){
        this.deselectButtons();
        this.mainPanel.removeAll();
        this.mainPanel.add(panel);
        this.mainPanel.validate();
        panel.setSize(this.mainPanel.getSize());
        panel.setPreferredSize(this.mainPanel.getSize());
        panel.validate();
        panel.setVisible(true);
    }
    
    public final void setSize(){
        int sw = Toolkit.getDefaultToolkit().getScreenSize().width;
        double w = sw/(MainFrame.PANEL_WIDTH_FRACTION);
        Dimension dim = new Dimension((int)w, this.getHeight());
        
        this.menuPanel.setPreferredSize(dim);
        this.menuPanel.setSize(dim);
    }
    
    /**
     * @return state change. False, if the form was already displayed.
     */
    public boolean displayUsers(){
        if(!this.activeUsers){
            SettingsMainPanel panelUsers = new SettingsPanelUsers();
            this.display(panelUsers);
            this.activeApplication = false;
            this.activePatients = false;
            this.activeUsers = true;
            return true;
        }
        return false;
    }
    
    /**
     * @return state change. False, if the form was already displayed.
     */
    public boolean displayPatients(){
        if(!this.activePatients){
            SettingsMainPanel panelPatients = new SettingsPanelPatients();
            this.display(panelPatients);
            this.activeApplication = false;
            this.activePatients = true;
            this.activeUsers = false;
            return true;
        }
        return false;
    }
    
    /**
     * @return state change. False, if the form was already displayed.
     */
    public boolean displayApplication(){
        if(!this.activeApplication){
            SettingsMainPanel panelApplication = new SettingsPanelApplication();
            this.display(panelApplication);
            this.activeApplication = true;
            this.activePatients = false;
            this.activeUsers = false;
            return true;
        }
        return false;
    }
    
    @Override
    public void setEditing(Object o) {
    }

    @Override
    public void setCreating() {
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel = new javax.swing.JPanel();
        menuButtonsPanel = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        menuPanel.setMinimumSize(new java.awt.Dimension(200, 600));
        menuPanel.setPreferredSize(new java.awt.Dimension(250, 600));

        menuButtonsPanel.setMaximumSize(new java.awt.Dimension(280, 400));
        menuButtonsPanel.setMinimumSize(new java.awt.Dimension(280, 400));
        menuButtonsPanel.setPreferredSize(new java.awt.Dimension(280, 400));

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPanelLayout.createSequentialGroup()
                .addGap(0, 70, Short.MAX_VALUE)
                .addComponent(menuButtonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(menuButtonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
        );

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel menuButtonsPanel;
    private javax.swing.JPanel menuPanel;
    // End of variables declaration//GEN-END:variables

}