/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.forms;

import edent.controller.ViewController;
import edent.model.Appointment;
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
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;

/**
 *
 * @author Stepan Tesar
 */
public final class AppointmentDetailsForm extends EdentForm {
//    private final JLabel IMG_OK = new JLabel(new ImageIcon(getClass().getResource("/edent/view/utils/ok.png")));

    private static final String DOCTOR_TYPE = "Doktor";
    private static final String NURSE_TYPE = "Sestra";
    private static final int LINE_HEIGHT = 54;
    private static final String DOCTORS_LABEL = "Vyberte ošetřující:";
    private static final Dimension SQUARE = new Dimension(25, 25);
    private static final Dimension BUTTON_SIZE = new Dimension(100, 40);
    private static final String BUTTON_DELETE_LABEL = "Smazat";
    private static final String BUTTON_START_LABEL = "Začít";
    private static final Dimension BUTTON_START_SIZE = new Dimension(120, 48);
    private static final String FNAME_TEXT = "Poznámky... ";
    private static final String INFO_TEXT_EDITING = "Pro změnu údajů je jednoduše přepište. Po uložení se zobrazí potvrzovací ikona.";
    private static final String INFO_TEXT_CREATING = "Pro změnu údajů je jednoduše přepište. Pro uložení použijte zelené tlačítko.";
    private static final String DELETE_CONFIRM_TEXT = "Chcete skutečně smazat schůzku?";
    private static final String DELETE_CONFIRM_TITLE = "Potvrďte smazání";
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int BUTTON_START_FONT_SIZE = 24;
    private JPanel pad = new JPanel();
    private boolean editing;
    private long apptId = -1;
    private long patientId = -1;
    private List<UserLine> lines;

    /**
     * Creates new form appointemnt
     */
    public AppointmentDetailsForm() {
        initComponents();

        panelInfo.setPreferredSize(SQUARE);
        panelInfo.setBackground(MainFrame.FOREGROUND);
        panelInfo.setLayout(new java.awt.BorderLayout());

        this.setBackground(MainFrame.BACKGROUND);
        this.resetForm();

        this.panelDoctors.revalidate();
        this.scrollPatient.revalidate();
        this.revalidate();
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
        if (editing) {

//            buttonAction = new EdentButton(BUTTON_DELETE_LABEL, BUTTON_SIZE, BUTTON_FONT_SIZE, EdentButtonColor.red, BorderLayout.CENTER);
//            buttonStart = new EdentButton(BUTTON_START_LABEL, BUTTON_START_SIZE, BUTTON_START_FONT_SIZE, EdentButtonColor.blue, BorderLayout.CENTER);

            buttonDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (editing) {
                        if (ViewController.showConfirmDialog(DELETE_CONFIRM_TEXT, DELETE_CONFIRM_TITLE)) {
                            ViewController.modelFacade().deleteAppointment(apptId);
                            ViewController.showPrevious();
                            ViewController.refreshAppts();
                        }
                    }
                }
            });

            buttonStart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (editing) {
                        ViewController.showMouth(apptId);
                    }
                }
            });
        }
    }

    private void removeListeners() {
        for (FocusListener fl : textNote.getFocusListeners()) {
            if (fl.getClass().equals(TextFieldFocusListener.class)) {
                textNote.removeFocusListener(fl);
            }
        }
        for (FocusListener fl : textDate.getFocusListeners()) {
            if (fl.getClass().equals(TextFieldFocusListener.class)) {
                textDate.removeFocusListener(fl);
            }
        }
        for (ActionListener fl : buttonDelete.getActionListeners()) {
            buttonDelete.removeActionListener(fl);
        }
        for (ActionListener fl : buttonStart.getActionListeners()) {
            buttonStart.removeActionListener(fl);
        }
//        System.out.println("removed all listeners");
    }

    private void addListeners() {
        if (editing) {
            this.textNote.addFocusListener(new TextFieldFocusListener(textNote, panelInfo, false, true, "note", "Appointment", this.apptId));
            this.textDate.addFocusListener(new TextFieldFocusListener(textDate, panelInfoBDate, true, true, "date", "Appointment", this.apptId));
//            System.out.println("added editing listeners");
        } else {
            this.textNote.addFocusListener(new TextFieldFocusListener(textNote, panelInfo, false));
            this.textDate.addFocusListener(new TextFieldFocusListener(textDate, panelInfoBDate, true, true, null, null, -1));
//            System.out.println("added creating listeners");
        }
    }

    @Override
    public void setEditing(Object o) {
        this.resetForm();

        Appointment a = (Appointment) o;
        this.editing = true;
        this.apptId = a.getId();
        this.patientId = a.getPatient().getId();
        this.myInit();

        this.textNote.setText(a.getNote());
        this.textDate.setText(TimeFormatter.getAppointmentDate(a.getDate()));

        this.infoBox.setText(INFO_TEXT_EDITING);

        String name = a.getPatient().getSname() + " " + a.getPatient().getFname();
        this.labelName.setText(name);

        this.initUserList();
        this.addListeners();
    }

    @Override
    public void setCreating() {
        this.resetForm();
        System.out.println("FORM AppointmentDetailsForm has no creating state");
    }

    @Override
    public void resetForm() {
        this.removeListeners();
        this.editing = false;
        this.infoBox.setText(INFO_TEXT_CREATING);

        panelFoto.removeAll();
        textNote.setText(FNAME_TEXT);
        textDate.setText(TimeFormatter.getAppointmentDate(System.currentTimeMillis()));

        this.labelName.setText("");

        this.panelInfo.removeAll();
        this.panelInfoBDate.removeAll();

        this.panelDoctors.removeAll();

        this.buttonWrap.removeAll();
        this.apptId = -1;
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
        textNote = new javax.swing.JTextField();
        panelFoto = new javax.swing.JPanel();
        textDate = new javax.swing.JTextField();
        panelInfo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoBox = new javax.swing.JTextArea();
        panelInfoBDate = new javax.swing.JPanel();
        scrollPatient = new javax.swing.JScrollPane();
        panelDoctors = new javax.swing.JPanel();
        labelDoctors = new javax.swing.JLabel();
        labelDate = new javax.swing.JLabel();
        buttonWrapBig = new javax.swing.JPanel();
        buttonStart = new EdentButton(BUTTON_START_LABEL, BUTTON_START_SIZE, BUTTON_START_FONT_SIZE, EdentButtonColor.blue, BorderLayout.CENTER);
        buttonDelete = new EdentButton(BUTTON_DELETE_LABEL, BUTTON_SIZE, BUTTON_FONT_SIZE, EdentButtonColor.red, BorderLayout.CENTER);
        labelName = new javax.swing.JLabel();

        labelPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        buttonWrap.setOpaque(false);
        buttonWrap.setLayout(new java.awt.BorderLayout());

        textNote.setBackground(new java.awt.Color(240, 240, 240));
        textNote.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textNote.setText("poznamka");
        textNote.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textNote.setMaximumSize(new java.awt.Dimension(117, 32));
        textNote.setMinimumSize(new java.awt.Dimension(117, 32));
        textNote.setPreferredSize(new java.awt.Dimension(117, 32));

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

        textDate.setBackground(new java.awt.Color(240, 240, 240));
        textDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textDate.setText("1.1.1970");
        textDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textDate.setMinimumSize(new java.awt.Dimension(127, 27));
        textDate.setPreferredSize(new java.awt.Dimension(127, 27));

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

        scrollPatient.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPatient.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelDoctors.setPreferredSize(new java.awt.Dimension(901, 211));

        javax.swing.GroupLayout panelDoctorsLayout = new javax.swing.GroupLayout(panelDoctors);
        panelDoctors.setLayout(panelDoctorsLayout);
        panelDoctorsLayout.setHorizontalGroup(
            panelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 901, Short.MAX_VALUE)
        );
        panelDoctorsLayout.setVerticalGroup(
            panelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );

        scrollPatient.setViewportView(panelDoctors);

        labelDoctors.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDoctors.setText("vyberte osetrujici");

        labelDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDate.setText(TimeFormatter.APPT_PATTERN);
        labelDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        buttonWrapBig.setOpaque(false);
        buttonWrapBig.setLayout(new java.awt.BorderLayout());

        labelName.setText("fname sname");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPatient, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(textDate, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(panelInfoBDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(textNote, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonWrapBig, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonWrap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                .addComponent(buttonStart, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDoctors)
                            .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonWrapBig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonWrap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(buttonStart, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(textNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(11, 11, 11)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(panelInfoBDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, 0)
                            .addComponent(labelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelDoctors, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(labelPass, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(475, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPass))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonStart;
    private javax.swing.JPanel buttonWrap;
    private javax.swing.JPanel buttonWrapBig;
    private javax.swing.JTextArea infoBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDate;
    private javax.swing.JLabel labelDoctors;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelPass;
    private javax.swing.JPanel panelDoctors;
    private javax.swing.JPanel panelFoto;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelInfoBDate;
    private javax.swing.JScrollPane scrollPatient;
    private javax.swing.JTextField textDate;
    private javax.swing.JTextField textNote;
    // End of variables declaration//GEN-END:variables
}
