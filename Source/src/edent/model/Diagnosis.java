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
public class Diagnosis implements java.io.Serializable {

    private long id;
    private long date;
    private User creator;
    private User doctor;
    private String text;
    private Set<History> inHistories;
    private Appointment origAppointment = null;

    public Diagnosis(long date, User creator, User doctor, String text, Set<History> inHistories, Appointment origin) {
        this.date = date;
        this.creator = creator;
        this.doctor = doctor;
        this.text = text;
        if (inHistories != null) {
            this.inHistories = inHistories;
        } else {
            this.inHistories = new HashSet<>();
        }
        this.origAppointment = origin;

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

    public Set<History> getInHistories() {
        return inHistories;
    }

    private void setInHistories(Set<History> inHistories) {
        this.inHistories = inHistories;
    }

    public Appointment getOrigAppointment() {
        return origAppointment;
    }

    private void setOrigAppointment(Appointment origAppointment) {
        this.origAppointment = origAppointment;
    }
}
