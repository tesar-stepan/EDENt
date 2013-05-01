/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils.settings;

/**
 *
 * @author Stepan Tesar
 */
public class StatePanel extends javax.swing.JPanel {
    private boolean infobox;
    
    /**
     * Creates new form ToothStatePanel
     */
    public StatePanel(boolean infobox) {
        super();
        this.infobox = infobox;
        initComponents();
        if(!infobox) {
            this.infoArea.setVisible(false);
            this.jScrollPane1.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statePanel = new javax.swing.JPanel();
        stateLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoArea = new javax.swing.JTextArea();
        scrollPane = new javax.swing.JScrollPane();
        panelStateList = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        statePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        stateLabel.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        stateLabel.setText("jLabel1");

        infoArea.setEditable(false);
        infoArea.setColumns(5);
        infoArea.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        infoArea.setLineWrap(true);
        infoArea.setRows(5);
        infoArea.setWrapStyleWord(true);
        infoArea.setAutoscrolls(false);
        infoArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoArea.setFocusable(false);
        infoArea.setHighlighter(null);
        infoArea.setMargin(new java.awt.Insets(10, 10, 10, 10));
        infoArea.setOpaque(false);
        infoArea.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(infoArea);

        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout panelStateListLayout = new javax.swing.GroupLayout(panelStateList);
        panelStateList.setLayout(panelStateListLayout);
        panelStateListLayout.setHorizontalGroup(
            panelStateListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );
        panelStateListLayout.setVerticalGroup(
            panelStateListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(panelStateList);

        javax.swing.GroupLayout statePanelLayout = new javax.swing.GroupLayout(statePanel);
        statePanel.setLayout(statePanelLayout);
        statePanelLayout.setHorizontalGroup(
            statePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stateLabel)
                    .addComponent(scrollPane))
                .addGap(72, 72, 72)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        statePanelLayout.setVerticalGroup(
            statePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(statePanelLayout.createSequentialGroup()
                        .addComponent(stateLabel)
                        .addGap(18, 18, 18)
                        .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea infoArea;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelStateList;
    public javax.swing.JScrollPane scrollPane;
    public javax.swing.JLabel stateLabel;
    public javax.swing.JPanel statePanel;
    // End of variables declaration//GEN-END:variables
}
