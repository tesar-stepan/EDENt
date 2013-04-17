/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stepan Tesar
 */
public class History implements java.io.Serializable{
    private long id;
    private List<Diagnosis> diagnoses;
    private Diagnosable owner;
    
    protected History(){
        System.out.println("------------------>>>history created "+owner);
    }

    protected History(Diagnosable owner) {
        this.owner = owner;
        this.diagnoses = new ArrayList<>();
        System.out.println("------------------>>>history created "+this.owner);
    }
    
    public void addDiagnosis(Diagnosis diagnosis, long date){
        diagnoses.add(diagnosis); //TODO add to correct position;
    }
    
    public boolean deleteDiagnosis(Diagnosis d){
        return this.diagnoses.remove(d);
    }
    
    //getters and setters

    public Tooth getTooth() {
        if(owner.getClass().equals(Tooth.class)){
            return (Tooth) owner;
        }
        return null;
    }

    private void setTooth(Tooth tooth) {
        if(tooth!=null)this.owner = tooth;
    }

    public Gum getGum() {
        System.out.println();
        if(owner.getClass().equals(Gum.class)){
            return (Gum) owner;
        }
        return null;
    }

    private void setGum(Gum gum) {
        if(gum!=null)this.owner = gum;
    }

    public Mouth getMouth() {
        if(owner.getClass().equals(Mouth.class)){
            return (Mouth) owner;
        }
        return null;
    }

    private void setMouth(Mouth mouth) {
        if(mouth!=null)this.owner = mouth;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public List<Diagnosis> getDiagnoses() {
        return diagnoses;
    }
    
    /**
     * Use add diagnosis instead.
     * @see this.addDiagnosis(Diagnosis diagnosis, long date);
     * @param diagnoses 
     */
    private void setDiagnoses(List<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Diagnosable getOwner() {
        return owner;
    }

    private void setOwner(Diagnosable owner) {
        this.owner = owner;
    }
    
}
