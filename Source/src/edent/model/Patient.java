/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model;

import edent.controller.hibernate.HibernateController;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Stepan Tesar
 */
public class Patient implements java.io.Serializable{
    private long id;
    private String fname;
    private String sname;
    private String birthNum;
    private long birthDate;
    private long created;
    
    private Mouth mouth;
    private Set<User> doctors;
    
    public Patient(){
        
    }

    public Patient(String fname, String sname, String birthNum, long birthDate, long created) {
        this.fname = fname;
        this.sname = sname;
        this.birthNum = birthNum;
        this.birthDate = birthDate;
        this.created = created;
        this.doctors = new HashSet<>();
        
        HibernateController.create(this);
        Mouth m = new Mouth(null, null, this);
        HibernateController.create(m);
        this.mouth = m;
    }
    
    public void addDoctor(User u){
        this.doctors.add(u);
    }
    
    public boolean deleteDoctor(User u){
        return this.doctors.remove(u);
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

    public String getBirthNum() {
        return birthNum;
    }

    public void setBirthNum(String birthNum) {
        this.birthNum = birthNum;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
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
    
}
