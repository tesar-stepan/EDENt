/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model;

import edent.controller.HibernateController;
import edent.model.utils.Diagnosable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Stepan Tesar
 */
public class History implements java.io.Serializable{
    private long id;
    private Set<Diagnosis> diagnoses;
    private Diagnosable owner;
    
    protected History(){
        
    }

    public History(Diagnosable owner) {
        this.owner = owner;
        this.diagnoses = new HashSet<>();
    }
    
    private void update(){
        HibernateController.update(this);
    }
    
    public void addDiagnosis(Diagnosis diagnosis){
        diagnoses.add(diagnosis);
//        this.update();
    }
    
    public void deleteDiagnosis(Diagnosis d){
        this.diagnoses.remove(d);
        this.update();
    }
    
    //getters and setters

    public Tooth getTooth() {
        if(owner.getClass().equals(Tooth.class)){
            return (Tooth) owner;
        }
        return null;
    }

    private void setTooth(Tooth tooth) {
        if(tooth!=null){
            this.owner = tooth;
        }
    }

    public Gum getGum() {
//        System.out.println();
        if(owner.getClass().equals(Gum.class)){
            return (Gum) owner;
        }
        return null;
    }

    private void setGum(Gum gum) {
        if(gum!=null){
            this.owner = gum;
        }
    }

    public Mouth getMouth() {
        if(owner.getClass().equals(Mouth.class)){
            return (Mouth) owner;
        }
        return null;
    }

    private void setMouth(Mouth mouth) {
        if(mouth!=null){
            this.owner = mouth;
        }
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public Set<Diagnosis> getDiagnoses() {
        return diagnoses;
    }
    
    /**
     * Use add diagnosis instead.
     * @see this.addDiagnosis(Diagnosis diagnosis, long date);
     * @param diagnoses 
     */
    private void setDiagnoses(Set<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Diagnosable getOwner() {
        return owner;
    }

    private void setOwner(Diagnosable owner) {
        this.owner = owner;
    }
    
    @Override
    public String toString(){
        return getClass()+":"+this.getId();
    }
    
}
