/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils;

import edent.controller.ViewController;
import edent.view.MainFrame;
import edent.view.utils.settings.SettingsPanelApplication;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author Stepan Tesar
 */
public class OrganStateLine extends AbstractLine {

    private static final String DELETE_LABEL = "X";
    private static final String ADD_LABEL = "+";
    private static final String DELETE_CONFIRM_TEXT = "Chcete skutečně smazat stav ";
    private static final String DELETE_CONFIRM_TITLE = "Potvrďte smazání";
    private static final Dimension SQUARE = new Dimension(25, 25);
    private static final int LINE_LENGHT = 150;
    private static final int PAD_LENGHT = 40;
    private static final int FONT_SIZE = 18;
    private static final Insets INSETS = new Insets(0, 0, 0, 0);
    private EdentButton buttonIcon;
    private EdentButton deleteButton;
    private EdentButton addButton;
    private JSeparator sep1;
    private JSeparator sep2;
    private JPanel panelInfo;
    private JPanel panelPad;
    private JTextField textMark;
    private JTextField textName;
    private long organStateId;
    //private JFileChooser picChooser;
    private boolean editing;
    private SettingsPanelApplication parent;
    private String type;

    /**
     * Creates new visual component for editing/creating ToothState
     *
     * @param name the name of state to be displayed
     * @param icon the icon of state to be displayed
     * @param mark the mark of state to be displayed
     * @param organStateId the id of ToothState that this line represents. This
     * vaue must be correct for proper editing functionality, this value is not
     * considered when editing = false;
     * @param parent the parenting settings panel. Nessesary for informing it
     * when new object has been created, and it is nessesary to repaint the
     * list.
     * @param editing when set to false, component allows user to create new
     * ToothState. Instead of delete button, there is an ADD button.
     * @param type the type of organ that this line represents. Use "ToothState"
     * or "GumState".
     */
    public OrganStateLine(String name, String icon, String mark, long organStateId, SettingsPanelApplication parent, boolean editing, String type) {
        this.parent = parent;
        this.editing = editing;
        this.organStateId = organStateId;
        this.type = type;

        initComponents();
        myInit();
        addListeners();

        this.textName.setText(name);
        this.textMark.setText(mark);
        //TODO set icon of button
    }

    /**
     * Creates new form OrganStateLine in editing state
     */
    public OrganStateLine(String name, String icon, String mark, long organStateId, SettingsPanelApplication parent, String type) {
        this(name, icon, mark, organStateId, parent, true, type);
    }
    
    /**
     * Creates new form OrganStateLine in creating state
     */
    public OrganStateLine(String name, SettingsPanelApplication parent, String type) {
        this(name, "", "", -1, parent, false, type);
    }

    private void addListeners() {
        if (editing) {
            this.textName.addFocusListener(new TextFieldFocusListener(textName, panelInfo, true, true, "name", type, organStateId));
            this.textMark.addFocusListener(new TextFieldFocusListener(textMark, panelInfo, false, true, "mark", type, organStateId));
        } else {
            this.textName.addFocusListener(new TextFieldFocusListener(textName, panelInfo, true));
            this.textMark.addFocusListener(new TextFieldFocusListener(textMark, panelInfo, false));
        }
    }

    private void myInit() {
        //variables initialisation
        buttonIcon = new EdentButton("", SQUARE, FONT_SIZE, EdentButtonColor.white, BorderLayout.CENTER);
        deleteButton = new EdentButton(DELETE_LABEL, SQUARE, FONT_SIZE, EdentButtonColor.red, BorderLayout.CENTER);
        addButton = new EdentButton(ADD_LABEL, SQUARE, FONT_SIZE, EdentButtonColor.green, BorderLayout.CENTER);
        sep1 = new JSeparator();
        sep2 = new JSeparator();
        panelInfo = new JPanel();
        panelPad = new JPanel();
        textMark = new JTextField();
        textName = new JTextField();


        //adding components to layout
        textMark.setHorizontalAlignment(JTextField.CENTER);
        textMark.setBorder(null);

        panelWrapper.removeAll();

        textName.setBorder(null);
        textName.setMargin(INSETS);
        textName.setPreferredSize(new java.awt.Dimension(LINE_LENGHT, SQUARE.height));
        panelWrapper.add(textName);

        sep1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        sep1.setPreferredSize(new java.awt.Dimension(1, SQUARE.height));
        panelWrapper.add(sep1);

        buttonIcon.setBorder(null);
        buttonIcon.setMargin(null);
        buttonIcon.setPreferredSize(SQUARE);
        panelWrapper.add(buttonIcon);

        textMark.setMargin(INSETS);
        textMark.setPreferredSize(SQUARE);
        panelWrapper.add(textMark);

        sep2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        sep2.setPreferredSize(new java.awt.Dimension(15, SQUARE.height));
        panelWrapper.add(sep2);

        this.addProperButton();

        panelInfo.setPreferredSize(SQUARE);
        panelInfo.setBackground(MainFrame.FOREGROUND);
        panelInfo.setLayout(new java.awt.BorderLayout());
        panelWrapper.add(panelInfo);

        panelPad.setPreferredSize(new java.awt.Dimension(PAD_LENGHT, SQUARE.height));
        panelPad.setBackground(MainFrame.FOREGROUND);
        panelPad.setLayout(new java.awt.BorderLayout());
        panelWrapper.add(panelPad);

        panelWrapper.validate();
    }

    /**
     * Adds a proper button based on this.editing value. Also crates button
     * action listeners with corresponding actions.
     */
    private void addProperButton() {
        EdentButton enabledButton;
        EdentButton disabledButton;
        if (editing) {
            enabledButton = this.deleteButton;
            disabledButton = this.addButton;
        } else {
            enabledButton = this.addButton;
            disabledButton = this.deleteButton;
        }

        disabledButton.setEnabled(false);
        enabledButton.setMargin(null);
        enabledButton.setPreferredSize(SQUARE);
        panelWrapper.add(enabledButton);

        enabledButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (editing) {
                    if (ViewController.showConfirmDialog(DELETE_CONFIRM_TEXT, DELETE_CONFIRM_TITLE)) {
                        switch (type) {
                            case "ToothState":
                                ViewController.modelFacade().deleteToothState(organStateId);
                                parent.createToothList();
                                break;
                            case "GumState":
                                parent.createGumList();
                                ViewController.modelFacade().deleteGumState(organStateId);
                                parent.createGumList();
                                break;
                        }
                    }
                } else {
                    String name = textName.getText();
                    String mark = textMark.getText();
                    //String icon = picChooser.getSelectedFile().getAbsolutePath(); //TODO file checking and copying
                    String icon = null;
                    if (name != null && name.length() > 0) {
                        switch (type) {
                            case "ToothState":
                                ViewController.modelFacade().createToothState(name, mark, icon);
                                parent.createToothList();
                                break;
                            case "GumState":
                                ViewController.modelFacade().createGumState(name, mark, icon);
                                parent.createGumList();
                                break;
                        }
                    }
                }
            }
        });
    }

    public long getOrganStateId() {
        return organStateId;
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelWrapper;
    private javax.swing.JFileChooser picChooser;
    // End of variables declaration//GEN-END:variables
}
