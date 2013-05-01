/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model;

import edent.controller.HibernateController;
import edent.view.utils.EdentInlineEditableObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Stepan Tesar
 */
public class User implements java.io.Serializable, EdentInlineEditableObject {

    private long id;
    private String fname;
    private String sname;
    private String uname;
    private String titlePre;
    private String titlePos;
    private UserType type;
    private Set<Patient> patients;
    private Set<Appointment> appointments;
    
    /**
     * Password hashed by SHA-1 algorhytm. 
     */
    private String pass;
    //img swill be loaded by the uname from a specific directory, no need to save in db or object, only serves for GUI

    public User() {
    }
    
    /**
     * Creates new User entity.
     * @param fname first name
     * @param sname surname
     * @param uname user name, used for logging into system
     * @param titlePre pre-name titles
     * @param titlePos post-name titles
     * @param type type of user
     * @param pass UNHASHED password.
     */
    public User(String fname, String sname, String uname, String titlePre, String titlePos, UserType type, String pass) {
        this.fname = fname;
        this.sname = sname;
        this.uname = uname;
        this.titlePre = titlePre;
        this.titlePos = titlePos;
        this.type = type;
        this.patients = new HashSet<>();
        this.appointments = new HashSet<>();
        this.hashNewPass(pass);
    }

    private boolean hashNewPass(String newPass) {
        try {
            this.setPass(SHAsum(newPass.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            return false;
        }
//        System.out.println("new pass: "+newPass);
        return true;
    }

    public static String SHAsum(byte[] convertme) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        return byteArray2Hex(md.digest(convertme));
    }

    private static String byteArray2Hex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
    
    private void update(){
        HibernateController.update(this);
    }

    public void addPatient(Patient p) {
        this.patients.add(p);
        p.addDoctor(this);
        update();
    }

    public void deletePatient(Patient p) {
        this.patients.remove(p);
        p.deleteDoctor(this);
        update();
    }

    public void addAppointment(Appointment ap) {
        this.appointments.add(ap);
        ap.addServer(this);
        update();
    }

    public void deleteAppointment(Appointment ap) {
        this.appointments.remove(ap);
        ap.deleteServer(this);
        update();
    }

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserType getType() {
        return type;
    }

    private void setType(UserType type) {
        this.type = type;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getTitlePre() {
        return titlePre;
    }

    public void setTitlePre(String titlePre) {
        this.titlePre = titlePre;
    }

    public String getTitlePos() {
        return titlePos;
    }

    public void setTitlePos(String titlePos) {
        this.titlePos = titlePos;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    private void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    private void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
    
    /**
     * 
     * @return a SHA1 sum of password set for this user.
     */
    public String getPass() {
        return pass;
    }

    private void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return getClass() + ":" + this.getId();
    }

    @Override
    public void setStringValue(String name, String value) {
        switch (name) {
            case "sname":
                this.setSname(value);
                break;
            case "fname":
                this.setFname(value);
                break;
            case "uname":
                this.setUname(value);
                break;
            case "titpre":
                this.setTitlePre(value);
                break;
            case "titpos":
                this.setTitlePos(value);
                break;
            case "pass":
                this.hashNewPass(value);
                break;
            default:
                System.err.println("Property "+name+" not found in class User. Tried to assign value: "+value);
                break;
        }
        this.update();
    }
    
    public void changeType(UserType type){
        this.type = type;
        this.update();
    }
    
    public boolean changePass(String pass){
        boolean bl = this.hashNewPass(pass);
        this.update();
        return bl;
    }
    
}
