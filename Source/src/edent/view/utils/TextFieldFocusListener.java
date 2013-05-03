/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils;

import edent.controller.ViewController;
import edent.model.User;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Stepan Tesar
 */
public class TextFieldFocusListener implements FocusListener {

    private static final String UNAME_USED = "login je zabraný";
    private final JLabel IMG_OK = new JLabel(new ImageIcon(getClass().getResource("/edent/view/utils/ok.png")));
    private JPanel panelInfo;
    private JTextField textField;
    private boolean required;
    private boolean editing;
    private String cls;
    private String valName;
    private long objectId;
    private String origText;
    private Color origBg;

    /**
     *
     * @param textField the textfield to check the focus on
     * @param editing whether the text field is in entity editing or creating
     * form. If the editing is set to false, valName, cls and id are nt
     * impornant.
     * @param panelInfo the information panel to show the OK icon when object
     * has been updated.
     * @param required true for checking for empty values, false enables empty
     * string.
     * @param valName the name of value of object that is being changed.
     * @param cls the class of the obejct that is being changed.
     * @param id the id of the object that is being changed.
     */
    public TextFieldFocusListener(JTextField textField, JPanel panelInfo, boolean required, boolean editing, String valName, String cls, long id) {
        this.textField = textField;
        this.editing = editing;
        this.panelInfo = panelInfo;
        this.required = required;
        this.objectId = id;
        this.cls = cls;
        this.valName = valName;
        this.origBg = this.textField.getBackground();
    }

    /**
     * A convenient consturcotr for creating fields.
     *
     * @param textField
     * @param panelInfo
     * @param required
     */
    public TextFieldFocusListener(JTextField textField, JPanel panelInfo, boolean required) {
        this(textField, panelInfo, required, false, null, null, -1);
    }

    private void updateObject() {
        if(this.cls==null){
            return;
        }
        String value = this.textField.getText();
        EdentInlineEditableObject o = null;
        switch (this.cls) {
            case "ToothState":
                o = ViewController.modelFacade().getToothState(objectId);
                break;
            case "GumState":
                o = ViewController.modelFacade().getGumState(objectId);
                break;
            case "User":
                o = ViewController.modelFacade().getUser(objectId);
                break;
            case "Patient":
                o = ViewController.modelFacade().getPatient(objectId);
                break;
            case "Appointment":
                o = ViewController.modelFacade().getAppointment(objectId);
                break;
            default:
                break;
        }

        if (o != null) {
            if(this.cls.equals("User")&&this.valName.equals("uname")){
                if(!User.uNameCheck(value)){
                    panelInfo.removeAll();
                    panelInfo.repaint();
                    this.textField.setText(UNAME_USED);
                    this.textField.setBackground(Color.red);
                    return;
                }else{
                    panelInfo.add(IMG_OK);
                    panelInfo.validate();
                    panelInfo.repaint();
                    this.textField.setBackground(origBg);
                }
            }
            o.setStringValue(this.valName, value);
            if(this.cls.equals("Appointment")){
                ViewController.refreshAppts();
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        //System.out.println("focus gained");
        this.origText = textField.getText();
        if (editing) {
            panelInfo.removeAll();
            panelInfo.repaint();
            //panelInfo.setBackground(MainFrame.BACKGROUND);
//            System.out.println("no?");
        } else {
//            System.out.println("not editing");
            this.textField.setText(null);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        //System.out.println("focus lost");
        String text = this.textField.getText();
        //System.out.println("text is: "+text);
        if (editing) {
            //System.out.println("editing");
            if (required && (text == null || text.length() < 1)) {
                this.textField.setBackground(Color.RED);
                //System.out.println("editing - required field empty");
            } else {
                this.textField.setBackground(origBg);
                //System.out.println("editing - notrequired or not epmty");
                if (!origText.equals(text)&&this.objectId!=-1) {
                    //System.out.println("editing - new valid value, updated");
                    
                    //panelInfo.setBackground(MainFrame.BACKGROUND);
                    panelInfo.add(IMG_OK);
                    panelInfo.validate();
                    panelInfo.repaint();
                    this.updateObject();
                    origText = text;
                } else {
                    //System.out.println(origText+" = "+text);
                }
            }

        } else {
            //System.out.println("notediting");
            if (text == null || text.length() < 1) {
                if (required) {
                    this.textField.setBackground(Color.RED);
                    this.textField.setText(EdentInlineEditableObject.INSERT_VALUE);
                    //System.out.println("creating - empty value in required field");
                }
                //System.out.println("creating - empty value is ok");
            } else {
                this.textField.setBackground(this.origBg);
                //System.out.println("creating - new value");
            }
        }
    }
}
