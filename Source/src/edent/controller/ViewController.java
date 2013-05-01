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

    /**
     * MODEL COMMUNICATION
     */
    public static ModelController modelFacade() {
        return ModelController.getInstance();
    }

    public static boolean showConfirmDialog(String text, String title) {
        int reply = JOptionPane.showConfirmDialog(mainFrame, text, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
        if (reply == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
}
