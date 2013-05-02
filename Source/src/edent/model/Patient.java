/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model;

import edent.controller.HibernateController;
import edent.view.utils.EdentInlineEditableObject;
import edent.view.utils.TimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Stepan Tesar
 */
public class Patient implements java.io.Serializable, EdentInlineEditableObject {
    private long id;
    private String fname;
    private String sname;
    private String birthnum;
    private long birthdate;
    private long created;
    //img swill be loaded by the uname from a specific directory, no need to save in db or object, only serves for GUI
    
    private Mouth mouth;
    private Set<User> doctors;
    
    public Patient(){
        
    }

    public Patient(String fname, String sname, String birthNum, long birthDate, long created, Set<User> doctors) {
        this.fname = fname;
        this.sname = sname;
        this.birthnum = birthNum;
        this.birthdate = birthDate;
        this.created = created;
        this.doctors = doctors;
        
        HibernateController.create(this);
        Mouth m = new Mouth(this);
        HibernateController.create(m);
        this.mouth = m;
    }
    
    private void update(){
        HibernateController.update(this);
    }
    
    public void addDoctor(User u){
        this.doctors.add(u);
        update();
    }
    
    public void deleteDoctor(User u){
        this.doctors.remove(u);
        update();
    }
    
    //getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getBirthnum() {
        return birthnum;
    }

    public void setBirthnum(String birthNum) {
        this.birthnum = birthNum;
    }

    public long getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(long birthDate) {
        this.birthdate = birthDate;
    }

    public long getCreated() {
        return created;
    }

    private void setCreated(long created) {
        this.created = created;
    }

    public Mouth getMouth() {
        return mouth;
    }

    private void setMouth(Mouth mouth) {
        this.mouth = mouth;
    }

    public Set<User> getDoctors() {
        return doctors;
    }

    private void setDoctors(Set<User> doctors) {
        this.doctors = doctors;
    }
    
    @Override
    public String toString(){
        return getClass()+":"+this.getId();
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
            case "bdate":
                long bdate = TimeFormatter.getPatientsBDate(value);
                if(bdate!=-1){
                    this.setBirthdate(bdate);
                }else{
                    System.out.println("Invalid date property: "+value);
                }
                break;
            case "bnum":
                this.setBirthnum(value);
                break;
            default:
                System.err.println("Property "+name+" not found in class Patient. Tried to assign value: "+value);
                break;
        }
        this.update();
    }
    
}
