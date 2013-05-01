package edent.model;

import edent.controller.HibernateController;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stepan Tesar
 */
public class Appointment implements java.io.Serializable{
    private long id;
    private long date;
    private String note;
    private User creator;
    private Set<User> servers;
    private Patient patient;
    private List<Diagnosis> diagnoses;

    public Appointment(long date, String note, User creator, Patient patient) {
        this.date = date;
        this.note = note;
        this.creator = creator;
        this.patient = patient;
        this.diagnoses = new ArrayList<>();
        this.servers = new HashSet<>();
    }
    
    private void update(){
        HibernateController.update(this);
    }
    
    public void addServer(User server){
        this.servers.add(server);
    }
    
    public boolean deleteServer(User server){
        return this.servers.remove(server);
    }
    
    public void addDiagnosis(Diagnosis d){
        this.diagnoses.add(d);
    }
    
    public boolean deleteDiagnosis(Diagnosis d){
        return this.diagnoses.remove(d);
    }
    
    
    // getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getCreator() {
        return creator;
    }

    private void setCreator(User creator) {
        this.creator = creator;
    }

    public Patient getPatient() {
        return patient;
    }

    private void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    private void setDiagnoses(List<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }

    private Set<User> getServers() {
        return servers;
    }

    public void setServers(Set<User> servers) {
        this.servers = servers;
    }
    
    @Override
    public String toString(){
        return getClass()+":"+this.getId();
    }
    
    public void changePatient(Patient p){
        this.setPatient(p);
        update();
    }
    
}
