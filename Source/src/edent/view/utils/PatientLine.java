/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils;

import edent.controller.ViewController;
import edent.model.Appointment;
import edent.model.Patient;
import edent.view.MainFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Stepan Tesar
 */
public class PatientLine extends AbstractLine {

    private static final String EDIT_LABEL = ">";
    private static final String ADD_LABEL = "+";
    private static final Dimension SQUARE = new Dimension(25, 25);
    private static final int PAD_LENGHT = 40;
    private static final int FONT_SIZE = 18;
    private static final Insets INSETS = new Insets(0, 0, 0, 0);
    private EdentButton editButton;
    private EdentButton addButton;
    private JPanel panelPad;
    private JTextField textFName;
    private JTextField textSName;
    private JTextField textBNum;
    private JTextField textBDate;
    private JRadioButton radio;
    private long patientId;
    private long apptId;
    private Patient patient;
    //private JFileChooser picChooser;
    private boolean header;
    private boolean editing;
    private boolean select;
    private boolean odd;

    /**
     * Creates new visual component for editing/creating Patient.
     *
     * @param fname the first name of patient to be displayed. Provide full
     * String that is to be displayed.
     * @param sname the surname of patient.
     * @param bnum the birth-number od other unique indetifier of the patient.
     * @param bdate the birth date of patient. Provide date formated as String.
     * @param patientId the id of ToothState that this line represents. This
     * vaue must be correct for proper editing functionality, this value is not
     * considered when editing = false;
     * @param editing determines in which state the invoked form will be. False
     * means taht the form will be in state of creating new User, true for
     * editing.
     * @param odd if this line is odd, is is painted in darker color.
     * @param header if set as header, the line will have no buttons.
     */
    public PatientLine(String fname, String sname, String bnum, String bdate, long patientId, boolean editing, boolean odd, boolean header, boolean select, boolean selected, long apptId) {
        this.editing = editing;
        this.header = header;
        this.select = select;
        this.patientId = patientId;
        this.apptId = apptId;
        this.odd = odd;
        
        this.patient = ViewController.modelFacade().getPatient(patientId);

        initComponents();
        myInit();

        this.textFName.setText(fname);
        this.textSName.setText(sname);
        this.textBDate.setText(bdate);
        this.textBNum.setText(bnum);

        this.textFName.setEditable(false);
        this.textSName.setEditable(false);
        this.textBDate.setEditable(false);
        this.textBNum.setEditable(false);


        Color bg;
        if (odd) {
            bg = MainFrame.BACKGROUND;
        } else {
            bg = MainFrame.FOREGROUND;
        }

        this.panelWrapper.setBackground(bg);
        this.textFName.setBackground(bg);
        this.textSName.setBackground(bg);
        this.textBNum.setBackground(bg);
        this.textBDate.setBackground(bg);

    }

    /**
     * A convenient constructor for creating non-headers. See full-arguments
     * constructor for details about headers.
     *
     * @param fname the first name of patient to be displayed. Provide full
     * String that is to be displayed.
     * @param sname the surname of patient.
     * @param bnum the birth-number od other unique indetifier of the patient.
     * @param bdate the birth date of patient. Provide date formated as String.
     * @param patientId the id of ToothState that this line represents. This
     * vaue must be correct for proper editing functionality, this value is not
     * considered when editing = false;
     * @param editing determines in which state the invoked form will be. False
     * means taht the form will be in state of creating new User, true for
     * editing.
     * @param odd if this line is odd, is is painted in darker color.
     * @param select whether to show a radio button.
     * @param selected if the checkbox is displayed, sets its selected state.
     * @param patientId if select is true, this is nessesary for editing
     * actions.
     */
    public PatientLine(String fname, String sname, String bnum, String bdate, long patientId, boolean editing, boolean odd) {
        this(fname, sname, bnum, bdate, patientId, editing, odd, false, false, false, -1);
    }

    /**
     * A convenient costructor for creating an ADD line.
     *
     * @param fname text to be displayed next to the add button
     */
    public PatientLine(String fname) {
        this(null, fname, null, null, -1, false, false, false, false, false, -1);
    }

    private void myInit() {
        int sw = Toolkit.getDefaultToolkit().getScreenSize().width;
        double w = sw / (MainFrame.PANEL_WIDTH_FRACTION);
        Dimension dim = new Dimension(sw - (int) (2 * w), this.getHeight());
//        this.setSize(dim);
//        this.setPreferredSize(dim);
//        this.panelWrapper.setSize(dim);
//        this.panelWrapper.setPreferredSize(dim);

        //variables initialisation
        editButton = new EdentButton(EDIT_LABEL, SQUARE, FONT_SIZE, odd ? EdentButtonColor.gray : EdentButtonColor.white, BorderLayout.CENTER);
        addButton = new EdentButton(ADD_LABEL, SQUARE, FONT_SIZE, EdentButtonColor.green, BorderLayout.CENTER);
        panelPad = new JPanel();
        textFName = new JTextField();
        textSName = new JTextField();
        textBNum = new JTextField();
        textBDate = new JTextField();

        panelWrapper.removeAll();

        Dimension textDim = new Dimension((dim.width / 5) - (SQUARE.width / 4) - 15, SQUARE.height);
        
        textSName.setBorder(null);
        textSName.setMargin(INSETS);
        int swidth = editing||select ? textDim.width : textDim.width * 4;
        textSName.setPreferredSize(new java.awt.Dimension(swidth, SQUARE.height));
        panelWrapper.add(textSName);
        
        if (editing||select) {

            textFName.setBorder(null);
            textFName.setMargin(INSETS);
            textFName.setPreferredSize(textDim);
            panelWrapper.add(textFName);
        } else {
            textSName.setHorizontalAlignment(JTextField.RIGHT);
        }

        if (editing || select) {
            textBDate.setBorder(null);
            textBDate.setMargin(INSETS);
            textBDate.setPreferredSize(textDim);
            panelWrapper.add(textBDate);

            textBNum.setBorder(null);
            textBNum.setMargin(INSETS);
            textBNum.setPreferredSize(textDim);
            panelWrapper.add(textBNum);
        }

        this.addProperButton();

        radio = new JRadioButton();
        radio.setOpaque(false);

        panelPad.setPreferredSize(new java.awt.Dimension(PAD_LENGHT, SQUARE.height));
        panelPad.setBackground(MainFrame.FOREGROUND);
        panelPad.setLayout(new java.awt.BorderLayout());
        if (select) {

            if (editing) {
                radio.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Appointment a = ViewController.modelFacade().getAppointment(apptId);
                        //Patient p = ViewController.modelFacade().getPatient(patientId);
                        if (radio.isSelected()) {
                            a.changePatient(patient);
                        } else {
                        }
                    }
                });
            }
            
            panelPad.add(radio, BorderLayout.WEST);
        }
        panelPad.setOpaque(false);
        panelWrapper.add(panelPad);

        panelWrapper.validate();
    }

    /**
     * Adds a proper button based on this.editing value. Also crates button
     * action listeners with corresponding actions.
     */
    private void addProperButton() {
        if (header || select) {
            return;
        }
        EdentButton enabledButton;
        EdentButton disabledButton;
        if (editing) {
            enabledButton = this.editButton;
            disabledButton = this.addButton;
        } else {
            enabledButton = this.addButton;
            disabledButton = this.editButton;
        }

        disabledButton.setEnabled(false);
        enabledButton.setMargin(null);
        enabledButton.setPreferredSize(SQUARE);
        panelWrapper.add(enabledButton);

        enabledButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (editing) {
                    ViewController.showEditPatient(patientId);
                } else {
                    ViewController.showCreatePatient();
                }
            }
        });
    }

    public long getPatietnId() {
        return patientId;
    }
    
    public Patient getPatient(){
        return this.patient;
    }

    public boolean isSelected() {
        return radio.isSelected();
    }
    
    public JRadioButton getRadio(){
        return this.radio;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        picChooser = new javax.swing.JFileChooser();
        panelWrapper = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(414, 54));
        setPreferredSize(new java.awt.Dimension(414, 54));

        panelWrapper.setBackground(new java.awt.Color(255, 255, 255));
        panelWrapper.setMinimumSize(new java.awt.Dimension(370, 32));
        panelWrapper.setPreferredSize(new java.awt.Dimension(370, 32));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelWrapper;
    private javax.swing.JFileChooser picChooser;
    // End of variables declaration//GEN-END:variables
}
