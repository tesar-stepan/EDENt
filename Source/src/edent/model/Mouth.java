/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model;

import edent.model.utils.Diagnosable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Stepan Tesar
 */
public class Mouth extends Diagnosable implements java.io.Serializable{
    private Set<Tooth> teeth;
    private Set<Gum> gums;
    
    public Mouth(){
        
    }

    public Mouth(Set<Tooth> teeth, Set<Gum> gums, Patient owner) {
        super(owner);
        this.teeth = teeth;
        this.gums = gums;
        this.owner = owner;
        this.gums = new HashSet<>();
        this.teeth = new HashSet<>();
    }
    
    public void addGum(Gum gum){
        this.gums.add(gum);
    }
    
    public void addTooth(Tooth tooth){
        this.teeth.add(tooth);
    }
    
    public boolean deleteGum(Gum gum){
        return this.gums.remove(gum);
    }
    
    public boolean deleteTooth(Tooth t){
        return this.teeth.remove(t);
    }
    
    //getters and setters

    public Set<Tooth> getTeeth() {
        return teeth;
    }

    public void setTeeth(Set<Tooth> teeth) {
        this.teeth = teeth;
    }

    private Set<Gum> getGums() {
        return gums;
    }

    public void setGums(Set<Gum> gums) {
        this.gums = gums;
    }

    public Patient getOwner() {
        return owner;
    }

    public void setOwner(Patient owner) {
        this.owner = owner;
    }
    
}
