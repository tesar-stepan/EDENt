/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.forms;

import edent.controller.ViewController;
import edent.model.Appointment;
import edent.model.Patient;
import edent.model.User;
import edent.model.UserType;
import edent.view.utils.EdentButton;
import edent.view.utils.EdentButtonColor;
import edent.view.utils.MouthPanel;
import edent.view.utils.TextFieldFocusListener;
import edent.view.utils.TimeFormatter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

/**
 *
 * @author Stepan Tesar
 */
public class MouthForm extends EdentForm {

    private static final String LABEL_DOCTORS = "Doktoři:";
    private static final String LABEL_NURSES = "Sestry:";
    private static final String BUTTON_CANCEL_LABEL = "Zavřít";
    private static final String BUTTON_FINISH_LABEL = "Ukončit";
    private static final Dimension BUTTON_CANCEL_SIZE = new Dimension(100, 40);
    private static final Dimension BUTTON_FINISH_SIZE = new Dimension(120, 48);
    private static final int BUTTON_CANCEL_FONT_SIZE = 20;
    private static final int BUTTON_FINISH_FONT_SIZE = 24;
    private Appointment appt;
    private MouthPanel mp;

    /**
     * Creates new form Mouth
     */
    public MouthForm() {
        initComponents();
    }

    private void myInit() {
        String drs = "";
        String nrs = "";

        this.appt.changeServers(this.appt.getPatient().getDoctors());
        this.appt.changeCreator(ViewController.getLogged());

        for (User u : this.appt.getServers()) {
            if (u.getType().equals(UserType.doctor)) {
                drs += u.getSname() + ", ";
            } else if (u.getType().equals(UserType.nurse)) {
                nrs += u.getSname() + ", ";
            }
        }

        this.textDoctors.setText(drs);
        this.textNurses.setText(nrs);

//        buttonStart = new EdentButton(BUTTON_FINISH_LABEL, BUTTON_FINISH_SIZE, BUTTON_FINISH_FONT_SIZE, EdentButtonColor.blue, BorderLayout.CENTER);
//        buttonAction = new EdentButton(BUTTON_CANCEL_LABEL, BUTTON_CANCEL_SIZE, BUTTON_CANCEL_FONT_SIZE, EdentButtonColor.red, BorderLayout.CENTER);

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewController.showUnlogged();
            }
        });

        buttonFinish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewController.showUnlogged();
                appt.changeFinished(true);
                ViewController.refreshAppts();
            }
        });
        
    }

    private void addListeners() {
        long pid = this.appt.getPatient().getId();
        this.textFName.addFocusListener(new TextFieldFocusListener(textFName, panelInfo, true, true, "fname", "Patient", pid));
        this.textSName.addFocusListener(new TextFieldFocusListener(textSName, panelInfo, true, true, "sname", "Patient", pid));
        this.textBDate.addFocusListener(new TextFieldFocusListener(textBDate, panelInfoBDate, false, true, "bdate", "Patient", pid));
        this.textBNum.addFocusListener(new TextFieldFocusListener(textBNum, panelInfoBNum, true, true, "bnum", "Patient", pid));
    }

    private void removeListeners() {
        for (FocusListener fl : textFName.getFocusListeners()) {
            if (fl.getClass().equals(TextFieldFocusListener.class)) {
                textFName.removeFocusListener(fl);
            }
        }
        for (FocusListener fl : textSName.getFocusListeners()) {
            if (fl.getClass().equals(TextFieldFocusListener.class)) {
                textSName.removeFocusListener(fl);
            }
        }
        for (FocusListener fl : textBDate.getFocusListeners()) {
            if (fl.getClass().equals(TextFieldFocusListener.class)) {
                textBDate.removeFocusListener(fl);
            }
        }
        for (FocusListener fl : textBNum.getFocusListeners()) {
            if (fl.getClass().equals(TextFieldFocusListener.class)) {
                textBNum.removeFocusListener(fl);
            }
        }
        for (ActionListener fl : buttonCancel.getActionListeners()) {
            buttonCancel.removeActionListener(fl);
        }
        for (ActionListener fl : buttonFinish.getActionListeners()) {
            buttonFinish.removeActionListener(fl);
        }
//        System.out.println("removed all listeners");
    }

    @Override
    public void setEditing(Object o) {
        this.resetForm();

        this.appt = (Appointment) o;
        Patient p = appt.getPatient();

        this.textFName.setText(p.getFname());
        this.textSName.setText(p.getSname());
        this.textBNum.setText(p.getBirthnum());
        this.textBDate.setText(TimeFormatter.getPatientsBDate(p.getBirthdate()));

        myInit();
        
        mp = new MouthPanel();
        mp.setAppt(appt);
        this.panelMouthWrap.add(mp);
        this.panelMouthWrap.validate();
        mp.setSize(this.panelMouthWrap.getSize());
        mp.setPreferredSize(this.panelMouthWrap.getSize());
        mp.validate();
        mp.setVisible(true);
        
        this.addListeners();
    }

    @Override
    public void setCreating() {
        this.resetForm();
        System.out.println("FORM MouthForm has no creating state");
    }

    @Override
    public void resetForm() {
        this.removeListeners();
        this.panelMouthWrap.removeAll();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        panelDetails = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        textFName = new javax.swing.JTextField();
        textSName = new javax.swing.JTextField();
        textBDate = new javax.swing.JTextField();
        textBNum = new javax.swing.JTextField();
        panelInfo = new javax.swing.JPanel();
        panelInfoBDate = new javax.swing.JPanel();
        panelInfoBNum = new javax.swing.JPanel();
        labelDoctors = new javax.swing.JLabel();
        labelNurses = new javax.swing.JLabel();
        textDoctors = new javax.swing.JLabel();
        textNurses = new javax.swing.JLabel();
        buttonFinish = new EdentButton(BUTTON_FINISH_LABEL, BUTTON_FINISH_SIZE, BUTTON_FINISH_FONT_SIZE, EdentButtonColor.blue, BorderLayout.CENTER);
        buttonCancel = new EdentButton(BUTTON_CANCEL_LABEL, BUTTON_CANCEL_SIZE, BUTTON_CANCEL_FONT_SIZE, EdentButtonColor.red, BorderLayout.CENTER);
        panelMouthWrap = new javax.swing.JPanel();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setOpaque(false);

        panelDetails.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        textFName.setBackground(new java.awt.Color(240, 240, 240));
        textFName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textFName.setText("jmeno");
        textFName.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textFName.setMaximumSize(new java.awt.Dimension(117, 32));
        textFName.setMinimumSize(new java.awt.Dimension(117, 32));
        textFName.setPreferredSize(new java.awt.Dimension(117, 32));

        textSName.setBackground(new java.awt.Color(240, 240, 240));
        textSName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textSName.setText("prijmeni");
        textSName.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textSName.setMaximumSize(new java.awt.Dimension(111, 32));
        textSName.setMinimumSize(new java.awt.Dimension(111, 32));
        textSName.setPreferredSize(new java.awt.Dimension(111, 32));

        textBDate.setBackground(new java.awt.Color(240, 240, 240));
        textBDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textBDate.setText("1.1.1970");
        textBDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textBDate.setMinimumSize(new java.awt.Dimension(127, 27));
        textBDate.setPreferredSize(new java.awt.Dimension(127, 27));

        textBNum.setBackground(new java.awt.Color(240, 240, 240));
        textBNum.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textBNum.setText("700101/0000");
        textBNum.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textBNum.setMinimumSize(new java.awt.Dimension(127, 27));
        textBNum.setPreferredSize(new java.awt.Dimension(127, 27));
        textBNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBNumActionPerformed(evt);
            }
        });

        panelInfo.setBackground(new java.awt.Color(255, 255, 255));
        panelInfo.setPreferredSize(new java.awt.Dimension(25, 25));
        panelInfo.setLayout(new java.awt.BorderLayout());

        panelInfoBDate.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoBDate.setPreferredSize(new java.awt.Dimension(25, 25));
        panelInfoBDate.setLayout(new java.awt.BorderLayout());

        panelInfoBNum.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoBNum.setPreferredSize(new java.awt.Dimension(25, 25));
        panelInfoBNum.setLayout(new java.awt.BorderLayout());

        labelDoctors.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelDoctors.setText(LABEL_DOCTORS);
        labelDoctors.setToolTipText("");

        labelNurses.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelNurses.setText(LABEL_NURSES);

        textDoctors.setText("jLabel3");

        textNurses.setText("jLabel4");

        javax.swing.GroupLayout panelDetailsLayout = new javax.swing.GroupLayout(panelDetails);
        panelDetails.setLayout(panelDetailsLayout);
        panelDetailsLayout.setHorizontalGroup(
            panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetailsLayout.createSequentialGroup()
                        .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDetailsLayout.createSequentialGroup()
                                .addComponent(textBNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelInfoBNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelNurses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDetailsLayout.createSequentialGroup()
                                .addComponent(textBDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelInfoBDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelDoctors, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textNurses, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textDoctors, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelDetailsLayout.createSequentialGroup()
                        .addComponent(textFName, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textSName, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelDetailsLayout.setVerticalGroup(
            panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetailsLayout.createSequentialGroup()
                        .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelDetailsLayout.createSequentialGroup()
                                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelDetailsLayout.createSequentialGroup()
                                        .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(panelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(textSName, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(textFName, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(panelInfoBDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(textBDate, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                    .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelDoctors)
                                        .addComponent(textDoctors)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelInfoBNum, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textBNum, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelNurses)
                                        .addComponent(textNurses)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetailsLayout.createSequentialGroup()
                        .addComponent(buttonFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        panelMouthWrap.setBackground(new java.awt.Color(255, 255, 255));
        panelMouthWrap.setOpaque(false);
        panelMouthWrap.setLayout(new javax.swing.BoxLayout(panelMouthWrap, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelMouthWrap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMouthWrap, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textBNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBNumActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonFinish;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JLabel labelDoctors;
    private javax.swing.JLabel labelNurses;
    private javax.swing.JPanel panelDetails;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelInfoBDate;
    private javax.swing.JPanel panelInfoBNum;
    private javax.swing.JPanel panelMouthWrap;
    private javax.swing.JTextField textBDate;
    private javax.swing.JTextField textBNum;
    private javax.swing.JLabel textDoctors;
    private javax.swing.JTextField textFName;
    private javax.swing.JLabel textNurses;
    private javax.swing.JTextField textSName;
    // End of variables declaration//GEN-END:variables
}
