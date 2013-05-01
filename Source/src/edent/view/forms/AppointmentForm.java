/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.forms;

import edent.controller.ViewController;
import edent.model.Appointment;
import edent.model.Patient;
import edent.view.MainFrame;
import edent.view.utils.EdentButton;
import edent.view.utils.EdentButtonColor;
import edent.view.utils.PatientLine;
import edent.view.utils.TextFieldFocusListener;
import edent.view.utils.TimeFormatter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Stepan Tesar
 */
public final class AppointmentForm extends EdentForm {
//    private final JLabel IMG_OK = new JLabel(new ImageIcon(getClass().getResource("/edent/view/utils/ok.png")));

    private static final int LINE_HEIGHT = 54;
    private static final String PATIENTS_LABEL = "Vyberte pacienta:";
    private static final Dimension SQUARE = new Dimension(25, 25);
    private static final Dimension BUTTON_SIZE = new Dimension(100, 40);
    private static final String BUTTON_DELETE_LABEL = "Smazat";
    private static final String BUTTON_SAVE_LABEL = "Uložit";
    private static final String FNAME_TEXT = "Poznámky... ";
    private static final String BDATE_TEXT = "Datum a čas";
    private static final String INFO_TEXT_EDITING = "Pro změnu údajů je jednoduše přepište. Po uložení se zobrazí potvrzovací ikona.";
    private static final String INFO_TEXT_CREATING = "Pro změnu údajů je jednoduše přepište. Pro uložení použijte zelené tlačítko.";
    private static final String DELETE_CONFIRM_TEXT = "Chcete skutečně smazat schůzku?";
    private static final String DELETE_CONFIRM_TITLE = "Potvrďte smazání";
    private static final String FILTER_FNAME_TEXT = "Jméno";
    private static final String FILTER_SNAME_TEXT = "Příjmení";
    private static final String FILTER_BDATE_TEXT = "Datum narození "+TimeFormatter.BDATE_PATTERN;
    private static final String FILTER_BUTTON_LABEL = "filtrovat";
    private static final String FILTER_CANCEL_LABEL = "x";
    private static final int BUTTON_FONT_SIZE = 20;
    private boolean editing;
    private long apptId = -1;
    private List<PatientLine> lines;
    
    private String filterFName = "";
    private String filterSName = "";
    private long filterBDate = -1;

    /**
     * Creates new form appointemnt
     */
    public AppointmentForm() {
        initComponents();

        panelInfo.setPreferredSize(SQUARE);
        panelInfo.setBackground(MainFrame.FOREGROUND);
        panelInfo.setLayout(new java.awt.BorderLayout());

        this.setBackground(MainFrame.BACKGROUND);
        this.resetForm();
        
        this.panelPatient.revalidate();
        this.scrollPatient.revalidate();
        this.revalidate();
    }

    private List<Patient> initPatientList() {
        this.panelPatient= new JPanel();
        
        this.labelPatient.setText(PATIENTS_LABEL);

        List<Patient> patients = ViewController.modelFacade().getFilteredPatients(this.filterFName,this.filterSName,this.filterBDate);

        this.initCommon(patients);
        
        this.scrollPatient.setViewportView(panelPatient);
        this.panelPatient.revalidate();
        this.scrollPatient.validate();
        
        return patients;
    }
    
    private void initPatientList2(List<Patient> patients) {
        this.panelPatient.removeAll();
        
        this.labelPatient.setText(PATIENTS_LABEL);

//        List<Patient> patients = ViewController.modelFacade().getFilteredPatients(this.filterFName,this.filterSName,this.filterBDate);
        this.initCommon(patients);
        
        this.panelPatient.revalidate();
        this.scrollPatient.validate();
    }
    
    private void initCommon(List<Patient> patients){
        int maxSize = LINE_HEIGHT * patients.size();
        int mainSize = panelPatient.getSize().height;
        if (maxSize > mainSize) {
            Dimension dim = new Dimension(panelPatient.getWidth(), maxSize);
            panelPatient.setPreferredSize(dim);
        }

        int maxRows = mainSize / LINE_HEIGHT;
        int rows = patients.size();
        if (maxRows > rows) {
            rows = maxRows;
        }

        GridLayout grid = new GridLayout(rows, 1);
        this.panelPatient.setLayout(grid);

        Patient assigned = null;

        if (editing) {
            Appointment a = ViewController.modelFacade().getAppointment(apptId);
            assigned = a.getPatient();
        }

        lines = new ArrayList<>();
        int i = 0;
        for (Patient p : patients) {

            String bdate = TimeFormatter.getPatientsBDate(p.getBirthdate());

            boolean selected = assigned != null && assigned.equals(p);

            PatientLine line = new PatientLine(p.getFname(), p.getSname(), p.getBirthnum(), bdate, p.getId(), editing, i % 2 == 0, false, true, selected, apptId);
            this.panelPatient.add(line);
            this.lines.add(line);

            line.getRadio().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (PatientLine l : lines) {
                        if(!e.getSource().equals(l.getRadio())){
                            l.getRadio().setSelected(false);
                        }
                    }
                }
            });

            i++;
        }
    }

    private void myInit() {
        if (!editing) {
            EdentButton buttonAction = editing
                    ? new EdentButton(BUTTON_DELETE_LABEL, BUTTON_SIZE, BUTTON_FONT_SIZE, EdentButtonColor.red, BorderLayout.CENTER)
                    : new EdentButton(BUTTON_SAVE_LABEL, BUTTON_SIZE, BUTTON_FONT_SIZE, EdentButtonColor.green, BorderLayout.CENTER);

            buttonAction.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (editing) {
                        if (ViewController.showConfirmDialog(DELETE_CONFIRM_TEXT, DELETE_CONFIRM_TITLE)) {
                            ViewController.modelFacade().deleteAppointment(apptId);
                            ViewController.showPrevious();
                        }
                    } else {
                        String note = textNote.getText();
                        long bdate = TimeFormatter.getPatientsBDate(textDate.getText());
                        long patientId = getPatientId();

                        //String icon = picChooser.getSelectedFile().getAbsolutePath(); //TODO file checking and copying
                        if (bdate != -1 && patientId > -1) {
                            ViewController.modelFacade().createAppointment(bdate, note, patientId);
                            ViewController.showPrevious();
                        } else if(patientId==-1){
                            System.out.println("Patient not selected");
                            labelPatient.setBackground(Color.red);
                        } else{
                            System.out.println("Date not specified or in wrong format");
                        }
                    }
                }
            });

            this.buttonWrap.add(buttonAction);
        }
    }

    private long getPatientId() {
        for(PatientLine l : this.lines){
            if(l.isSelected()) {
                return l.getPatietnId();
            }
        }
        return -1;
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
//        System.out.println("removed all listeners");
    }

    private void addListeners() {
        if (editing) {
            this.textNote.addFocusListener(new TextFieldFocusListener(textNote, panelInfo, false, true, "fname", "Appointment", this.apptId));
            this.textDate.addFocusListener(new TextFieldFocusListener(textDate, panelInfoBDate, true, true, "bdate", "Appointment", this.apptId));
//            System.out.println("added editing listeners");
        } else {
            this.textNote.addFocusListener(new TextFieldFocusListener(textNote, panelInfo, false));
            this.textDate.addFocusListener(new TextFieldFocusListener(textDate, panelInfoBDate, true, true, null, null, -1));
//            System.out.println("added creating listeners");
        }
    }
    
    private void setFiltering(String fname, String sname, String bdate){
        this.filterBDate = TimeFormatter.getPatientsBDate(bdate);
        this.filterFName = fname;
        this.filterSName = sname;
//        System.out.println("filtering: "+this.filterFName+" "+this.filterSName+" "+this.filterBDate);
    }

    @Override
    public void setEditing(Object o) {
        this.resetForm();

        Appointment a = (Appointment) o;
        this.editing = true;
        this.apptId = a.getId();
        this.myInit();

        this.textNote.setText(a.getNote());
        this.textDate.setText(TimeFormatter.getAppointmentDate(a.getDate()));

        this.infoBox.setText(INFO_TEXT_EDITING);

        this.initPatientList();
        this.initPatientList();
        this.addListeners();
    }

    @Override
    public void setCreating() {
        this.resetForm();
        this.myInit();
        this.addListeners();
        this.initPatientList2(this.initPatientList());
    }

    @Override
    public void resetForm() {
        this.removeListeners();
        this.editing = false;
        this.infoBox.setText(INFO_TEXT_CREATING);

        panelFoto.removeAll();
        textNote.setText(FNAME_TEXT);
        textDate.setText(TimeFormatter.getAppointmentDate(System.currentTimeMillis()));

        this.panelInfo.removeAll();
        this.panelInfoBDate.removeAll();

        this.panelPatient.removeAll();

        this.buttonWrap.removeAll();
        this.apptId = -1;
        
        this.filterCancelButtonActionPerformed(null);
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
        panelPatient = new javax.swing.JPanel();
        labelPatient = new javax.swing.JLabel();
        labelDate = new javax.swing.JLabel();
        panelFilters = new javax.swing.JPanel();
        filterButton = new EdentButton(FILTER_BUTTON_LABEL, new java.awt.Dimension(80, 25), 14, EdentButtonColor.blue, BorderLayout.CENTER);
        textFNameFilter = new javax.swing.JTextField();
        textSNameFilter = new javax.swing.JTextField();
        textBDateFilter = new javax.swing.JTextField();
        filterCancelButton = new EdentButton(FILTER_CANCEL_LABEL, SQUARE, 14, EdentButtonColor.red, BorderLayout.CENTER);

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

        panelPatient.setPreferredSize(new java.awt.Dimension(901, 211));

        javax.swing.GroupLayout panelPatientLayout = new javax.swing.GroupLayout(panelPatient);
        panelPatient.setLayout(panelPatientLayout);
        panelPatientLayout.setHorizontalGroup(
            panelPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 901, Short.MAX_VALUE)
        );
        panelPatientLayout.setVerticalGroup(
            panelPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 211, Short.MAX_VALUE)
        );

        scrollPatient.setViewportView(panelPatient);

        labelPatient.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelPatient.setText("vybete pacienta");

        labelDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDate.setText(TimeFormatter.APPT_PATTERN);
        labelDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        filterButton.setPreferredSize(new java.awt.Dimension(80, 25));
        filterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterButtonActionPerformed(evt);
            }
        });

        textFNameFilter.setText(FILTER_FNAME_TEXT);
        textFNameFilter.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textFNameFilter.setPreferredSize(new java.awt.Dimension(36, 25));

        textSNameFilter.setText(FILTER_SNAME_TEXT);
        textSNameFilter.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textSNameFilter.setPreferredSize(new java.awt.Dimension(37, 25));

        textBDateFilter.setText(FILTER_BDATE_TEXT);
        textBDateFilter.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textBDateFilter.setPreferredSize(new java.awt.Dimension(28, 25));

        filterCancelButton.setMaximumSize(new java.awt.Dimension(25, 25));
        filterCancelButton.setMinimumSize(new java.awt.Dimension(25, 25));
        filterCancelButton.setPreferredSize(new java.awt.Dimension(25, 25));
        filterCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterCancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFiltersLayout = new javax.swing.GroupLayout(panelFilters);
        panelFilters.setLayout(panelFiltersLayout);
        panelFiltersLayout.setHorizontalGroup(
            panelFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFiltersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textSNameFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFNameFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textBDateFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelFiltersLayout.setVerticalGroup(
            panelFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filterCancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelFiltersLayout.createSequentialGroup()
                        .addGroup(panelFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(filterButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textBDateFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textSNameFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFNameFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

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
                                .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonWrap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelPatient)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(panelFilters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(textNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelInfoBDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(labelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonWrap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelFilters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(scrollPatient)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPass))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void filterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterButtonActionPerformed
        String fname = (this.textFNameFilter.getText().equals(FILTER_FNAME_TEXT))?"":this.textFNameFilter.getText();
        String sname = (this.textSNameFilter.getText().equals(FILTER_SNAME_TEXT))?"":this.textSNameFilter.getText();
        String bdate = (this.textBDateFilter.getText().equals(FILTER_BDATE_TEXT))?"":this.textBDateFilter.getText();
        this.setFiltering(fname, sname, bdate);
        this.initPatientList2(this.initPatientList());
    }//GEN-LAST:event_filterButtonActionPerformed

    private void filterCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterCancelButtonActionPerformed
        this.textFNameFilter.setText(FILTER_FNAME_TEXT);
        this.textSNameFilter.setText(FILTER_SNAME_TEXT);
        this.textBDateFilter.setText(FILTER_BDATE_TEXT);
        this.setFiltering("", "", "");
        this.initPatientList2(this.initPatientList());
    }//GEN-LAST:event_filterCancelButtonActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonWrap;
    private javax.swing.JButton filterButton;
    private javax.swing.JButton filterCancelButton;
    private javax.swing.JTextArea infoBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDate;
    private javax.swing.JLabel labelPass;
    private javax.swing.JLabel labelPatient;
    private javax.swing.JPanel panelFilters;
    private javax.swing.JPanel panelFoto;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelInfoBDate;
    private javax.swing.JPanel panelPatient;
    private javax.swing.JScrollPane scrollPatient;
    private javax.swing.JTextField textBDateFilter;
    private javax.swing.JTextField textDate;
    private javax.swing.JTextField textFNameFilter;
    private javax.swing.JTextField textNote;
    private javax.swing.JTextField textSNameFilter;
    // End of variables declaration//GEN-END:variables
}
