/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.forms;

import edent.controller.ViewController;
import edent.model.User;
import edent.model.UserType;
import edent.view.MainFrame;
import edent.view.utils.EdentButton;
import edent.view.utils.EdentButtonColor;
import edent.view.utils.TextFieldFocusListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Stepan Tesar
 */
public final class UserForm extends EdentForm {
    private final JLabel IMG_OK = new JLabel(new ImageIcon(getClass().getResource("/edent/view/utils/ok.png")));
    private static final String UNAME_USED = "login je zabraný";
    private static final Dimension SQUARE = new Dimension(25, 25);
    private static final Dimension BUTTON_SIZE = new Dimension(100, 40);
    private static final String BUTTON_DELETE_LABEL = "Smazat";
    private static final String BUTTON_SAVE_LABEL = "Uložit";
    private static final String RADIO_DR_LABEL = "Doktor";
    private static final String RADIO_NR_LABEL = "Sestra";
    private static final String NEWPASS_LABEL_CREATING = "<- Zadejte heslo";
    private static final String FNAME_TEXT = "Jméno";
    private static final String SNAME_TEXT = "Příjmení";
    private static final String UNAME_TEXT = "Login";
    private static final String TITPRE_TEXT = "Titul";
    private static final String TITPOS_TEXT = "Titul za";
    private static final String PASS_TEXT = "";
    private static final String ORIGPASS_LABEL = "Původní heslo";
    private static final String NEWPASS_LABEL = "Nové heslo";
    private static final String INFO_TEXT_EDITING = "Pro změnu údajů je jednoduše přepište. Po uložení se zobrazí potvrzovací ikona. "
            + "\r\nPokud chcete změnit heslo, je nutné nejprve zadat původní, a poté níže vyplnit nové. Pokud se původní heslo shoduje, bude nové heslo uloženo."
            + "Změnu hesla potvrďte tlačítkem. V případě zadání chybného původního hesla, se textové pole zabarví do červena.";
    private static final String INFO_TEXT_CREATING = "Pro změnu údajů je jednoduše přepište. Pro uložení použijte zelené tlačítko.";
    private static final String DELETE_CONFIRM_TEXT = "Chcete skutečně smazat uživatele?";
    private static final String DELETE_CONFIRM_TITLE = "Potvrďte smazání";
    private static final String SAVEPASS_BUTTON_LABEL = "Uložit heslo";
    private static final int BUTTON_FONT_SIZE = 20;
    private boolean editing;
    private long userId = -1;

    /**
     * Creates new form User
     */
    public UserForm() {
        initComponents();
        this.labelPasswd.setText(ORIGPASS_LABEL);
        this.labelNewPasswd.setText(NEWPASS_LABEL);

        panelInfo.setPreferredSize(SQUARE);
        panelInfo.setBackground(MainFrame.FOREGROUND);
        panelInfo.setLayout(new java.awt.BorderLayout());

        this.radioDr.setText(RADIO_DR_LABEL);
        this.radioNr.setText(RADIO_NR_LABEL);

        this.setBackground(MainFrame.BACKGROUND);
        this.resetForm();
    }

    private void myInit() {
        EdentButton buttonAction = editing
                ? new EdentButton(BUTTON_DELETE_LABEL, BUTTON_SIZE, BUTTON_FONT_SIZE, EdentButtonColor.red, BorderLayout.CENTER)
                : new EdentButton(BUTTON_SAVE_LABEL, BUTTON_SIZE, BUTTON_FONT_SIZE, EdentButtonColor.green, BorderLayout.CENTER);

        buttonAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editing) {
                    if(userId == ViewController.getLogged().getId()){
                        return;
                    }
                    if (ViewController.showConfirmDialog(DELETE_CONFIRM_TEXT, DELETE_CONFIRM_TITLE)) {
                        ViewController.modelFacade().deleteUser(userId);
                        ViewController.showPrevious();
                    }
                } else {
                    String fname = textFName.getText();
                    String sname = textSName.getText();
                    String uname = textUName.getText();
                    String titpre = textTitPre.getText();
                    String titpos = textTitPos.getText();
                    String pass = new String(textPass.getPassword());

                    if (!User.uNameCheck(uname)) {
                        panelInfo.removeAll();
                        panelInfo.repaint();
                        textUName.setText(UNAME_USED);
                        textUName.setBackground(Color.red);
                        return;
                    } 
//                    else {
//                        panelInfoLogin.add(IMG_OK);
//                        panelInfo.validate();
//                        panelInfo.repaint();
//                        textUName.setBackground(panelFoto.getBackground());
//                    }

                    UserType type = getType();
                    //String icon = picChooser.getSelectedFile().getAbsolutePath(); //TODO file checking and copying
                    if (fname != null && fname.length() > 0 && sname != null && sname.length() > 0 && uname != null && uname.length() > 0 && pass != null && pass.length() > 0) {
                        ViewController.modelFacade().createUser(fname, sname, uname, titpre, titpos, type, pass);
                        ViewController.showPrevious();
                    }
                }
            }
        });

        this.buttonWrap.add(buttonAction);
    }

    private boolean passCheck(String pass) {
        User u = ViewController.modelFacade().getUser(userId);

        String uPass = u.getPass();
        String checkPass = User.SHAsum(pass.getBytes());
//            System.out.println(checkPass + " : " + uPass);
        return uPass.equals(checkPass);
    }

    private UserType getType() {
        if (this.radioDr.isSelected()) {
            return UserType.doctor;
        } else if (this.radioNr.isSelected()) {
            return UserType.nurse;
        }
        return null;
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
        for (FocusListener fl : textUName.getFocusListeners()) {
            if (fl.getClass().equals(TextFieldFocusListener.class)) {
                textUName.removeFocusListener(fl);
            }
        }
        for (FocusListener fl : textTitPre.getFocusListeners()) {
            if (fl.getClass().equals(TextFieldFocusListener.class)) {

                textTitPre.removeFocusListener(fl);
            }
        }
        for (FocusListener fl : textTitPos.getFocusListeners()) {
            if (fl.getClass().equals(TextFieldFocusListener.class)) {
                textTitPos.removeFocusListener(fl);
            }
        }
//        System.out.println("removed all listeners");
    }

    private void addListeners() {
        if (editing) {
            this.textFName.addFocusListener(new TextFieldFocusListener(textFName, panelInfo, true, true, "fname", "User", this.userId));
            this.textSName.addFocusListener(new TextFieldFocusListener(textSName, panelInfo, true, true, "sname", "User", this.userId));
            this.textUName.addFocusListener(new TextFieldFocusListener(textUName, panelInfoLogin, true, true, "uname", "User", this.userId));
            this.textTitPre.addFocusListener(new TextFieldFocusListener(textTitPre, panelInfo, false, true, "titpre", "User", this.userId));
            this.textTitPos.addFocusListener(new TextFieldFocusListener(textTitPos, panelInfo, false, true, "titpos", "User", this.userId));

//            System.out.println("added editing listeners");
        } else {
            this.textFName.addFocusListener(new TextFieldFocusListener(textFName, panelInfo, true));
            this.textSName.addFocusListener(new TextFieldFocusListener(textSName, panelInfo, true));
            this.textUName.addFocusListener(new TextFieldFocusListener(textUName, panelInfoLogin, true));
            this.textTitPre.addFocusListener(new TextFieldFocusListener(textTitPre, panelInfo, false));
            this.textTitPos.addFocusListener(new TextFieldFocusListener(textTitPos, panelInfo, false));
//            System.out.println("added creating listeners");
        }
    }

    @Override
    public void setEditing(Object o) {
        this.resetForm();
        this.editing = true;
        this.myInit();
        User u = (User) o;

        this.textFName.setText(u.getFname());
//        this.textPass.setText(u.getPass());
        this.textSName.setText(u.getSname());
        this.textTitPos.setText(u.getTitlePos());
        this.textTitPre.setText(u.getTitlePre());
        this.textUName.setText(u.getUname());

        this.infoBox.setText(INFO_TEXT_EDITING);

        this.textPass2.setVisible(true);
        this.labelNewPasswd.setVisible(true);
        this.labelNewPasswd.setText(NEWPASS_LABEL);
        this.labelPasswd.setText(ORIGPASS_LABEL);
        this.buttonSavePass.setVisible(true);

        switch (u.getType()) {
            case doctor:
                this.selectDoctor();
                break;
            case nurse:
                this.selectNurse();
                break;
        }

        this.userId = u.getId();
        this.addListeners();
    }

    private void selectNurse() {
        this.radioNr.setSelected(true);
        this.radioDr.setSelected(false);
    }

    private void selectDoctor() {
        this.radioNr.setSelected(false);
        this.radioDr.setSelected(true);
    }

    @Override
    public void setCreating() {
        this.resetForm();
        this.myInit();
        this.addListeners();
    }

    @Override
    public void resetForm() {
        this.removeListeners();
        this.editing = false;
        this.infoBox.setText(INFO_TEXT_CREATING);

        panelFoto.removeAll();
        textFName.setText(FNAME_TEXT);
        textTitPre.setText(TITPRE_TEXT);
        textSName.setText(SNAME_TEXT);
        textTitPos.setText(TITPOS_TEXT);
        textUName.setText(UNAME_TEXT);

        textUName.setBackground(panelFoto.getBackground());
        
        textPass.setText(PASS_TEXT);
        textPass2.setText(PASS_TEXT);
        textPass2.setVisible(false);
        this.labelNewPasswd.setVisible(false);
        this.labelPasswd.setText(NEWPASS_LABEL_CREATING);
        this.buttonSavePass.setVisible(false);

        textPass.setText(PASS_TEXT);
        radioDr.setSelected(true);
        radioNr.setSelected(false);

        this.panelInfo.removeAll();
        this.panelInfoPass.removeAll();
        this.panelInfoLogin.removeAll();

        this.buttonWrap.removeAll();
        this.userId = -1;
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
        textTitPos = new javax.swing.JTextField();
        textFName = new javax.swing.JTextField();
        textTitPre = new javax.swing.JTextField();
        radioNr = new javax.swing.JRadioButton();
        radioDr = new javax.swing.JRadioButton();
        panelFoto = new javax.swing.JPanel();
        textPass = new javax.swing.JPasswordField();
        textUName = new javax.swing.JTextField();
        panelInfo = new javax.swing.JPanel();
        labelPasswd = new javax.swing.JLabel();
        textPass2 = new javax.swing.JPasswordField();
        labelNewPasswd = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoBox = new javax.swing.JTextArea();
        panelInfoLogin = new javax.swing.JPanel();
        buttonSavePass = new EdentButton(SAVEPASS_BUTTON_LABEL, new Dimension(127,23), 14, EdentButtonColor.gray, BorderLayout.CENTER);
        panelInfoPass = new javax.swing.JPanel();

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

        textTitPos.setBackground(new java.awt.Color(240, 240, 240));
        textTitPos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textTitPos.setText("titul za");
        textTitPos.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textTitPos.setMaximumSize(new java.awt.Dimension(50, 32));
        textTitPos.setMinimumSize(new java.awt.Dimension(50, 32));
        textTitPos.setPreferredSize(new java.awt.Dimension(50, 32));

        textFName.setBackground(new java.awt.Color(240, 240, 240));
        textFName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textFName.setText("jmeno");
        textFName.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textFName.setMaximumSize(new java.awt.Dimension(117, 32));
        textFName.setMinimumSize(new java.awt.Dimension(117, 32));
        textFName.setPreferredSize(new java.awt.Dimension(117, 32));

        textTitPre.setBackground(new java.awt.Color(240, 240, 240));
        textTitPre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textTitPre.setText("titul");
        textTitPre.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textTitPre.setMaximumSize(new java.awt.Dimension(45, 32));
        textTitPre.setMinimumSize(new java.awt.Dimension(45, 32));
        textTitPre.setPreferredSize(new java.awt.Dimension(45, 32));

        radioNr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioNr.setText("sestra");
        radioNr.setOpaque(false);
        radioNr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNrActionPerformed(evt);
            }
        });

        radioDr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioDr.setText("doktor");
        radioDr.setOpaque(false);
        radioDr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFotoLayout = new javax.swing.GroupLayout(panelFoto);
        panelFoto.setLayout(panelFotoLayout);
        panelFotoLayout.setHorizontalGroup(
            panelFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelFotoLayout.setVerticalGroup(
            panelFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        textPass.setBackground(new java.awt.Color(240, 240, 240));
        textPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textPass.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textPass.setMinimumSize(new java.awt.Dimension(127, 27));
        textPass.setPreferredSize(new java.awt.Dimension(127, 27));

        textUName.setBackground(new java.awt.Color(240, 240, 240));
        textUName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textUName.setText("login");
        textUName.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textUName.setMinimumSize(new java.awt.Dimension(127, 27));
        textUName.setPreferredSize(new java.awt.Dimension(127, 27));

        panelInfo.setBackground(new java.awt.Color(255, 255, 255));
        panelInfo.setPreferredSize(new java.awt.Dimension(25, 25));
        panelInfo.setLayout(new java.awt.BorderLayout());

        labelPasswd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelPasswd.setText("puvodni heslo");
        labelPasswd.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));

        textPass2.setBackground(new java.awt.Color(240, 240, 240));
        textPass2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textPass2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        textPass2.setMinimumSize(new java.awt.Dimension(127, 27));
        textPass2.setPreferredSize(new java.awt.Dimension(127, 27));
        textPass2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textPass2FocusGained(evt);
            }
        });

        labelNewPasswd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelNewPasswd.setText("nove heslo");
        labelNewPasswd.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));

        infoBox.setColumns(20);
        infoBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infoBox.setLineWrap(true);
        infoBox.setRows(5);
        infoBox.setWrapStyleWord(true);
        infoBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jScrollPane1.setViewportView(infoBox);

        panelInfoLogin.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoLogin.setPreferredSize(new java.awt.Dimension(25, 25));
        panelInfoLogin.setLayout(new java.awt.BorderLayout());

        buttonSavePass.setText("Uložit heslo");
        buttonSavePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSavePassActionPerformed(evt);
            }
        });

        panelInfoPass.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoPass.setPreferredSize(new java.awt.Dimension(25, 25));
        panelInfoPass.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(radioNr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(radioDr)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textTitPre, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFName, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textSName, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textTitPos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(buttonSavePass, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(textPass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textPass2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textUName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(labelPasswd, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                        .addComponent(labelNewPasswd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(panelInfoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelInfoPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 354, Short.MAX_VALUE)
                        .addComponent(buttonWrap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textSName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textTitPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textTitPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(panelInfoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textUName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(labelPasswd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(textPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(labelNewPasswd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(textPass2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(panelFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonSavePass)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(radioDr)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radioNr))
                                    .addComponent(panelInfoPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(buttonWrap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(129, 129, 129)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(labelPass, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(553, Short.MAX_VALUE))
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
                .addGap(23, 23, 23)
                .addComponent(labelPass)
                .addContainerGap(105, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void radioDrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDrActionPerformed
        this.selectDoctor();
        if (editing) {
            User u = ViewController.modelFacade().getUser(userId);
            u.changeType(UserType.doctor);
        }
    }//GEN-LAST:event_radioDrActionPerformed

    private void radioNrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNrActionPerformed
        this.selectNurse();
        if (editing) {
            User u = ViewController.modelFacade().getUser(userId);
            u.changeType(UserType.nurse);
        }
    }//GEN-LAST:event_radioNrActionPerformed

    private void buttonSavePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSavePassActionPerformed
        if (!this.passCheck(new String(this.textPass.getPassword()))) {
            this.textPass.setBackground(Color.RED);
            this.textPass.setText(null);
        } else {
            this.textPass.setBackground(textPass2.getBackground());
            User u = ViewController.modelFacade().getUser(userId);
//            if (
            u.changePass(new String(this.textPass2.getPassword()));
//                    ) {
            this.panelInfoPass.add(IMG_OK);
            this.panelInfoPass.validate();
            this.panelInfoPass.repaint();
//            }
            this.textPass.setText(null);
            this.textPass2.setText(null);
        }
    }//GEN-LAST:event_buttonSavePassActionPerformed

    private void textPass2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textPass2FocusGained
        this.panelInfoPass.remove(IMG_OK);
    }//GEN-LAST:event_textPass2FocusGained
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSavePass;
    private javax.swing.JPanel buttonWrap;
    private javax.swing.JTextArea infoBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelNewPasswd;
    private javax.swing.JLabel labelPass;
    private javax.swing.JLabel labelPasswd;
    private javax.swing.JPanel panelFoto;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelInfoLogin;
    private javax.swing.JPanel panelInfoPass;
    private javax.swing.JRadioButton radioDr;
    private javax.swing.JRadioButton radioNr;
    private javax.swing.JTextField textFName;
    private javax.swing.JPasswordField textPass;
    private javax.swing.JPasswordField textPass2;
    private javax.swing.JTextField textSName;
    private javax.swing.JTextField textTitPos;
    private javax.swing.JTextField textTitPre;
    private javax.swing.JTextField textUName;
    // End of variables declaration//GEN-END:variables
}
