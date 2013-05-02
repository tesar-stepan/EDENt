/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model;

import edent.controller.HibernateController;
import edent.view.utils.TimeFormatter;

/**
 *
 * @author Stepan Tesar
 */
public class Diagnosis implements java.io.Serializable, Comparable {

    private long id;
    private long date;
    private User creator;
    private User doctor;
    private String text;
//    private Set<History> inHistories;
    private History history;
    private Appointment origAppointment = null;

    public Diagnosis() {
        
    }

    public Diagnosis(long date, User creator, User doctor, String text, History history, Appointment origin) {
        this.date = date;
        this.creator = creator;
        this.doctor = doctor;
        this.text = text;
        HibernateController.create(this);
        
//        if (inHistories != null) {
//            this.inHistories = inHistories;
//            for(History h : inHistories){
//                h.addDiagnosis(this);
//            }
//        } else {
//            this.inHistories = new HashSet<>();
//        }
        this.history = history;
        if(history!=null){
            history.addDiagnosis(this);
        }
        
        this.origAppointment = origin;
        if(origin!=null){
            origin.addDiagnosis(this);
        }
        this.update();
        
//        this.update();
    }
    
    private void update(){
        HibernateController.update(this);
    }

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getDoctor() {
        return doctor;
    }

    private void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public long getDate() {
        return date;
    }

    private void setDate(long date) {
        this.date = date;
    }

    public User getCreator() {
        return creator;
    }

    private void setCreator(User creator) {
        this.creator = creator;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public History getHistory() {
        return history;
    }

    private void setHistory(History history) {
        this.history = history;
    }

//    public Set<History> getInHistories() {
//        return inHistories;
//    }
//
//    private void setInHistories(Set<History> inHistories) {
//        this.inHistories = inHistories;
//    }

    public Appointment getOrigAppointment() {
        return origAppointment;
    }

    private void setOrigAppointment(Appointment origAppointment) {
        this.origAppointment = origAppointment;
    }
    
    @Override
    public String toString(){
        String time = TimeFormatter.getAppointmentDate(date);
        String dr = this.doctor.getSname();
        String by = this.creator!=null?", zapsal/a "+this.creator.getSname():"";
        String txt = this.text!=null?this.text:"";
        
        return time+", "+dr+" "+by+": "+txt;
    }

    @Override
    public int compareTo(Object o) {
        if(o.getClass().equals(this.getClass())){
            Diagnosis d = (Diagnosis) o;
            return (int) (this.date-d.date);
        }
        return 0;
    }
    
    
    
}
