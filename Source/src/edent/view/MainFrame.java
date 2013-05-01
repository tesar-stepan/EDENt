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
    public static final Color FOREGROUND =  Color.white;
    public static final int TOPPANEL_HEIGHT = 100;
    public static final double PANEL_WIDTH_FRACTION = 5;
    
    private static final Dimension MIN_DIMENSION = new Dimension(800,600);
    private static final String TITLE_LABEL = "EDEN.t";
    
    private UserForm userForm = new UserForm();
    private PatientForm patientForm = new PatientForm();
    private AppointmentForm apptForm = new AppointmentForm();
    private AppointmentDetailsForm apptDetails = new AppointmentDetailsForm();
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
        
        double w = this.getWidth()/PANEL_WIDTH_FRACTION;
        Dimension sideDim = new Dimension((int)w, this.getHeight());
        Dimension mainDim = new Dimension(this.getWidth()-(int)w, this.getHeight());
        
        this.add(sidePanel, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
        sidePanel.setPreferredSize(sideDim);
        sidePanel.setSize(sideDim);
        mainPanel.setPreferredSize(mainDim);
        mainPanel.setSize(mainDim);
        
        mainPanel.add(this.apptDetails);
        mainPanel.add(this.apptForm);
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
        if(this.activeForm.equals(this.settings)){
            this.sidePanel.setSettingsSelected();
        }
        this.activeForm.setVisible(true);
        this.activeForm.setSize(this.mainPanel.getSize());
    }
    
    private void hideActive(){
        if(this.activeForm.equals(this.settings)){
            this.sidePanel.deselectButtons();
        }
        this.activeForm.resetForm();
        this.activeForm.setVisible(false);
    }
    
    private void showForm(EdentForm form){
        this.hideActive();
        form.setPrevious(this.activeForm);
        this.activeForm = form;
        this.showActive();
    }
    
    /***************************************************
     * Public methods
     ***************************************************/
    
    /**
     * Functionality is currently disabled because of problems with editing forms.
     * Unlogged for is always displayed.
     */
    public void showPrevious(){
        this.hideActive();
//        this.activeForm = this.activeForm.displayPrevious();
//        if(this.activeForm==null||this.activeForm==this.userForm||this.activeForm==this.patientForm){
            this.activeForm = this.unlogged;
//        }
        this.showActive();
    }
    
    public void showUnlogged(){
        this.showForm(this.unlogged);
    }
    
    public void showUserForm(){
        this.showForm(this.userForm);
    }
    
    public void showPatientForm(){
        this.showForm(this.patientForm);
    }
    
    public void showMouthForm(){
        this.showForm(this.mouthForm);
    }
    
    public void showAppointment(){
        this.showForm(this.apptForm);
    }
    
    public void showAppointmenDetails(){
        this.showForm(this.apptDetails);
    }
    
    public void showCalendar(){
        this.showForm(this.calendar);
    }
    
    public void showQuickstart(){
        this.showForm(this.quickstart);
    }
    
    public void showSettings(){
        this.showForm(this.settings);
    }
    
    public void setFormEditing(Object o){
        this.activeForm.setEditing(o);
    }
    
    public void setFormCreating(){
        this.activeForm.setCreating();
    }

}
