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
    
    public Mouth(){
        
    }

    public Mouth(Patient owner) {
        super(owner);
        this.owner = owner;
        this.teeth = new HashSet<>();
        
        //top right
        this.teeth.add(new Tooth(false, true, false, 8, this));
        this.teeth.add(new Tooth(false, true, false, 7, this));
        this.teeth.add(new Tooth(false, true, false, 6, this));
        this.teeth.add(new Tooth(false, true, false, 5, this));
        this.teeth.add(new Tooth(false, true, false, 4, this));
        this.teeth.add(new Tooth(false, true, false, 3, this));
        this.teeth.add(new Tooth(false, true, false, 2, this));
        this.teeth.add(new Tooth(false, true, false, 1, this));
        System.out.println("----------------------------> created teeth: "+this.teeth.size());
        //top left
        this.teeth.add(new Tooth(false, true, true, 1, this));
        this.teeth.add(new Tooth(false, true, true, 2, this));
        this.teeth.add(new Tooth(false, true, true, 3, this));
        this.teeth.add(new Tooth(false, true, true, 4, this));
        this.teeth.add(new Tooth(false, true, true, 5, this));
        this.teeth.add(new Tooth(false, true, true, 6, this));
        this.teeth.add(new Tooth(false, true, true, 7, this));
        this.teeth.add(new Tooth(false, true, true, 8, this));
        System.out.println("----------------------------> created teeth: "+this.teeth.size());
        
        //bottom right
        this.teeth.add(new Tooth(false, false, false, 8, this));
        this.teeth.add(new Tooth(false, false, false, 7, this));
        this.teeth.add(new Tooth(false, false, false, 6, this));
        this.teeth.add(new Tooth(false, false, false, 5, this));
        this.teeth.add(new Tooth(false, false, false, 4, this));
        this.teeth.add(new Tooth(false, false, false, 3, this));
        this.teeth.add(new Tooth(false, false, false, 2, this));
        this.teeth.add(new Tooth(false, false, false, 1, this));
        System.out.println("----------------------------> created teeth: "+this.teeth.size());
        //bootom left
        this.teeth.add(new Tooth(false, false, true, 1, this));
        this.teeth.add(new Tooth(false, false, true, 2, this));
        this.teeth.add(new Tooth(false, false, true, 3, this));
        this.teeth.add(new Tooth(false, false, true, 4, this));
        this.teeth.add(new Tooth(false, false, true, 5, this));
        this.teeth.add(new Tooth(false, false, true, 6, this));
        this.teeth.add(new Tooth(false, false, true, 7, this));
        this.teeth.add(new Tooth(false, false, true, 8, this));
        System.out.println("----------------------------> created teeth: "+this.teeth.size());
        
    }
    
//    public void addTooth(Tooth tooth){
//        this.teeth.add(tooth);
//    }
//    
//    public boolean deleteTooth(Tooth t){
//        return this.teeth.remove(t);
//    }
    
    //getters and setters

    public Set<Tooth> getTeeth() {
        return teeth;
    }

    private void setTeeth(Set<Tooth> teeth) {
        this.teeth = teeth;
    }
    
    public Patient getOwner() {
        return owner;
    }

    private void setOwner(Patient owner) {
        this.owner = owner;
    }
    
}
