/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils;

import edent.controller.ViewController;
import edent.model.Patient;
import edent.model.User;
import edent.view.MainFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Stepan Tesar
 */
public class UserLine extends AbstractLine {

    private static final String EDIT_LABEL = ">";
    private static final String ADD_LABEL = "+";
    private static final Dimension SQUARE = new Dimension(25, 25);
    private static final int PAD_LENGHT = 40;
    private static final int TYPE_LENGHT = 100;
    private static final int FONT_SIZE = 18;
    private static final Insets INSETS = new Insets(0, 0, 0, 0);
    private EdentButton editButton;
    private EdentButton addButton;
    private JPanel panelPad;
    private JTextField textName;
    private JTextField textType;
    private JCheckBox box;
    private long userId;
    private long patientId;
    //private JFileChooser picChooser;
    private boolean header;
    private boolean editing;
    private boolean select;
    private boolean odd;

    /**
     * Creates new visual component for editing/creating User.
     *
     * @param name the name of user to be displayed. Provide full String that is
     * to be displayed.
     * @param type the type of user.
     * @param userId the id of ToothState that this line represents. This vaue
     * must be correct for proper editing functionality, this value is not
     * considered when editing = false;
     * @param editing determines in which state the invoked form will be. False
     * means taht the form will be in state of creating new User, true for
     * editing.
     * @param odd if this line is odd, is is painted in darker color.
     * @param header if set as header, the line will have no buttons.
     * @param select whether to show a checkbox
     * @param selected if the checkbox is displayed, sets its selected state.
     * @param patientId if select is true, this is nessesary for editing
     * actions.
     */
    public UserLine(String name, String type, long userId, boolean editing, boolean odd, boolean header, boolean select, boolean selected, long patientId) {
        this.editing = editing;
        this.header = header;
        this.userId = userId;
        this.select = select;
        this.odd = odd;

        this.patientId = patientId;

        initComponents();
        myInit();

        this.textName.setText(name);
        this.textType.setText(type);

        this.textName.setEditable(false);
        this.textType.setEditable(false);

        this.box.setSelected(selected);

        Color bg;
        if (odd) {
            bg = MainFrame.BACKGROUND;
        } else {
            bg = MainFrame.FOREGROUND;
        }

        //this.setBackground(bg);
        this.panelWrapper.setBackground(bg);
        this.textName.setBackground(bg);
        this.textType.setBackground(bg);
    }

    /**
     * A convenient constructor for creating non-headers. See full-arguments
     * constructor for details about headers.
     *
     * @param name the name of user to be displayed. Provide full String that is
     * to be displayed.
     * @param type the type of user.
     * @param userId the id of ToothState that this line represents. This vaue
     * must be correct for proper editing functionality, this value is not
     * considered when editing = false;
     * @param editing determines in which state the invoked form will be. False
     * means taht the form will be in state of creating new User, true for
     * editing.
     * @param odd if this line is odd, is is painted in darker color.
     */
    public UserLine(String name, String type, long userId, boolean editing, boolean odd) {
        this(name, type, userId, editing, odd, false, false, false, -1);
    }

    private void myInit() {
        int sw = Toolkit.getDefaultToolkit().getScreenSize().width;
        double w = sw / (MainFrame.PANEL_WIDTH_FRACTION);
        Dimension dim = new Dimension(sw - (int) (3 * w), this.getHeight());
//        this.setSize(dim);
//        this.setPreferredSize(dim);
//        this.panelWrapper.setSize(dim);
//        this.panelWrapper.setPreferredSize(dim);

        //variables initialisation
        editButton = new EdentButton(EDIT_LABEL, SQUARE, FONT_SIZE, odd ? EdentButtonColor.gray : EdentButtonColor.white, BorderLayout.CENTER);
        addButton = new EdentButton(ADD_LABEL, SQUARE, FONT_SIZE, EdentButtonColor.green, BorderLayout.CENTER);
        panelPad = new JPanel();
        textName = new JTextField();
        textType = new JTextField();

        panelWrapper.removeAll();

        textName.setBorder(null);
        textName.setMargin(INSETS);
        int wsubs = editing ? TYPE_LENGHT : 0;
        textName.setPreferredSize(new java.awt.Dimension(dim.width - (2 * SQUARE.width) - 20 - wsubs, SQUARE.height));
        panelWrapper.add(textName);
        
        if (editing||select) {
            textType.setBorder(null);
            textType.setMargin(INSETS);
            textType.setPreferredSize(new java.awt.Dimension(TYPE_LENGHT, SQUARE.height));
            panelWrapper.add(textType);
        } else {
            textName.setHorizontalAlignment(JTextField.RIGHT);
        }

        this.addProperButton();

        box = new JCheckBox();
        box.setOpaque(false);

        panelPad.setPreferredSize(new java.awt.Dimension(PAD_LENGHT, SQUARE.height));
        panelPad.setBackground(MainFrame.FOREGROUND);
        panelPad.setLayout(new java.awt.BorderLayout());

        if (select) {
            if (editing) {
                box.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        User u = ViewController.modelFacade().getUser(userId);
                        Patient p = ViewController.modelFacade().getPatient(patientId);
                        if (box.isSelected()) {
                            u.addPatient(p);
                        } else {
                            u.deletePatient(p);
                        }
                    }
                });
            }

            panelPad.add(box, BorderLayout.WEST);
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
                    ViewController.showEditUser(userId);
                } else {
                    ViewController.showCreateUser();
                }
            }
        });
    }

    public long getUserId() {
        return userId;
    }

    public boolean isSelected() {
        return box.isSelected();
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
