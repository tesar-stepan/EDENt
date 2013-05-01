/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils.settings;

import edent.controller.ViewController;
import edent.model.utils.OrganState;
import edent.view.utils.OrganStateLine;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

/**
 *
 * @author Stepan Tesar
 */
public class SettingsPanelApplication extends SettingsMainPanel {

    private static final String TS_LABEL = "Nastavení stavů zubů";
    private static final String GS_LABEL = "Nastavení stavů dásní";
    private static final String INFO = "Změnu údajů provedete kliknutím na položku, "
            + "kterou chcete upravit. Po uložení údajů se v pravo zobrazí potvrzení. "
            + "\r\nPro smazání klepněte na tlačítko s křížkem."
            + "\r\nPřidání nové položky proveďte na spodním konci seznamu.";
    public static final String ADD_TEXT = "Zadejte jméno";
    
    private StatePanel toothStatePanel = new StatePanel(true);
    private StatePanel gumStatePanel = new StatePanel(false);

    /**
     * Creates new form SettingsPanelApplication
     */
    public SettingsPanelApplication() {
        initComponents();
        Dimension dim = new Dimension(SettingsPanelApplication.WIDTH, (Toolkit.getDefaultToolkit().getScreenSize().height/2));
        toothStatePanel.setSize(dim);
        toothStatePanel.setPreferredSize(dim);
        //toothStatePanel.toothStatePanel.setPreferredSize(dim);
        gumStatePanel.setSize(dim);
        gumStatePanel.setPreferredSize(dim);
        
        toothStatePanel.stateLabel.setText(TS_LABEL);
        gumStatePanel.stateLabel.setText(GS_LABEL);
        
        toothStatePanel.infoArea.setText(INFO);

        this.createToothList();
        this.createGumList();
    }
    
    /**
     * 
     * @param type "ToothState" or "GumState";
     * @param where BorderLayout.NORTH or BorderLayout.CENTER.
     */
    private void createList(String type, String where) {
        List<OrganState> states = null;
        StatePanel statePanel = null;
        
        switch(type){
            case "ToothState": 
                statePanel = this.toothStatePanel;
                states = ViewController.modelFacade().getToothStates();
                break;
            case "GumState":
                statePanel = this.gumStatePanel;
                states = ViewController.modelFacade().getGumStates();
                break;
        }
        
        statePanel.panelStateList.removeAll();
        

        //creating list layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(statePanel.panelStateList);
        statePanel.panelStateList.setLayout(layout);

        //adding components for every existing ToothState to list
        ParallelGroup pg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false);
        SequentialGroup sg = layout.createSequentialGroup();
        sg.addContainerGap();
        if (states != null) {
            for (OrganState s : states) {
                OrganStateLine line = new OrganStateLine(s.getName(), s.getIcon(), s.getMark(), s.getId(), this, type);
                pg.addComponent(line, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);

                sg.addComponent(line, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
            }
        }

        //adding ADD ToothState lines
        OrganStateLine line = new OrganStateLine(ADD_TEXT, this, type);
        pg.addComponent(line);

        sg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(line, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        sg.addContainerGap();

        //adding list to layout
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pg)
                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(sg));
        
        this.add(statePanel, where);
        statePanel.panelStateList.revalidate();
        this.revalidate();
    }
    
    
    
    public final void createToothList(){
        this.createList("ToothState", BorderLayout.NORTH);
    }
    
    public final void createGumList(){
        this.createList("GumState", BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
