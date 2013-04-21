/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Stepan Tesar
 */
public class User implements java.io.Serializable {
    private long id;
    private String fname;
    private String sname;
    private String uname;
    private String titlePre;
    private String titlePos;
    private UserType type;
    private Set<Patient> patients;
    private Set<Appointment> appointments;
    //img swill be loaded by the uname from a specific directory, no need to save in db or object, only serves for GUI

    public User(String fname, String sname, String uname, String titlePre, String titlePos, UserType type) {
        this.fname = fname;
        this.sname = sname;
        this.uname = uname;
        this.titlePre = titlePre;
        this.titlePos = titlePos;
        this.type = type;
        this.patients = new HashSet<>();
        this.appointments = new HashSet<>();
    }
    
    public void addPatient(Patient p){
        this.patients.add(p);
    }
    
    public boolean deletePatient(Patient p){
        return this.patients.remove(p);
    }
    
    public void addAppointment(Appointment ap){
        this.appointments.add(ap);
    }
    
    public boolean deleteAppointment(Appointment ap){
        return this.appointments.remove(ap);
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
    
}
