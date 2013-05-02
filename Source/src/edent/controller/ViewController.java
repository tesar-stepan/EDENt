/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.controller;

import edent.view.MainFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Stepan Tesar
 */
public class ViewController {

    private static final MainFrame mainFrame = new MainFrame();
    private static ViewController instance = new ViewController();

    private ViewController() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                String lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
//                try {
//                    UIManager.setLookAndFeel(lookAndFeel);
//                    MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
//                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//                    System.out.println("LookAndFeelException");
//                }
                mainFrame.setVisible(true);
                mainFrame.showUnlogged();
            }
        });
    }

    public static ViewController getInstance() {
        return instance;
    }

    public static void shutDown() {
        mainFrame.dispose();//TODO ask user for confirmation
        System.exit(0); //TODO inform other controllers first!
    }

    public static void showPrevious() {
        mainFrame.showPrevious();
    }

    public static void showSettings() {
        mainFrame.showSettings();
    }

    public static void showUnlogged() {
        mainFrame.showUnlogged();
    }
    
    public static void showEditUser(long id){
        mainFrame.showUserForm();
        Object o = modelFacade().getUser(id);
        mainFrame.setFormEditing(o);
    }
    
    public static void showCreateUser(){
        mainFrame.showUserForm();
        mainFrame.setFormCreating();
    }
    
    public static void showEditPatient(long id){
        mainFrame.showPatientForm();
        Object o = modelFacade().getPatient(id);
        mainFrame.setFormEditing(o);
    }
    
    public static void showCreatePatient(){
        mainFrame.showPatientForm();
        mainFrame.setFormCreating();
    }
    
    public static void showEditAppt(long id){
        mainFrame.showAppointment();
        mainFrame.setFormEditing(id);
    }
    
    public static void showCreateAppt(){
        mainFrame.showAppointment();
        mainFrame.setFormCreating();
    }
    
    public static void showApptDetails(long id){
        mainFrame.showAppointmenDetails();
        Object o = modelFacade().getAppointment(id);
        mainFrame.setFormEditing(o);
    }
    
    /**
     * Displays a mouthForm, which is a main form for creating new diagnoses
     * of patients teeth. 
     * @param apptId id of the appointment from which this session originates.
     * CAUTION - do not pass id of mouth or patient directly, pass id of the
     * appointment with associated patient, whose mouth should be displayed.
     */
    public static void showMouth(long apptId){
        mainFrame.showMouthForm();
        Object o = modelFacade().getAppointment(apptId);
        mainFrame.setFormEditing(o);
    }
    
    public static void refreshAppts(){
        mainFrame.refreshAppts();
    }

    /**
     * MODEL COMMUNICATION facade. All visual components should access any bussiness
     * entities through this facade, to ensure the persistency of objects in
     * database. Creating any bussiness entities by any other means than through
     * this facade can render unpredictable situations and data loss.
     */
    public static ModelController modelFacade() {
        return ModelController.getInstance();
    }

    /**
     * Shows standard yes/no confirmation dialog.
     * @param text the text to be displayed, should be a yes/no question.
     * @param title title of the question window.
     * @return true if user answered yes, false if user answered no.
     */
    public static boolean showConfirmDialog(String text, String title) {
        int reply = JOptionPane.showConfirmDialog(mainFrame, text, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
        if (reply == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
}
