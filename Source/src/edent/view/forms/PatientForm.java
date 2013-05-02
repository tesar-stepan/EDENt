/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.forms;

import edent.controller.ViewController;
import edent.model.Patient;
import edent.model.User;
import edent.view.MainFrame;
import edent.view.utils.EdentButton;
import edent.view.utils.EdentButtonColor;
import edent.view.utils.TextFieldFocusListener;
import edent.view.utils.TimeFormatter;
import edent.view.utils.UserLine;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Stepan Tesar
 */
public final class PatientForm extends EdentForm {
//    private final JLabel IMG_OK = new JLabel(new ImageIcon(getClass().getResource("/edent/view/utils/ok.png")));
    private static final String DOCTOR_TYPE = "Doktor";
    private static final String NURSE_TYPE = "Sestra";
    private static final int LINE_HEIGHT = 54;
    private static final String DOCTORS_LABEL = "Vyberte hlavní ošetřující:";
    private static final Dimension SQUARE = new Dimension(25, 25);
    private static final Dimension BUTTON_SIZE = new Dimension(100, 40);
//    private static final String BUTTON_DELETE_LABEL = "Smazat";
    private static final String BUTTON_SAVE_LABEL = "Uložit";
    private static final String FNAME_TEXT = "Jméno";
    private static final String SNAME_TEXT = "Příjmení";
    private static final String BDATE_TEXT = "Narození " + TimeFormatter.BDATE_PATTERN;
    private static final String BNUM_TEXT = "Rodné číslo";
    private static final String INFO_TEXT_EDITING = "Pro změnu údajů je jednoduše přepište. Po uložení se zobrazí potvrzovací ikona.";
    private static final String INFO_TEXT_CREATING = "Pro změnu údajů je jednoduše přepište. Pro uložení použijte zelené tlačítko.";
//    private static final String DELETE_CONFIRM_TEXT = "Chcete skutečně smazat pacienta?";
//    private static final String DELETE_CONFIRM_TITLE = "Potvrďte smazání";
    private static final int BUTTON_FONT_SIZE = 20;
    private boolean editing;
    private long patientId = -1;
    
    private List<UserLine> lines;

    /**
     * Creates new form patient
     */
    public PatientForm() {
        initComponents();
        
        panelInfo.setPreferredSize(SQUARE);
        panelInfo.setBackground(MainFrame.FOREGROUND);
        panelInfo.setLayout(new java.awt.BorderLayout());

        this.setBackground(MainFrame.BACKGROUND);
        this.resetForm();
    }

    private void initUserList() {
        this.labelDoctors.setText(DOCTORS_LABEL);

        List<User> users = ViewController.modelFacade().getUsers();

        int maxSize = LINE_HEIGHT * users.size();
        int mainSize = panelDoctors.getSize().height;
        if (maxSize > mainSize) {
            Dimension dim = new Dimension(panelDoctors.getWidth(), maxSize);
            panelDoctors.setPreferredSize(dim);
            panelDoctors.revalidate();
        }

        int maxRows = mainSize / LINE_HEIGHT;
        int rows = users.size();
        if (maxRows > rows) {
            rows = maxRows;
        }

        GridLayout grid = new GridLayout(rows, 1);
        this.panelDoctors.setLayout(grid);

        Set<User> assigned = null;
        
        if (editing) {
            Patient p = ViewController.modelFacade().getPatient(patientId);
            assigned = p.getDoctors();
        }
        
        this.lines = new ArrayList<>();
        int i = 0;
        for (User u : users) {
            String name = u.getTitlePre() + " " + u.getFname() + " " + u.getSname() + " " + u.getTitlePos();
            String type = null;
            switch (u.getType()) {
                case doctor:
                    type = DOCTOR_TYPE;
                    break;
                case nurse:
                    type = NURSE_TYPE;
                    break;
            }
            boolean selected = assigned != null && assigned.contains(u);

            UserLine line = new UserLine(name, type, u.getId(), editing, i % 2 == 0, false, true, selected, this.patientId);
            
            this.panelDoctors.add(line);
            this.lines.add(line);
            
            i++;
        }
        this.panelDoctors.revalidate();
    }

    private void myInit() {
        if (!editing) {
            EdentButton buttonAction = //editing
                    //? null new EdentButton(BUTTON_DELETE_LABEL, BUTTON_SIZE, BUTTON_FONT_SIZE, EdentButtonColor.red, BorderLayout.CENTER)
                    //: 
                    new EdentButton(BUTTON_SAVE_LABEL, BUTTON_SIZE, BUTTON_FONT_SIZE, EdentButtonColor.green, BorderLayout.CENTER);

            buttonAction.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    if (editing) {
//                        if (ViewController.showConfirmDialog(DELETE_CONFIRM_TEXT, DELETE_CONFIRM_TITLE)) {
//                            //ViewController.modelFacade().deletePatient(patientId);
//                            ViewController.showPrevious();
//                        }
//                    } else {
                    String fname = textFName.getText();
                    String sname = textSName.getText();
                    String bnum = textBNum.getText();
                    long bdate = TimeFormatter.getPatientsBDate(textBDate.getText());
                    long created = System.currentTimeMillis();
                    Set<User> doctors = new HashSet<>();
                    for(UserLine l : lines){
                        if(l.isSelected()){
                            long id = l.getUserId();
                            doctors.add(ViewController.modelFacade().getUser(id));
                        }
                    }

                    //String icon = picChooser.getSelectedFile().getAbsolutePath(); //TODO file checking and copying
                    if (fname != null && fname.length() > 0 && sname != null && sname.length() > 0 && bnum != null && bnum.length() > 0 && bdate != -1) {
                        ViewController.modelFacade().createPatient(fname, sname, bnum, bdate, created, doctors);
                        ViewController.showPrevious();
                    } else {
//                        System.out.println("problem: " + bdate);
                    }
//                    }
                }
            });

            this.buttonWrap.add(buttonAction);
        }
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
//        System.out.println("removed all listeners");
    }

    private void addListeners() {
        if (editing) {
            this.textFName.addFocusListener(new TextFieldFocusListener(textFName, panelInfo, true, true, "fname", "Patient", this.patientId));
            this.textSName.addFocusListener(new TextFieldFocusListener(textSName, panelInfo, true, true, "sname", "Patient", this.patientId));
            this.textBDate.addFocusListener(new TextFieldFocusListener(textBDate, panelInfoBDate, false, true, "bdate", "Patient", this.patientId));
            this.textBNum.addFocusListener(new TextFieldFocusListener(textBNum, panelInfoBNum, true, true, "bnum", "Patient", this.patientId));
//            System.out.println("added editing listeners");
        } else {
            this.textFName.addFocusListener(new TextFieldFocusListener(textFName, panelInfo, true));
            this.textSName.addFocusListener(new TextFieldFocusListener(textSName, panelInfo, true));
            this.textBDate.addFocusListener(new TextFieldFocusListener(textBDate, panelInfoBDate, false));
            this.textBNum.addFocusListener(new TextFieldFocusListener(textBNum, panelInfoBNum, true));
//            System.out.println("added creating listeners");
        }
    }

    @Override
    public void setEditing(Object o) {
        this.resetForm();
        
        Patient p = (Patient) o;
        this.editing = true;
        this.patientId = p.getId();
        this.myInit();

        this.textFName.setText(p.getFname());
        this.textSName.setText(p.getSname());
        this.textBNum.setText(p.getBirthnum());
        this.textBDate.setText(TimeFormatter.getPatientsBDate(p.getBirthdate()));

        this.infoBox.setText(INFO_TEXT_EDITING);
        
        this.initUserList();
        this.addListeners();
    }

    @Override
    public void setCreating() {
        this.resetForm();
        this.myInit();
        this.addListeners();
        this.initUserList();
    }

    @Override
    public void resetForm() {
        this.removeListeners();
        this.editing = false;
        this.infoBox.setText(INFO_TEXT_CREATING);

        panelFoto.removeAll();
        textFName.setText(FNAME_TEXT);
        textSName.setText(SNAME_TEXT);
        textBNum.setText(BNUM_TEXT);
        textBDate.setText(BDATE_TEXT);

        this.panelInfo.removeAll();
        this.panelInfoBNum.removeAll();
        this.panelInfoBDate.removeAll();
        
        this.panelDoctors.removeAll();

        this.buttonWrap.removeAll();
        this.patientId = -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPass = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        buttonWrap = new javax.swing.JPanel();
        textSName = new javax.swing.JTextField();
        textFName = new javax.swing.JTextField();
        panelFoto = new javax.swing.JPanel();
        textBDate = new javax.swing.JTextField();
        panelInfo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoBox = new javax.swing.JTextArea();
        panelInfoBDate = new javax.swing.JPanel();
        panelInfoBNum = new javax.swing.JPanel();
        textBNum = new javax.swing.JTextField();
        scrollDoctors = new javax.swing.JScrollPane();
        panelDoctors = new javax.swing.JPanel();
        labelDoctors = new javax.swing.JLabel();

        labelPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        buttonWrap.setOpaque(false);

        javax.swing.GroupLayout buttonWrapLayout = new javax.swing.GroupLayout(buttonWrap);
        buttonWrap.setLayout(buttonWrapLayout);
        buttonWrapLayout.setHorizontalGroup(
            buttonWrapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 88, Short.MAX_VALUE)
        );
        buttonWrapLayout.setVerticalGroup(
            buttonWrapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );

        textSName.setBackground(new java.awt.Color(240, 240, 240));
        textSName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textSName.setText("prijmeni");
        textSName.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textSName.setMaximumSize(new java.awt.Dimension(111, 32));
        textSName.setMinimumSize(new java.awt.Dimension(111, 32));
        textSName.setPreferredSize(new java.awt.Dimension(111, 32));

        textFName.setBackground(new java.awt.Color(240, 240, 240));
        textFName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textFName.setText("jmeno");
        textFName.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textFName.setMaximumSize(new java.awt.Dimension(117, 32));
        textFName.setMinimumSize(new java.awt.Dimension(117, 32));
        textFName.setPreferredSize(new java.awt.Dimension(117, 32));

        javax.swing.GroupLayout panelFotoLayout = new javax.swing.GroupLayout(panelFoto);
        panelFoto.setLayout(panelFotoLayout);
        panelFotoLayout.setHorizontalGroup(
            panelFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelFotoLayout.setVerticalGroup(
            panelFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        textBDate.setBackground(new java.awt.Color(240, 240, 240));
        textBDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textBDate.setText("1.1.1970");
        textBDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textBDate.setMinimumSize(new java.awt.Dimension(127, 27));
        textBDate.setPreferredSize(new java.awt.Dimension(127, 27));

        panelInfo.setBackground(new java.awt.Color(255, 255, 255));
        panelInfo.setPreferredSize(new java.awt.Dimension(25, 25));
        panelInfo.setLayout(new java.awt.BorderLayout());

        infoBox.setColumns(20);
        infoBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infoBox.setLineWrap(true);
        infoBox.setRows(5);
        infoBox.setWrapStyleWord(true);
        infoBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jScrollPane1.setViewportView(infoBox);

        panelInfoBDate.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoBDate.setPreferredSize(new java.awt.Dimension(25, 25));
        panelInfoBDate.setLayout(new java.awt.BorderLayout());

        panelInfoBNum.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoBNum.setPreferredSize(new java.awt.Dimension(25, 25));
        panelInfoBNum.setLayout(new java.awt.BorderLayout());

        textBNum.setBackground(new java.awt.Color(240, 240, 240));
        textBNum.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textBNum.setText("700101/0000");
        textBNum.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textBNum.setMinimumSize(new java.awt.Dimension(127, 27));
        textBNum.setPreferredSize(new java.awt.Dimension(127, 27));

        scrollDoctors.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDoctors.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout panelDoctorsLayout = new javax.swing.GroupLayout(panelDoctors);
        panelDoctors.setLayout(panelDoctorsLayout);
        panelDoctorsLayout.setHorizontalGroup(
            panelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 901, Short.MAX_VALUE)
        );
        panelDoctorsLayout.setVerticalGroup(
            panelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 411, Short.MAX_VALUE)
        );

        scrollDoctors.setViewportView(panelDoctors);

        labelDoctors.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDoctors.setText("priradte hlavni osetrujici");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollDoctors, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textBDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textBNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelInfoBDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelInfoBNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textFName, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textSName, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonWrap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelDoctors)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textSName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textBDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelInfoBDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelInfoBNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textBNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(buttonWrap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(labelDoctors)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollDoctors, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(labelPass, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPass)
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonWrap;
    private javax.swing.JTextArea infoBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDoctors;
    private javax.swing.JLabel labelPass;
    private javax.swing.JPanel panelDoctors;
    private javax.swing.JPanel panelFoto;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelInfoBDate;
    private javax.swing.JPanel panelInfoBNum;
    private javax.swing.JScrollPane scrollDoctors;
    private javax.swing.JTextField textBDate;
    private javax.swing.JTextField textBNum;
    private javax.swing.JTextField textFName;
    private javax.swing.JTextField textSName;
    // End of variables declaration//GEN-END:variables
}
