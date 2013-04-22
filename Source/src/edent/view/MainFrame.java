/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view;

import edent.view.forms.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JRootPane;

/**
 *
 * @author Stepan Tesar
 */
public class MainFrame extends javax.swing.JFrame {
    /***************************************************
     * Variable declaration
     ***************************************************/
    public static final Color BACKGROUND =  new Color(150, 150, 150);
    public static final int TOPPANEL_HEIGHT = 100;
    private static final Dimension MIN_DIMENSION = new Dimension(800,600);
    private static final String TITLE_LABEL = "EDEN.t";
    private static final int PANEL_WIDTH_FRACTION = 6;
    
    private UserForm userForm = new UserForm();
    private PatientForm patientForm = new PatientForm();
    private AppointmentForm ApptForm = new AppointmentForm();
    private AppointmentDetailsForm ApptDetails = new AppointmentDetailsForm();
    private CalendarForm calendar = new CalendarForm();
    private MouthForm mouthForm = new MouthForm();
    private QuickstartForm quickstart = new QuickstartForm();
    private SettingsForm settings = new SettingsForm();
    private UnloggedForm unlogged = new UnloggedForm();
    
    private SidePanel sidePanel = new SidePanel();
    private MainPanel mainPanel = new MainPanel();
    private EdentForm activeForm = unlogged;

    /***************************************************
     * Constructor
     ***************************************************/
    public MainFrame() {
        this.setTitle(TITLE_LABEL);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        this.repaint();
    }
    
    /***************************************************
     * Private methods
     ***************************************************/

    private void initComponents() {
        this.setFullscreen();
        
        int w = this.getWidth()/PANEL_WIDTH_FRACTION;
        Dimension sideDim = new Dimension(w, this.getHeight());
        Dimension mainDim = new Dimension(this.getWidth()-w, this.getHeight());
        
        this.add(sidePanel, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
        sidePanel.setPreferredSize(sideDim);
        sidePanel.setSize(sideDim);
        mainPanel.setPreferredSize(mainDim);
        mainPanel.setSize(mainDim);
        
        mainPanel.add(this.ApptDetails);
        mainPanel.add(this.ApptForm);
        mainPanel.add(this.calendar);
        mainPanel.add(this.mouthForm);
        mainPanel.add(this.patientForm);
        mainPanel.add(this.patientForm);
        mainPanel.add(this.quickstart);
        mainPanel.add(this.settings);
        mainPanel.add(this.unlogged);
        mainPanel.add(this.userForm);
        
    }
    
    private void setFullscreen(){
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(0, 0);
        this.setSize(screenSize);
        
        this.setMinimumSize(MIN_DIMENSION);
    }
    
    private void showActive(){
        this.activeForm.setVisible(true);
        this.activeForm.setSize(this.mainPanel.getSize());
    }
    
    private void hideActive(){
        this.activeForm.setVisible(false);
    }
    
    /***************************************************
     * Public methods
     ***************************************************/
    
    public void showUnlogged(){
        this.hideActive();
        this.activeForm = this.unlogged;
        this.showActive();
    }
    
    public void showUserForm(){
        this.hideActive();
        this.activeForm = this.userForm;
        this.showActive();
    }
    
    public void showPatientForm(){
        this.hideActive();
        this.activeForm = this.patientForm;
        this.showActive();
    }
    
    public void showMouthForm(){
        this.hideActive();
        this.activeForm = this.mouthForm;
        this.showActive();
    }
    
    public void showAppointment(){
        this.hideActive();
        this.activeForm = this.ApptForm;
        this.showActive();
    }
    
    public void showAppointmenDetails(){
        this.hideActive();
        this.activeForm = this.ApptDetails;
        this.showActive();
    }
    
    public void showCalendar(){
        this.hideActive();
        this.activeForm = this.calendar;
        this.showActive();
    }
    
    public void showQuickstart(){
        this.hideActive();
        this.activeForm = this.quickstart;
        this.showActive();
    }
    
    public void showSettings(){
        this.hideActive();
        this.activeForm = this.settings;
        this.showActive();
    }

    

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrame mf = new MainFrame();
                mf.setVisible(true);
                mf.showUnlogged();
            }
        });
    }
}
