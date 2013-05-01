/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model.utils;

import edent.controller.HibernateController;
import edent.model.Diagnosis;
import edent.model.History;
import edent.model.Patient;

/**
 *
 * @author Stepan Tesar
 */
public abstract class Diagnosable implements java.io.Serializable{
    private long id;
    protected History history;
    protected Patient owner;
    
    public Diagnosable(){
        
    }

    public Diagnosable(Patient owner) {
        this.owner = owner;
        HibernateController.create(this);
        History h = new History(this);
        HibernateController.create(h);
        this.history = h;
//        System.out.println("diagnosable construct finished");
    }
    
    /**
     * Adds a new diagnosis into the history of this diagnosable object, using
     * current date.
     * @param diagnosis the diagnosis to be added
     */
    public void addDiagnosis(Diagnosis diagnosis){
        this.history.addDiagnosis(diagnosis, System.currentTimeMillis());
    }
    
    /**
     * Inserts a new diagnosis into the specified date of the history of this
     * diagnosable object.
     * @param diagnosis the diagnosis to be added.
     * @param date the date to instert the diagnosis into.
     */
    public void addDiagnosis(Diagnosis diagnosis, long date){
        this.history.addDiagnosis(diagnosis, date);
    }
    
    //getters and setters
    
    /**
     * @return the whole dianostic history of this diagnosable object.
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public History getHistory() {
        return history;
    }

    private void setHistory(History history) {
        this.history = history;
    }
    
    @Override
    public String toString(){
        return getClass()+":"+this.getId();
    }
    
}
