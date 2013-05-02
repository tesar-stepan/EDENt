/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils;

import edent.controller.ViewController;
import edent.model.Appointment;
import edent.model.Diagnosis;
import edent.model.GumState;
import edent.model.Mouth;
import edent.model.Tooth;
import edent.model.ToothState;
import edent.model.User;
import edent.model.UserType;
import edent.model.utils.Diagnosable;
import edent.model.utils.OrganState;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author Stepan Tesar
 */
public class MouthPanel extends javax.swing.JPanel {

    private static final int CHOOSE_LINES = 15;
    private static final String TOP = "nahoře";
    private static final String BOT = "dole";
    private static final String LEFT = "vlevo";
    private static final String RIGHT = "vpravo";
    private static final String MOUTH = "Celá ústa";
    private static final String DIAGNOSES = "Diagnózy: ";
    private static final String BUTTON_ADD_LABEL = "Přidat";
    private static final Dimension BUTTON_ADD_SIZE = new Dimension(61, 29);
    private static final int BUTTON_ADD_FONT_SIZE = 14;
    
    private Appointment appt;
    private Diagnosable selected;
    private List<ToothPanel> tPanels;

    /**
     * Creates new form MouthPanel
     */
    public MouthPanel() {
        initComponents();
    }

    private void myInit() {
        tPanels = new ArrayList<>();
        Set<Tooth> teeth = appt.getPatient().getMouth().getTeeth();
        List<Tooth> top = new ArrayList<>();
        List<Tooth> topL = new ArrayList<>();
        List<Tooth> bot = new ArrayList<>();
        List<Tooth> botL = new ArrayList<>();


        //sorting teeth to the proper sectors
        for (Tooth t : teeth) {
            if (t.isTopFloor()) {
                if (t.isLeftSide()) {
                    topL.add(t);
                } else {
                    top.add(t);
                }
            } else {
                if (t.isLeftSide()) {
                    botL.add(t);
                } else {
                    bot.add(t);
                }
            }
        }

        //sorting teeth ascendlingly
        Collections.sort(bot);
        Collections.sort(botL);
        Collections.sort(top);
        Collections.sort(topL);

        //reversing order of right side of mouth (from patients view).
        Collections.reverse(bot);
        Collections.reverse(top);


        //adding all teeth to the mouht one by one from right to left, top first.
        Dimension dim = new Dimension(60, 15);
        int i = 8;
        for (Tooth t : top) {
            ToothPanel p = new ToothPanel(t);
            this.teethTopPanel.add(p);

            this.addListener(p);

            JLabel num = new JLabel(" " + i + "");
            JPanel pad = new JPanel();
            pad.setPreferredSize(dim);
            pad.setSize(dim);
            pad.setMinimumSize(dim);
            pad.add(num);
            this.teethPosPanel.add(pad);
            i--;
        }
        i++;
        for (Tooth t : topL) {
            ToothPanel p = new ToothPanel(t);
            this.teethTopPanel.add(p);

            this.addListener(p);

            JLabel num = new JLabel(" " + i + "");
            JPanel pad = new JPanel();
            pad.setPreferredSize(dim);
            pad.setSize(dim);
            pad.setMinimumSize(dim);
            pad.add(num);
            this.teethPosPanel.add(pad);
            i++;
        }
        for (Tooth t : bot) {
            ToothPanel p = new ToothPanel(t);
            this.addListener(p);
            this.teethBotPanel.add(p);
        }
        for (Tooth t : botL) {
            ToothPanel p = new ToothPanel(t);
            this.addListener(p);
            this.teethBotPanel.add(p);
        }

        //final touches to the gui.
        this.setInfoMouth(botL.get(0).getMouth());

        initStateChoosers();

        this.teethBotPanel.validate();
        this.teethTopPanel.validate();
        this.teethBotPanel.repaint();
        this.teethTopPanel.repaint();
    }

    /**
     * adds a mouse click listener to the give toothPanel, which changes the
     * info area.
     *
     * @param p
     */
    private void addListener(ToothPanel p) {
        tPanels.add(p);
        p.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (ToothPanel tp : tPanels) {
                    if (!tp.equals(e.getSource())) {
                        tp.setSelected(false);
                    } else {
                        if (!tp.isSelected()) {
                            tp.setSelected(true);
                            setInfoTooth(tp);
                        } else {
                            tp.setSelected(false);
                            setInfoMouth(tp.getTooth().getMouth());
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private void setInfoTooth(ToothPanel tp) {
        this.removeBoxListeners();
        this.removeChooserListeners();
        Tooth t = tp.getTooth();
        this.selected = t;
        String pos = t.getPosition() + " " + (t.isLeftSide() ? LEFT : RIGHT) + " " + (t.isTopFloor() ? TOP : BOT);
        this.setInfoCommon(pos, t);

        System.out.println(t.isBraced());
        this.boxBraces.setEnabled(true);
        this.boxBraces.setSelected(t.isBraced());
        this.boxMilk.setEnabled(true);
        this.boxMilk.setSelected(t.isMilk());

        this.chooseGumState.setEnabled(true);
        this.chooseToothState.setEnabled(true);

        this.chooseGumState.setSelectedItem(t.getGum().getState());
        this.chooseToothState.setSelectedItem(t.getState());

        this.addBoxListeners(tp);
        this.addChooserListeners(tp);
    }

    private void setInfoMouth(Mouth m) {
        this.removeBoxListeners();
        this.removeChooserListeners();
        this.selected = m;
        String pos = MOUTH;
        this.setInfoCommon(pos, m);

        this.boxBraces.setEnabled(false);
        this.boxBraces.setSelected(false);
        this.boxMilk.setEnabled(false);
        this.boxMilk.setSelected(false);
        this.chooseGumState.setEnabled(false);
        this.chooseToothState.setEnabled(false);
    }

    private void setInfoCommon(String header, Diagnosable d) {
        this.labelToothPosition.setText(header);

        this.listDiags.setLayoutOrientation(JList.VERTICAL);
//        this.listDiags.setListData(d.getHistory().getDiagnoses().toArray());
        this.refreshDiagnoses();
    }
    
    private void refreshDiagnoses(){
        List<Diagnosis> diags = new ArrayList<>();
        diags.addAll(this.selected.getHistory().getDiagnoses());
        
        Collections.sort(diags);
        Collections.reverse(diags);
        
        this.listDiags.setListData(diags.toArray());
    }

    private void initStateChoosers() {
        List<OrganState> ts = ViewController.modelFacade().getToothStates();
        List<OrganState> gs = ViewController.modelFacade().getGumStates();

        this.initChooser(chooseToothState, ts);
        this.initChooser(chooseGumState, gs);
    }

    private void initChooser(JComboBox chooser, List<OrganState> list) {
        DefaultComboBoxModel model = new DefaultComboBoxModel(list.toArray());
        chooser.setModel(model);
        chooser.setMaximumRowCount(CHOOSE_LINES);
        chooser.setEditable(false);
    }

    /**
     * Adds a change listeners to the checkBoxes, changing the milk and braced
     * property of selected tooth.
     *
     * @param tp
     */
    private void addBoxListeners(final ToothPanel tp) {
        this.boxBraces.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boxBraces.isSelected()) {
                    tp.getTooth().changeBraced(true);
                    tp.refreshMarks();
                } else {
                    tp.getTooth().changeBraced(false);
                    tp.refreshMarks();
                }
            }
        });

        this.boxMilk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boxMilk.isSelected()) {
                    tp.getTooth().changeMilk(true);
                    tp.refreshMarks();
                } else {
                    tp.getTooth().changeMilk(false);
                    tp.refreshMarks();
                }
            }
        });
    }

    private void removeBoxListeners() {
        for (ActionListener al : this.boxBraces.getActionListeners()) {
            this.boxBraces.removeActionListener(al);
        }
        for (ActionListener al : this.boxMilk.getActionListeners()) {
            this.boxMilk.removeActionListener(al);
        }
    }

    private void addChooserListeners(final ToothPanel tp) {
        this.chooseGumState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                GumState state = (GumState) cb.getSelectedItem();
                tp.getTooth().getGum().changeState(state);
                tp.refreshMarks();
            }
        });

        this.chooseToothState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                ToothState state = (ToothState) cb.getSelectedItem();
                tp.getTooth().changeState(state);
                tp.refreshMarks();
            }
        });
    }

    private void removeChooserListeners() {
        for (ActionListener al : this.chooseGumState.getActionListeners()) {
            this.chooseGumState.removeActionListener(al);
        }
        for (ActionListener al : this.chooseToothState.getActionListeners()) {
            this.chooseToothState.removeActionListener(al);
        }
    }

    /**
     * Sets this forms appointment and changes the visual informations
     * accordingly.
     *
     * @param appt
     */
    public void setAppt(Appointment appt) {
        this.appt = appt;
        myInit();
    }

    /**
     * Resets the panel to the neutrla state. Call setAppt before displaying
     * this panel after callling this method.
     */
    public void reset() {
        this.appt = null;
        this.tPanels = null;
        this.labelToothPosition = null;

        this.teethBotPanel.removeAll();
        this.teethTopPanel.removeAll();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        mainPanel = new javax.swing.JPanel();
        teethTopPanel = new javax.swing.JPanel();
        teethPosPanel = new javax.swing.JPanel();
        teethBotPanel = new javax.swing.JPanel();
        settingsPanel = new javax.swing.JPanel();
        labelToothPosition = new javax.swing.JLabel();
        labelToothState = new javax.swing.JLabel();
        labelGumState = new javax.swing.JLabel();
        chooseToothState = new javax.swing.JComboBox();
        chooseGumState = new javax.swing.JComboBox();
        boxMilk = new javax.swing.JCheckBox();
        boxBraces = new javax.swing.JCheckBox();
        diagsLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listDiags = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        textNewDiag = new javax.swing.JTextArea();
        buttonNewDiag = new EdentButton(BUTTON_ADD_LABEL, BUTTON_ADD_SIZE, BUTTON_ADD_FONT_SIZE, EdentButtonColor.blue, BorderLayout.CENTER);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setMinimumSize(new java.awt.Dimension(738, 400));

        teethTopPanel.setOpaque(false);
        teethTopPanel.setPreferredSize(new java.awt.Dimension(718, 175));
        teethTopPanel.setLayout(new javax.swing.BoxLayout(teethTopPanel, javax.swing.BoxLayout.X_AXIS));

        teethPosPanel.setMaximumSize(new java.awt.Dimension(20, 25));
        teethPosPanel.setMinimumSize(new java.awt.Dimension(20, 25));
        teethPosPanel.setOpaque(false);
        teethPosPanel.setPreferredSize(new java.awt.Dimension(20, 25));
        teethPosPanel.setLayout(new javax.swing.BoxLayout(teethPosPanel, javax.swing.BoxLayout.X_AXIS));

        teethBotPanel.setMinimumSize(new java.awt.Dimension(960, 120));
        teethBotPanel.setOpaque(false);
        teethBotPanel.setPreferredSize(new java.awt.Dimension(960, 175));
        teethBotPanel.setLayout(new javax.swing.BoxLayout(teethBotPanel, javax.swing.BoxLayout.X_AXIS));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(teethPosPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teethTopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teethBotPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(teethTopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(teethPosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(teethBotPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        settingsPanel.setBackground(new java.awt.Color(255, 255, 255));
        settingsPanel.setMinimumSize(new java.awt.Dimension(738, 162));
        settingsPanel.setPreferredSize(new java.awt.Dimension(738, 162));

        labelToothPosition.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelToothPosition.setText("1 vpravo nahore");

        labelToothState.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelToothState.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelToothState.setText("stav zubu");
        labelToothState.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        labelGumState.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelGumState.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelGumState.setText("stav dasne");
        labelGumState.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        chooseToothState.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        chooseToothState.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));

        chooseGumState.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        chooseGumState.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));

        boxMilk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boxMilk.setLabel("mlecny zub");
        boxMilk.setOpaque(false);

        boxBraces.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boxBraces.setLabel("rovnatka");
        boxBraces.setOpaque(false);

        diagsLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        diagsLabel.setText(DIAGNOSES);

        listDiags.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item m sdfhu hhhhh       h h hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh hhhh hhhhhhhhhhhhhh hhhhhhhhhhhhhhh hhh1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listDiags.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(listDiags);

        textNewDiag.setColumns(20);
        textNewDiag.setLineWrap(true);
        textNewDiag.setRows(5);
        textNewDiag.setWrapStyleWord(true);
        jScrollPane3.setViewportView(textNewDiag);

        buttonNewDiag.setMaximumSize(new java.awt.Dimension(61, 29));
        buttonNewDiag.setMinimumSize(new java.awt.Dimension(61, 29));
        buttonNewDiag.setPreferredSize(new java.awt.Dimension(61, 29));
        buttonNewDiag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewDiagActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelToothPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelGumState, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(labelToothState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chooseGumState, 0, 147, Short.MAX_VALUE)
                            .addComponent(chooseToothState, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(60, 60, 60)
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(boxMilk, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(boxBraces, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addComponent(diagsLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonNewDiag, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelToothPosition)
                    .addComponent(diagsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonNewDiag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelToothState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chooseToothState)
                    .addComponent(boxMilk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelGumState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(boxBraces, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chooseGumState))
                        .addGap(58, 58, 58))
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(settingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(settingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNewDiagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewDiagActionPerformed
        if(this.textNewDiag.getText()==null||this.textNewDiag.getText().length()<1){
            return;
        }
        
        long date = System.currentTimeMillis();
        Set<User> users = this.appt.getServers();
        User doctor = null;
        if(users!=null){
            for(User u : users){
                if(u.getType().equals(UserType.doctor)){
                    doctor = u;
                }
            }
        }
        
        User creator = this.appt.getCreator();
        String text = this.textNewDiag.getText();
        
        ViewController.modelFacade().createDiagnosis(date, creator, doctor, text, this.selected.getHistory(), this.appt);
        
        this.textNewDiag.setText("");
        this.refreshDiagnoses();
    }//GEN-LAST:event_buttonNewDiagActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox boxBraces;
    private javax.swing.JCheckBox boxMilk;
    private javax.swing.JButton buttonNewDiag;
    private javax.swing.JComboBox chooseGumState;
    private javax.swing.JComboBox chooseToothState;
    private javax.swing.JLabel diagsLabel;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelGumState;
    private javax.swing.JLabel labelToothPosition;
    private javax.swing.JLabel labelToothState;
    private javax.swing.JList listDiags;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JPanel teethBotPanel;
    private javax.swing.JPanel teethPosPanel;
    private javax.swing.JPanel teethTopPanel;
    private javax.swing.JTextArea textNewDiag;
    // End of variables declaration//GEN-END:variables
}
